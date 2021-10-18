package Requests;

import Exceptions.InvalidPollStateException;
import Responses.Response;
import Users.PollManager;

import javax.servlet.http.HttpServletRequest;

public class CloseRequest extends AbstractRequest implements Request {

    CloseRequest(HttpServletRequest request){
        super(request);
    };

    @Override
    public Response call() {

        try {

            PollManager.closePoll();

            return new Response().ok();

        } catch (InvalidPollStateException e) {

            return new Response().serverError().exceptionBody(e);
        }

    }
}
