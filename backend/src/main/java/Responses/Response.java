package Responses;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The response class is used as an implementation of the Http Response
 * This class contains methods which can be used with method chaining to build the response
 * We found that in this case this was a better implementation than using a builder.
 */
public class Response {

    private int statusCode;     // response status code
    private String body = "";   // response body, default empty
    private final Map<String, String> headers = new HashMap<>(); //extra headers

    // empty base constructor
    public Response(){};

    public Response(int statusCode, JSONObject body){
        this.statusCode = statusCode;
        this.body = body.toString();
    }

    public Response setStatusCode(int statusCode){
        this.statusCode = statusCode;
        return this;
    }

    public void setBody(Object body){
        this.body = body.toString();
    }

    public Response body(JSONObject body){
        this.body = body.toString();
        return this;
    }

    public Response exceptionBody(Exception givenException){
        this.body = new JSONObject().put("exception", givenException.getMessage()).toString();
        return this;
    }

    public Response body(String givenString){
        this.body = new JSONObject(givenString).toString();
        return this;
    }

    public Response ok(){
        this.statusCode = 200;
        return this;
    }

    public Response created() {
        this.statusCode = 201;
        return this;
    }

    public Response badRequest(){
        this.statusCode = 400;
        return this;
    }

    public Response unauthorized(){
        this.statusCode = 401;
        return this;
    }

    public Response forbidden(){
        this.statusCode = 403;
        return this;
    }

    public Response notFound(){
        this.statusCode = 404;
        return this;
    }

    public Response serverError(){
        this.statusCode = 500;
        return this;
    }

    public Response addHeader(String header, String headerValue){
        this.headers.put(header, headerValue);
        return this;
    }

    public Map<String, String> getHeaders(){
        return this.headers;
    }

    public Optional<String> getBody(){
        return Optional.of(this.body);
    }

    public Optional<Integer> getStatusCode(){
        return Optional.of(this.statusCode);
    }
}