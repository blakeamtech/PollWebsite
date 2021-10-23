package Requests;

import Exceptions.AssignmentException;
import Exceptions.InvalidPollStateException;
import Responses.Response;
import Users.PollManager;

public class ReleaseRequest implements Request {

    ReleaseRequest(){};

    /**
     * Implementation of the Release request. Sets the poll status to released
     * @return Response object containing body and status request.
     */
    @Override
    public Response call() {
        try {
            PollManager.releasePoll();
            return new Response().ok();
        } catch (AssignmentException e) {
            return new Response().serverError().exceptionBody(e);
        }
    }
}
