package modules.security;

import modules.organizer.OrganizerDAO;
import modules.organizer.OrganizerEntity;

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

    public void login(HttpServletRequest req, String mail, String password) {


        HttpSession httpSession = req.getSession();
        httpSession.setAttribute(ATT_SESSION_USER, mail);


        System.out.println("je me suis connecte avec " + mail);

    }

    public static HttpSession getUserSession(HttpServletRequest req){
        return req.getSession(false);
    }
}
