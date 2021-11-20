package Requests.objects;

import Exceptions.AssignmentException;
import Responses.Response;
import Users.PollManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class RunRequest extends AbstractRequest implements Request {

    public RunRequest(HttpServletRequest request){
        super(request);
    };

    /**
     * Implementation of the Run request. Sets the poll status to running.
     * @return Response object containing body and status request.
     */
    @Override
    public Response call() {
        try {
            PollManager.runPoll(this.getPollId());
            return new Response().ok();
        } catch (AssignmentException | SQLException | ClassNotFoundException e) {
            return new Response().badRequest().exceptionBody(e);
        }
    }

}
