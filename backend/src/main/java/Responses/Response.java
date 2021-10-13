package Responses;

import org.json.JSONObject;

import java.util.Optional;

public class Response {

    private int statusCode;
    private JSONObject body;

    public Response(int statusCode, JSONObject body){
        this.statusCode = statusCode;
        this.body = body;
    }

    public void setStatusCode(int statusCode){
        this.statusCode = statusCode;
    }

    public void setBody(JSONObject body){
        this.body = body;
    }

    public Response body(JSONObject body){
        this.body = body;
        return this;
    }

    public Response ok(){
        this.statusCode = 200;
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

    public Optional<String> getBody(){
        return Optional.of(this.body.toString());
    }

    public Optional<Integer> getStatusCode(){
        return Optional.of(this.statusCode);
    }
}