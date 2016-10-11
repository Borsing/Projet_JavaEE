package servlet.utils;

import modules.ControllerInterface;

import java.util.Map;

/**
 * Created by adric on 11/10/2016.
 */
public class ParsedRequest {

    private ControllerInterface targetedController;
    private Map<String,String> parameters;

    public ParsedRequest(ControllerInterface targetedController, Map<String, String> parameters) {
        this.targetedController = targetedController;
        this.parameters = parameters;
    }

    public ControllerInterface getTargetedController() {
        return targetedController;
    }

    public void setTargetedController(ControllerInterface targetedController) {
        this.targetedController = targetedController;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "ParsedRequest{" +
                "targetedController=" + targetedController +
                ", parameters=" + parameters +
                '}';
    }
}
