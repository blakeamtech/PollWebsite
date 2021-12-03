package Requests.objects;

import Interfaces.Request;
import Responses.Response;
import Users.PollManager;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Map;


/**
 * The results request can be called by everyone, therefore it doesn't need to check for permissions
 */
public class ResultsRequest extends AbstractRequest implements Request {

    public ResultsRequest(HttpServletRequest request){
        super(request);
    };

    /**
     * Implementation of the Results request. Returns the results of the poll
     * @return Response object containing body and status request.
     */
    @Override
    public Response call() {

        Map<String, Long> results = null;
        try {

            results = PollManager.getPollResults(this.getPollId());
            JSONObject object = new JSONObject(results);
            return new Response()
                    .body(object)
                    .ok()
                    .addHeader("Content-Type", "application/json");
        } catch (SQLException | ClassNotFoundException throwables) {
            return new Response().badRequest();
        }

    }
}

