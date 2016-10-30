package modules.security;

import exception.BeanException;
import exception.EnumException;
import modules.organizer.OrganizerService;

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
        OrganizerService organizerService = new OrganizerService();
        System.out.println("Login " + organizerService.checkLogin(mail,password));

        // TODO Check the user is good and throw new Exception if not
        if(!organizerService.checkLogin(mail,password)){
            System.out.println("NOT ");
            throw new BeanException(EnumException.WRONG_LOGIN);
        }
        else{
            System.out.println("OUI" + mail);

            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute(ATT_SESSION_USER, organizerService.findOrganizerById(mail));
        }

    }

    public static HttpSession getUserSession(HttpServletRequest req){
        return req.getSession(false);
    }
}
