package Requests.objects;

import Exceptions.AssignmentException;
import Interfaces.Request;
import Responses.Response;
import Users.PollManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class UnreleaseRequest extends AbstractRequest implements Request {

    public UnreleaseRequest(HttpServletRequest request){
        super(request);
    };

    /**
     * Implementation of the Unrelease request. Unreleases the poll
     * @return Response object containing body and status request.
     */
    @Override
    public Response call() {
        try {
            PollManager.unreleasePoll(this.getPollId());
            return new Response().ok();
        } catch (AssignmentException | SQLException | ClassNotFoundException e) {
            return new Response().badRequest().exceptionBody(e);
        }
    }
}
