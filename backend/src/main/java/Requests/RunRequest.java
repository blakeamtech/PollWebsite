package Requests;

import Exceptions.InvalidPollStateException;
import Responses.Response;
import Users.PollManager;

import javax.servlet.http.HttpServletRequest;

public class RunRequest extends AbstractRequest implements Request {

    RunRequest(HttpServletRequest request){
        super(request);
    };

    @Override
    public Response call() {
        try {
            PollManager.runPoll();
            return new Response().ok();
        } catch (InvalidPollStateException e) {
            return new Response().badRequest();
        }
    }

}
