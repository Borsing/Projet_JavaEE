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

            System.out.println("INIT SERVLET ");
            context = getServletContext();
            ROUTER.setContext(context);
            ROUTER.initRoutes();
            databaseManager.populate();
        } catch (PersistenceException ex) {
            /*System.out.println("EXCEPTION CLASS NAME: " + ex.getClass().getName().toString());
            System.out.println("THROWABLE CLASS NAME: " + ex.getCause().getClass().getName().toString());
            Throwable th = ex.getCause();
            System.out.println("THROWABLE INFO: " + th.getCause().getClass().getName().toString());*/
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

        /*PrintWriter pr = resp.getWriter();

        databaseManager.listEntitiesOfDatabase().forEach(pr::println);*/



    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp, ServletContext context) {
        ROUTER.handleRequest(req,resp,context);
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
