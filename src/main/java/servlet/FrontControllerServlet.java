package servlet;

import servlet.utils.RequestRouter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
/**
 * Created by adric on 07/10/2016.
 * This controller is the unique controller in the application. All requests will pass by it.
 *
 */
public class FrontControllerServlet extends HttpServlet{

    public static final RequestRouter router = new RequestRouter();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher rd ;
        ServletContext context;
        // TODO Split the request with the @RequestRouter (/user, /event, /participant)

        // TODO Call the right process (@EventService or @ParticipantService or @UserService
        // TODO Forward to the template to change the body of the page

        context = getServletContext();
        router.handleRequest(req,resp, context);
        rd = context.getRequestDispatcher("/layout/template.jsp"); // Do not forward to another jsp
        if(rd != null){
            rd.forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
