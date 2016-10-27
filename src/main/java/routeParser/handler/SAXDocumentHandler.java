package routeParser.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import routeParser.om.HttpMethod;
import routeParser.om.Route;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adric on 21/10/2016.
 */
public class SAXDocumentHandler extends DefaultHandler {
    //Nous nous servirons de cette variable plus tard
    private String node = null;
    private final String value = "value";

    private List<Route> routes = null;
    private Route route = null;

    //début du parsing
    public void startDocument() throws SAXException {
        System.out.println("D�but du parsing");
    }
    //fin du parsing
    public void endDocument() throws SAXException {
        System.out.println("Fin du parsing");
    }

    /**
     * Redéfinition de la méthode pour intercepter les événements
     */
    public void startElement(String namespaceURI, String lname,
                             String qname, Attributes attrs) throws SAXException {

        node = qname;

        //dès que nous rencontrons un élément, nous créons l'objet associé
        switch (qname) {
            case "routes":
                routes = new ArrayList<>();
                break;
            case "route":
                assert attrs != null;
                route = new Route(attrs.getValue("id"), Boolean.valueOf(attrs.getValue("default-route")));
                break;
            case "method":
                route.setMethod(HttpMethod.valueOf(attrs.getValue(value)));
                break;
            case "targeted-service":
                route.setTargetedService(attrs.getValue(value));
                break;
            case "parameter":
                route.getParameters().add(attrs.getValue(value));
                break;
            case "jsp":
                route.setJsp(attrs.getValue(value));
                break;
            case "redirected-jsp":
                route.setRedirectedJsp(attrs.getValue(value));
                break;
            case "url":
                route.setUrl(attrs.getValue(value));
                break;
        }
    }

    public void endElement(String uri, String localName, String qName)
            throws SAXException{
        if(qName.equals("route")){
            routes.add(route);
        }
    }

    public List<Route> getRoutes() {
        return routes;
    }
}
