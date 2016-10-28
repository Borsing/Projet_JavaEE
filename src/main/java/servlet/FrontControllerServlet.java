package servlet;

import modules.DatabaseManager;
import servlet.utils.RequestRouter;

import javax.persistence.PersistenceException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

/**
 * Created by adric on 07/10/2016.
 * This controller is the unique controller in the application. All requests will pass by it.
 *
 */
public class FrontControllerServlet extends HttpServlet{

    private static final RequestRouter ROUTER = RequestRouter.getInstance();
    private static final DatabaseManager databaseManager = DatabaseManager.getInstance();
    private ServletContext context;
    @Override
    public void destroy() {
        super.destroy();
        databaseManager.closeEntityManagerFactory();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            context = getServletContext();
            ROUTER.setContext(getServletContext());
            ROUTER.initRoutes();
            databaseManager.populate();
        } catch (PersistenceException | ClassNotFoundException | ParseException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher rd;

        this.handleRequest(req,resp, context);
        rd = context.getRequestDispatcher("/layout/index.jsp"); // Do not forward to another jsp
        if(rd != null){
            rd.forward(req,resp);
        }

    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp, ServletContext context) {
        ROUTER.handleRequest(req,resp,context);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd;

        this.handleRequest(req,resp, context);
        rd = context.getRequestDispatcher("/layout/index.jsp"); // Do not forward to another jsp
        if(rd != null){
            rd.forward(req,resp);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }


}
