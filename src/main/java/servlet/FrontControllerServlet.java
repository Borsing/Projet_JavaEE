package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by adric on 07/10/2016.
 * This controller is the unique controller in the application. All requests will pass by it.
 *
 */
public class FrontControllerServlet extends HttpServlet{


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        // TODO Split the request with the @RequestHandlerInterface (/user, /event, /participant)
        // TODO Call the right process (@EventController or @ParticipantController or @UserController
        // TODO Forward to the template to change the body of the page

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
