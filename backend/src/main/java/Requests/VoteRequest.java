package Requests;

import Exceptions.AssignmentException;
import Exceptions.InvalidChoiceException;
import Exceptions.InvalidSessionException;
import Responses.Response;
import Users.PollManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class VoteRequest extends AbstractRequest implements Request {

    VoteRequest(HttpServletRequest servletRequest){
        super(servletRequest);
    };

    /**
     * Implementation of the Vote request. Submits a vote to the poll
     * @return Response object containing body and status request.
     */
    @Override
    public Response call() {
        try {
            HttpSession session = this.getHttpSession().orElseThrow(InvalidSessionException::new);

            String choice = this.getRequest().getParameter("choice");

            PollManager.vote(session, choice);

            return new Response().ok();

        } catch (AssignmentException e) {
            return new Response().serverError().exceptionBody(e);
        }
    }
}

