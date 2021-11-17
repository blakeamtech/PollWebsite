package Requests;

import Responses.Response;

import javax.servlet.http.HttpServletRequest;

public class GetPollsRequest extends AbstractRequest implements Request {

    GetPollsRequest(HttpServletRequest request){
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
             * Endpoint: http://localhost:8080/polls?username=${username}
             *
             * - See if user associated with "username" has any polls
             *  - If Yes:
             *      - return all their polls (in format like below)
             *  - If No:
             *      - return same format as below but with empty array ("polls":[])
             *
             * INPUT:
             *  - Username in query string
             *
             * OUTPUT BODY:
             *  - Format: one attribute "polls" which is an array of objects. The objects are of format {pollid : question}
             *
             *  E.g.: return data should look like:
             *     {
             *         "polls": [
             *             ["1234567890", "Yes or No?"],
             *             ["827364AIZS", "Where is Kappa?"]
             *         ]
             *     }
             */

            return new Response().ok();
        } catch (Exception e) {
            return new Response().badRequest().exceptionBody(e);
        }
    }

}
