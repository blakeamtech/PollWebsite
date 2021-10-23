package Requests;

import Exceptions.AssignmentException;
import Exceptions.InvalidPollStateException;
import Responses.Response;
import Users.PollManager;

import javax.servlet.http.HttpServletRequest;

public class CloseRequest extends AbstractRequest implements Request {

    CloseRequest(HttpServletRequest request){
        super(request);
    };


    /**
     * Implementation of the Close request. Closes the poll
     * @return Response object containing body and status request.
     */
    @Override
    public Response call() {

        try {

            PollManager.closePoll();

            return new Response().ok();

        } catch (AssignmentException e) {

            return new Response().serverError().exceptionBody(e);
        }

    }
}
