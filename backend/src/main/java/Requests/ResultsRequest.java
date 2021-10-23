package Requests;

import Responses.Response;
import Users.PollManager;
import org.json.JSONObject;

import java.util.Map;


/**
 * The results request can be called by everyone, therefore it doesn't need to check for permissions
 */
public class ResultsRequest implements Request {

    ResultsRequest(){};

    /**
     * Implementation of the Results request. Returns the results of the poll
     * @return Response object containing body and status request.
     */
    @Override
    public Response call() {
        Map<String, String> results = PollManager.getPollResults();

        JSONObject object = new JSONObject(results);
        return new Response()
                .body(object)
                .ok()
                .addHeader("Content-Type", "application/json");
    }
}

