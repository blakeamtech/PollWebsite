package Requests.objects;

import Responses.Response;
import Users.PollManager;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class GetStateRequest extends AbstractRequest implements Request{

    public GetStateRequest(HttpServletRequest request) {
        super(request);
    }

    /**
     * Implementation of the Create request. Creates the poll
     * In this case, the Poll always has a state, therefore the Call method is straight forward
     * @return Response object containing body and status request.
     */
    @Override
    public Response call() {
        try{
            return new Response().ok().body(new JSONObject(PollManager.getState(this.getPollId())));
        } catch (SQLException | ClassNotFoundException e) {
            return new Response().badRequest();
        }

    }
}
