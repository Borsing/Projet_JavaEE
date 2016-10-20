package servlet.utils;


import modules.event.EventDAO;
import modules.participant.ParticipantDAO;
import modules.organizer.OrganizerDAO;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by adric on 07/10/2016.
 */
public class RequestRouter {



    public void handleRequest(HttpServletRequest req, HttpServletResponse resp, ServletContext context) {

        String pathInfo = req.getPathInfo();
        String[] urlFragments = pathInfo.split("/");
        Map parameters = req.getParameterMap();

        OrganizerDAO userService = new OrganizerDAO();
        EventDAO eventController = new EventDAO();
        ParticipantDAO participantService = new ParticipantDAO();


            switch (req.getMethod()){
                case "GET":
                    switch (urlFragments[1]) {
                        case "users":
                            switch (urlFragments[2]) {
                                case "login":
                                    context.setAttribute("page","login");
                                    break;
                                case "register":
                                    break;
                                case "settings":
                                    break;
                            }
                            break;
                        case "events":
                            switch (urlFragments[2]) {
                                case "all":
                                    break;
                                case "create":
                                    context.setAttribute("page","create-event");
                                    break;
                                case "sort":
                                    break;
                                case "myevents":
                                    break;
                                case "detail":
                                    break;
                                case "join":
                                    break;
                            }
                            break;
                    }

                case "POST":
                    switch (urlFragments[1]) {
                        case "users":
                            switch (urlFragments[2]) {
                                case "login":
                                    break;
                                case "logout":
                                    break;
                                case "register":
                                    break;
                            }
                                    break;
                                case "events":
                                    switch (urlFragments[2]) {
                                        case "myevents":
                                            break;
                                        case "create":
                                            context.setAttribute("page","create-event");
                                            break;
                                        case "join":
                                            break;
                                    }
                                    break;
                            }
                    }
    }


}
