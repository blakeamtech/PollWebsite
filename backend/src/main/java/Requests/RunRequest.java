package Requests;

import Responses.Response;
import Users.PollManager;

import javax.servlet.http.HttpServletRequest;

public class RunRequest extends AbstractRequest implements Request {

    RunRequest(HttpServletRequest request){
        super(request);
    };

    @Override
    public Response call() {
        PollManager.runPoll();
        return new Response().badRequest();
    }

}
