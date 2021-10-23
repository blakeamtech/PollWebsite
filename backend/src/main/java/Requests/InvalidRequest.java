package Requests;

import Responses.Response;

public class InvalidRequest implements Request {

    private int statusCode;

    InvalidRequest(){};

    // If we want to pass a status code when creating an invalid request
    InvalidRequest(int statusCode){
        this.statusCode = statusCode;
    }

    /**
     * Implementation of the Invalid request. This is a base invalid request, used when the request doesn't match out format.
     * @return Response object containing body and status request.
     */
    @Override
    public Response call() {
        return new Response()
                .setStatusCode(this.statusCode);
    }
}
