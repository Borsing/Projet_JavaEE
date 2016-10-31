package servlet.utils;


import exception.BeanException;
import modules.ArgumentsParser;
import routeParser.RouteXML;
import routeParser.om.Route;
import modules.security.SecurityService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.*;
import java.util.List;

/**
 * Created by adric on 07/10/2016.
 */
public class RequestRouter {

    private static RequestRouter INSTANCE = new RequestRouter();
    private static List<Route> routes = null;
    private ServletContext context;
    private static final String PAGE = "page";
    private static final String SESSION = "session";
    private static final String EXCEPTION = "exception";
    private static final String DATA = "data";

    private static final String PAGE_404 = "404";
    private static final String PAGE_UNAUTHORIZED = "unauthorized";




    /** Constructeur privé */
    private RequestRouter() {
    }
    /** Point d'accès pour l'instance unique du singleton */
    public static RequestRouter getInstance()
    {	return INSTANCE;
    }

    public void initRoutes() {
        routes = RouteXML.parse(getContext());
    }


    public void handleRequest(HttpServletRequest req, HttpServletResponse resp, ServletContext context) {

        Route route = null;
        Object data = null;
        BeanException beanException = null;

        try {
            route = getRouteOfURL(req.getPathInfo(),req.getParameterMap(),req.getMethod());

            if(!route.isRequiredConnection() || (route.isRequiredConnection() && SecurityService.isConnected(req))) {
                data = process(route, req, resp, context);
            }
            else{
                for(Route definedRoute : routes){
                    if (PAGE_UNAUTHORIZED.equals(definedRoute.getId()))
                        route = definedRoute;
                }
            }

        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            for(Route definedRoute : routes){
                if (PAGE_404.equals(definedRoute.getId()))
                    route = definedRoute;
            }
            e.printStackTrace();
        } catch (BeanException e) {
            beanException = e;
            redirect(route.getRedirectedJsp(), req.getSession(false), data, beanException);
            System.out.println("redirected to = " + route.getRedirectedJsp());
            e.printStackTrace();
            return;
        }

        assert route != null;
        redirect(route.getJsp(), req.getSession(false), data, beanException);

    }

    private Object process(Route route, HttpServletRequest req, HttpServletResponse resp, ServletContext context) throws ClassNotFoundException, NoSuchMethodException, BeanException {
        Object data = null;
        if(!("".equals(route.getTargetedService())) && !("".equals(route.getTargetedMethod()))) {
            Class<?> service = Class.forName(route.getTargetedService());

            Method method = null;
            for(Method m : service.getDeclaredMethods()){
                if(m.getName().equals(route.getTargetedMethod())){
                    method = m;
                }
            }

            try {
                if(SecurityService.class.getName().equals(service.getName())){
                    assert method != null;
                    data =  method.invoke(service.newInstance(), getMethodParameters(req,method.getParameterTypes(),true).toArray());
                } else {
                    assert method != null;
                    data = method.invoke(service.newInstance(), getMethodParameters(req, method.getParameterTypes(), false).toArray());
                }
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e){
                if (e.getCause() instanceof BeanException) {
                    context.setAttribute(EXCEPTION, e.getCause());
                    throw new BeanException(((BeanException) e.getCause()).getEnumException());
                }
            }
        }

        return data;

    }

    private List<Object> getMethodParameters(HttpServletRequest request, Class<?>[] parameterTypes, boolean includeRequest) {
        List<Object> values = new ArrayList<>();

        System.out.println("values.toString() = " + Arrays.toString(parameterTypes));
        
        List<Object> mapValues = new ArrayList<>();

        System.out.println("request.getParameterMap() = " + request.getParameterMap().toString());

        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet())
        {
            mapValues.add(entry.getValue()[0]);
        }

        if(includeRequest){
            mapValues.add(request);
        }

        for(int i=0; i < parameterTypes.length ;i++){
            try {
                values.add(ArgumentsParser.convertTo(parameterTypes[i],mapValues.get(i)));
            } catch (ClassNotFoundException | ParseException e) {
                e.printStackTrace();
            }
        }

        Collections.reverse(values);

        System.out.println("mapValues after = " + values.toString());

        return  values;

    }

    private void redirect(String jsp, HttpSession session, Object data, BeanException beanException) {
        if(session != null)
            context.setAttribute(SESSION, session.getAttribute(SecurityService.ATT_SESSION_USER));
        else
            context.setAttribute(SESSION, null);
        context.setAttribute(DATA, data);
        context.setAttribute(PAGE,jsp);
        if(beanException != null)
        context.setAttribute(EXCEPTION, beanException.getEnumException());
        else
            context.setAttribute(EXCEPTION, null);
        System.out.println("jsp = " + context.getAttribute(PAGE));
        System.out.println("session = " + context.getAttribute(SESSION));
        System.out.println("exception = " + context.getAttribute(EXCEPTION));
        System.out.println("data = " + context.getAttribute(DATA));


    }

    private Route getRouteOfURL(String pathInfo, Map parameters, String method) throws URISyntaxException{

        for(Route definedRoute : routes){
            if(definedRoute.getMethod().getValue().equals(method)
                    && definedRoute.getUrl().equals(pathInfo)
                    && checkParameters(definedRoute.getParameters(),parameters)){
                return definedRoute;
            }
        }

        throw new URISyntaxException("URI Syntax Exception","Bad URL or/and bad parameters");
    }

    private boolean checkParameters(List<String> definedParameters, Map parameters) {

        if(definedParameters.size() != parameters.size())
            return false;

        for (Object parameter : parameters.keySet()) {
            if(!definedParameters.contains(parameter)){
                return false;
            }
        }

        return true;
    }


    public ServletContext getContext() {
        return context;
    }

    public void setContext(ServletContext context) {
        this.context = context;
    }

}
