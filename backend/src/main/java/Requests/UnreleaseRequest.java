package Requests;

import Responses.Response;
import Users.PollManager;

public class UnreleaseRequest implements IRequest{

    UnreleaseRequest(){};

    @Override
    public Response call() {
        PollManager.unreleasePoll();
        return new Response().ok();
    }
}
