package routeParser.om;

/**
 * Created by adric on 21/10/2016.
 */
public enum HttpMethod {
    POST("POST"), GET("GET"),DELETE("DELETE");

    String value;

    HttpMethod(String v) {
        value = v;
    }

    public String getValue() {
        return value;
    }
}
