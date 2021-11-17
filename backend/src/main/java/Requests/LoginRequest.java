package Requests;

import Responses.Response;

import javax.servlet.http.HttpServletRequest;

public class LoginRequest extends AbstractRequest implements Request {

    LoginRequest(HttpServletRequest request){
        super(request);
    }

    /**
     * Implementation of the Run request. Sets the poll status to running.
     * @return Response object containing body and status request.
     */
    @Override
    public Response call() {
        try {
            /** IMPLEMENTATION:
             *
             * Endpoint: http://localhost:8080/authenticate
             *
             * - See if username and password match the credentials.
             *
             * INPUT BODY:
             *  - E.g.: { "username": "helloworl123", "password": "123" }
             *
             * OUTPUT:
             *  - If success: 200 ok
             *  - Else: 401 unauthorized
             */

            return new Response().ok();
        } catch (Exception e) {
            return new Response().badRequest().exceptionBody(e);
        }
    }

}
