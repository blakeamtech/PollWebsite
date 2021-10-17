package Requests;

import Responses.Response;

public class InvalidRequest implements Request {

    private int statusCode;

    InvalidRequest(){};

    // If we want to pass a status code when creating an invalid request
    InvalidRequest(int statusCode){
        this.statusCode = statusCode;
    }

    @Override
    public Response call() {
        return new Response()
                .setStatusCode(this.statusCode);
    }
}
