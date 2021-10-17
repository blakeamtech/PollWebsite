package Requests;

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
        } catch (InvalidPollStateException e) {
            return new Response().serverError().body(e.getMessage());
        }
    }
}
