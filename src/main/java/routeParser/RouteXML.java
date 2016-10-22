package routeParser;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;
import routeParser.handler.SAXDocumentHandler;
import routeParser.om.Route;

import javax.servlet.ServletContext;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adric on 21/10/2016.
 */
public class RouteXML {

    private static final String URL = "/WEB-INF/router.xml";

    public static List<Route> parse(ServletContext context) {
        List<Route> routes = new ArrayList<>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            SAXDocumentHandler saxDocumentHandler = new SAXDocumentHandler();

            InputStream input = context.getResourceAsStream(URL);

            parser.parse(input, saxDocumentHandler);

            routes = saxDocumentHandler.getRoutes();

        } catch (DOMException | ParserConfigurationException | SAXException | TransformerFactoryConfigurationError | IOException e) {
            e.printStackTrace();
        }

        return routes;
    }

}
