package Requests.objects;

import Exceptions.InvalidPollStateException;
import Interfaces.Request;
import Responses.Response;
import Users.PollManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ClearRequest extends AbstractRequest implements Request {

    public ClearRequest(HttpServletRequest request){
        super(request);
    }

    /**
     * Implementation of the clear request. Clears the poll
     * @return Response object containing body and status request.
     */
    @Override
    public Response call() {
        try {
            PollManager.clearPoll(this.getPollId());
            return new Response().ok();
        } catch (InvalidPollStateException | SQLException | ClassNotFoundException e) {

            return new Response().badRequest().exceptionBody(e);
        }
    }
}
