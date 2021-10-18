package Requests;

import Exceptions.InvalidPollStateException;
import Responses.Response;
import Users.PollManager;

public class UnreleaseRequest implements Request {

    UnreleaseRequest(){};

    @Override
    public Response call() {
        try {
            PollManager.unreleasePoll();
            return new Response().ok();
        } catch (InvalidPollStateException e) {
            return new Response().badRequest().exceptionBody(e);
        }
    }
}
