package Requests.objects;

import Exceptions.AssignmentException;
import Exceptions.InvalidSessionException;
import Responses.Response;
import Users.PollManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class VoteRequest extends AbstractRequest implements Request {

    public VoteRequest(HttpServletRequest servletRequest){
        super(servletRequest);
    };

    /**
     * Implementation of the Vote request. Submits a vote to the poll
     * @return Response object containing body and status request.
     */
    @Override
    public Response call() {
        /**
         * TODO:
         * - Must vote for a specific given poll id.
         *
         * INPUT: query strings "choice", "id", "pin" from endpoint:
         *  - http://localhost:8080/vote?choice=${answer}&id=${props.id}&pin=${props.pin}
         *
         */
        try {
            HttpSession session = this.getHttpSession().orElseThrow(InvalidSessionException::new);
            String choice = this.getRequest().getParameter("choice");
            String choiceId = this.getRequest().getParameter("choiceId");
            String pin = this.getRequest().getParameter("pin");
            PollManager.vote(choiceId, choice, pin, getPollId());
            return new Response().ok();

        } catch (AssignmentException | SQLException | ClassNotFoundException e) {
            return new Response().serverError().exceptionBody(e);
        }
    }
}

