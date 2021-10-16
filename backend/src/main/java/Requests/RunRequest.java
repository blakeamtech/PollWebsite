package Requests;

import Responses.Response;
import Users.PollManager;

public class RunRequest implements Request {

    RunRequest(){};

    @Override
    public Response call() {
        PollManager.runPoll();
        return new Response().ok();
    }

}
