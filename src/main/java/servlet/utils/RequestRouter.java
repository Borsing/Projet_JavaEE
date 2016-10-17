package servlet.utils;


import modules.event.EventService;
import modules.participant.ParticipantService;
import modules.user.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by adric on 07/10/2016.
 */
public class RequestRouter {



    public void handleRequest(HttpServletRequest req, HttpServletResponse resp, ServletContext context) {

        String pathInfo = req.getPathInfo();
        String[] urlFragments = pathInfo.split("/");
        Map parameters = req.getParameterMap();

        UserService userService = new UserService();
        EventService eventController = new EventService();
        ParticipantService participantService = new ParticipantService();


        try {
            PrintWriter printWriter = resp.getWriter();
            printWriter.println("Path info : " + pathInfo +"\n");
            printWriter.println("Url Fragments : " + urlFragments[1] +"\n");
            printWriter.println("Parameters : " + parameters.toString() +"\n");

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
