package Requests;

import Responses.Response;
import Users.PollManager;

public class RunRequest implements IRequest{

    RunRequest(){};

    @Override
    public Response call() {
        PollManager.runPoll();
        return new Response().ok();
    }

}
