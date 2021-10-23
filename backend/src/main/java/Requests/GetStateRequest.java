package Requests;

import Responses.Response;
import Users.PollManager;
import org.json.JSONObject;

public class GetStateRequest implements Request{

    /**
     * Implementation of the Create request. Creates the poll
     * In this case, the Poll always has a state, therefore the Call method is straight forward
     * @return Response object containing body and status request.
     */
    @Override
    public Response call() {
        return new Response().ok().body(new JSONObject(PollManager.getState()));
    }
}
