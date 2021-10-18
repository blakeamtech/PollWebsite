package Requests;

import Responses.Response;
import Users.PollManager;
import org.json.JSONObject;

public class GetStateRequest implements Request{
    @Override
    public Response call() {
        return new Response().ok().body(new JSONObject(PollManager.getState()));
    }
}
