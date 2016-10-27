package routeParser.om;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adric on 21/10/2016.
 */
public class Route {
    private String id = "";
    private boolean defaultRoute = false;
    private boolean requiredConnection = false;
    private String url = "";
    private String targetedService = "";
    private String targetedMethod = "";
    private HttpMethod method;
    private List<String> parameters = new ArrayList<>();
    private String jsp = "";
    private String redirectedJsp = "";

    public Route(String id, boolean defaultRoute, boolean requiredConnection) {
        this.id = id;
        this.defaultRoute = defaultRoute;
        this.requiredConnection = requiredConnection;
    }

    public void addParameter(String parameter) {
        this.parameters.add(parameter);
    }

    public String getTargetedMethod() {
        return targetedMethod;
    }

    public void setTargetedMethod(String targetedMethod) {
        this.targetedMethod = targetedMethod;
    }

    public String getTargetedService() {
        return targetedService;
    }

    public void setTargetedService(String targetedService) {
        this.targetedService = targetedService;
        System.out.println("Je set le service avec: " + targetedService );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public String getJsp() {
        return jsp;
    }

    public void setJsp(String jsp) {
        this.jsp = jsp;
    }

    public String getRedirectedJsp() {
        return redirectedJsp;
    }

    public void setRedirectedJsp(String redirectedJsp) {
        this.redirectedJsp = redirectedJsp;
    }

    public boolean isDefaultRoute() {
        return defaultRoute;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setRequiredConnection(boolean requiredConnection) {
        this.requiredConnection = requiredConnection;
    }

    public boolean isRequiredConnection() {

        return requiredConnection;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id='" + id + '\'' +
                ", defaultRoute=" + defaultRoute +
                ", requiredConnection=" + requiredConnection +
                ", url='" + url + '\'' +
                ", targetedService='" + targetedService + '\'' +
                ", targetedMethod='" + targetedMethod + '\'' +
                ", method=" + method +
                ", parameters=" + parameters +
                ", jsp='" + jsp + '\'' +
                ", redirectedJsp='" + redirectedJsp + '\'' +
                '}';
    }

}
