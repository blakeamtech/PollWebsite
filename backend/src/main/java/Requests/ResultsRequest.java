package Requests;

import Responses.Response;
import Users.PollManager;
import org.json.JSONObject;

import java.util.Hashtable;


/**
 * The results request can be called by everyone, therefore it doesn't need to check for permissions
 */
public class ResultsRequest implements Request {

    ResultsRequest(){};

    @Override
    public Response call() {
        Hashtable<String, Integer> results = PollManager.getPollResults();

        JSONObject object = new JSONObject(results);
        return new Response()
                .body(object)
                .ok()
                .addHeader("Content-Type", "application/json");
    }
}

