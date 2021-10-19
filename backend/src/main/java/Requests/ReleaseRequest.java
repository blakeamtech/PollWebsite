package Requests;

import Exceptions.AssignmentException;
import Exceptions.InvalidPollStateException;
import Responses.Response;
import Users.PollManager;

public class ReleaseRequest implements Request {

    ReleaseRequest(){};

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
