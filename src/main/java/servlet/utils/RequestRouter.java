package servlet.utils;


import routeParser.RouteXML;
import routeParser.om.Route;
import modules.security.SecurityService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
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
    private static final String PAGE_404 = "404";



    /** Constructeur privé */
    private RequestRouter() {
    }
    /** Point d'accès pour l'instance unique du singleton */
    public static RequestRouter getInstance()
    {	return INSTANCE;
    }

    public void initRoutes() {

        routes = RouteXML.parse(getContext());
        System.out.println("Routes " +  routes.toString());

    }




    public void handleRequest(HttpServletRequest req, HttpServletResponse resp, ServletContext context) {

        Route route = null;

        try {
            route = getRouteOfURL(req.getPathInfo(),req.getParameterMap(),req.getMethod());

            if(!route.isRequiredConnection() || (route.isRequiredConnection() && SecurityService.isConnected(req))) {
                Object object = process(route, req, resp, context);
                if(object != null)
                System.out.println(object.toString());
            }

        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            for(Route definedRoute : routes){
                if (PAGE_404.equals(definedRoute.getId()))
                    route = definedRoute;
            }
            e.printStackTrace();
        }

        assert route != null;
        redirect(route.getJsp());

    }

    private Object process(Route route, HttpServletRequest req, HttpServletResponse resp, ServletContext context) throws ClassNotFoundException, NoSuchMethodException {
        Object data = null;
        if(!("".equals(route.getTargetedService())) && !("".equals(route.getTargetedMethod()))) {
            Class<?> service = Class.forName(route.getTargetedService());

            Method method = null;
            for(Method m : service.getDeclaredMethods()){
                if(m.getName().equals(route.getTargetedMethod())){
                    method = m;
                }
            }

            if(SecurityService.class.getName().equals(service.getName())){
                switch (method.getName()){
                    case "login":
                        try {
                            data =  method.invoke(service.newInstance(), getParameters(req,true).toArray());
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "logout":
                        try {
                            data =  method.invoke(service.newInstance(), req);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }

            else if(method.getParameterCount() == req.getParameterMap().size()){
                try {
                    data =  method.invoke(service.newInstance(), getParameters(req,false).toArray());
                } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                    e.printStackTrace();
                }
            }

        }

        return data;

    }

    private List<Object> getParameters(HttpServletRequest request, boolean includeRequest) {
        List<Object> values = new ArrayList<>();
        for(Map.Entry<String, String[]> entry: request.getParameterMap().entrySet()) {
            values.add(entry.getValue()[0]);
        }

        if(includeRequest){
            values.add(request);
        }

        Collections.reverse(values);
        return  values;

    }

    // TODO Insert the response here too.
    private void redirect(String jsp) {
        context.setAttribute(PAGE,jsp);
    }

    private Route getRouteOfURL(String pathInfo, Map parameters, String method) throws URISyntaxException{

        System.out.println("Methode : " + method + " PATH INFO " + pathInfo);
        System.out.println("Parametres : " + parameters.toString());


        // Get the Route matching with the urlFragments and the method
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
