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
        if(PollManager.runPoll()){
            return new Response().ok();
        }
        return new Response().badRequest();
    }

}
