package modules.security;

import exception.BeanException;
import exception.EnumException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by adric on 24/10/2016.
 */
public class SecurityService {

    public static final String ATT_SESSION_USER = "session-user";

    public void logout(HttpServletRequest req){

        HttpSession session = req.getSession();
        session.invalidate();

    }

    public static boolean isConnected(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        return !(session == null || session.getAttribute(ATT_SESSION_USER) == null);
    }

    public void login(HttpServletRequest req, String mail, String password) throws BeanException {

        // TODO Check the user is good and throw new Exception if not
        System.out.println("Je log avec mail " + mail);
        req.setAttribute("exception", EnumException.USER_ALREADY_EXISTS);
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute(ATT_SESSION_USER, mail);

        throw new BeanException(EnumException.WRONG_LOGIN);

    }

    public static HttpSession getUserSession(HttpServletRequest req){
        return req.getSession(false);
    }
}
