package modules;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by adric on 10/10/2016.
 */
public interface ControllerInterface {

    /**
     * Handle a post request sent from the servlet
     * @return an {@link HttpServletResponse}
     */
    HttpServletResponse handlePost();

    /**
     * Handle a get resquest sent from the servlet
     * @return an {@link HttpServletResponse}
     */
    HttpServletResponse handleGet();


}
