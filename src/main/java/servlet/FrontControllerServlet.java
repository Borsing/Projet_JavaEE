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

import java.io.PrintWriter;

/**
 * Created by adric on 07/10/2016.
 * This controller is the unique controller in the application. All requests will pass by it.
 *
 */
public class FrontControllerServlet extends HttpServlet{

    private static final RequestRouter router = new RequestRouter();
    private static final DatabaseManager databaseManager = DatabaseManager.getInstance();

    @Override
    public void destroy() {
        super.destroy();
        databaseManager.closeEntityManagerFactory();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        try {
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
        ServletContext context;
        context = getServletContext();

        router.handleRequest(req,resp, context);
        rd = context.getRequestDispatcher("/layout/index.jsp"); // Do not forward to another jsp
        if(rd != null){
            rd.forward(req,resp);
        }

        /*PrintWriter pr = resp.getWriter();

        databaseManager.listEntitiesOfDatabase().forEach(pr::println);*/



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
