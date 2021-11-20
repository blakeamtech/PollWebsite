package Requests.objects;

import Exceptions.AssignmentException;
import Responses.Response;
import Users.PollManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ReleaseRequest extends AbstractRequest implements Request {

    public ReleaseRequest(HttpServletRequest request){
        super(request);
    };

    /**
     * Implementation of the Release request. Sets the poll status to released
     * @return Response object containing body and status request.
     */
    @Override
    public Response call() {
        try {
            PollManager.releasePoll(getPollId());
            return new Response().ok();
        } catch (AssignmentException | SQLException | ClassNotFoundException e) {
            return new Response().serverError().exceptionBody(e);
        }
    }
}
