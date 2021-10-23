package Requests;

import Exceptions.InvalidPollStateException;
import Responses.Response;
import Users.PollManager;

import javax.servlet.http.HttpServletRequest;

public class ClearRequest extends AbstractRequest implements Request {

    ClearRequest(HttpServletRequest request){
        super(request);
    }

    /**
     * Implementation of the clear request. Clears the poll
     * @return Response object containing body and status request.
     */
    @Override
    public Response call() {
        try {
            PollManager.clearPoll();
            return new Response().ok();
        } catch (InvalidPollStateException e) {

            return new Response().badRequest().exceptionBody(e);
        }
    }
}
