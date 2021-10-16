package Requests;

import Responses.Response;
import Users.PollManager;
import org.json.JSONObject;

import java.util.Hashtable;

public class ResultsRequest implements Request {

    ResultsRequest(){};

    @Override
    public Response call() {
        Hashtable<String, Integer> results = PollManager.getPollResults();
        JSONObject object = new JSONObject(results);
        return new Response()
                .body(object)
                .ok();
    }
}

