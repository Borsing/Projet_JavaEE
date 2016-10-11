package servlet.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by adric on 07/10/2016.
 */
public interface RequestHandlerInterface {

    ParsedRequest parseRequest(HttpServletRequest request);


}
