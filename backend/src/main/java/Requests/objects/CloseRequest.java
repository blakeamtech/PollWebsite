package Requests.objects;

import Exceptions.AssignmentException;
import Responses.Response;
import Users.PollManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class CloseRequest extends AbstractRequest implements Request {

    public CloseRequest(HttpServletRequest request){
        super(request);
    };


    /**
     * Implementation of the Close request. Closes the poll
     * @return Response object containing body and status request.
     */
    @Override
    public Response call() {

        try {

            PollManager.closePoll(this.getPollId());

            return new Response().ok();

        } catch (AssignmentException | SQLException | ClassNotFoundException e) {

            return new Response().serverError().exceptionBody(e);
        }

    }
}
