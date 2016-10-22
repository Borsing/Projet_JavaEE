package servlet.utils;


import modules.event.EventDAO;
import modules.participant.ParticipantDAO;
import modules.organizer.OrganizerDAO;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import routeParser.RouteXML;
import routeParser.handler.SAXDocumentHandler;
import routeParser.om.Route;

import javax.print.URIException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.*;
import javax.xml.transform.TransformerFactoryConfigurationError;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

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
            // TODO Call the Database Manager if needed and redirect the right page passing the database Response
            System.out.println(route.toString());
            redirect(route.getJsp());
        } catch (URISyntaxException e) {
            for(Route definedRoute : routes){
                if (PAGE_404.equals(definedRoute.getId()))
                    route = definedRoute;
            }
            e.printStackTrace();
        }

        redirect(route.getJsp());

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
