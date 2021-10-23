package Requests;

import Exceptions.AssignmentException;
import Exceptions.InvalidPollStateException;
import Responses.Response;
import Users.PollManager;

import javax.servlet.http.HttpServletRequest;

public class RunRequest extends AbstractRequest implements Request {

    RunRequest(HttpServletRequest request){
        super(request);
    };

    /**
     * Implementation of the Run request. Sets the poll status to running.
     * @return Response object containing body and status request.
     */
    @Override
    public Response call() {
        try {
            PollManager.runPoll();
            return new Response().ok();
        } catch (AssignmentException e) {
            return new Response().badRequest().exceptionBody(e);
        }
    }

}
