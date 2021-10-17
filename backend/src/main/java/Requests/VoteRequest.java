package Requests;

import Exceptions.InvalidChoiceException;
import Exceptions.InvalidPermissionException;
import Exceptions.InvalidSessionException;
import Responses.Response;
import Users.PollManager;
import Util.SessionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class VoteRequest extends AbstractRequest implements Request {

    VoteRequest(HttpServletRequest servletRequest){
        super(servletRequest);
    };

    @Override
    public Response call() {
        try {
            HttpSession session = this.getHttpSession().orElseThrow(InvalidSessionException::new);

            if(SessionManager.isPollManager(session)){
                throw new InvalidPermissionException();
            }

            String choice = this.getRequest().getParameter("choice");

            if(choice.isBlank() || choice.isEmpty() || !PollManager.validateChoice(choice))
                throw new InvalidChoiceException();

            PollManager.vote(session, choice);

            return new Response().ok();

        } catch (InvalidSessionException | InvalidChoiceException | InvalidPermissionException e) {
            return new Response().serverError();
        }
    }
}

