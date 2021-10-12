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

    public Response setStatusCode(int statusCode){
        this.statusCode = statusCode;
        return this;
    }

    public Response setBody(JSONObject body){
        this.body = body;
        return this;
    }

    public Optional<String> getBody(){
        return Optional.of(this.body.toString());
    }

    public Optional<Integer> getStatusCode(){
        return Optional.of(this.statusCode);
    }
}