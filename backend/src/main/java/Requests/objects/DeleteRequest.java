package Requests.objects;

import Responses.Response;
import Users.PollManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class DeleteRequest extends AbstractRequest implements Request {

    public DeleteRequest(HttpServletRequest request){
        super(request);
    }

    /**
     * Implementation of the clear request. Clears the poll
     * @return Response object containing body and status request.
     */
    @Override
    public Response call() {
        try {
            PollManager.deletePoll(this.getPollId());
            return new Response().ok();
        } catch (SQLException | ClassNotFoundException e) {
            return new Response().badRequest().exceptionBody(e);
        }
    }
}
