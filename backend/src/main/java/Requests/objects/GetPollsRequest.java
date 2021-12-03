package Requests.objects;

import Interfaces.Request;
import Responses.Response;
import Users.PollManager;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

public class GetPollsRequest extends AbstractRequest implements Request {

    public GetPollsRequest(HttpServletRequest request){
        super(request);
    }

    /**
     * Implementation of the Run request. Sets the poll status to running.
     * @return Response object containing body and status request.
     */
    @Override
    public Response call() {
        try {
            String email = getRequest().getParameter("email");

            JSONObject body = PollManager.getAllPolls(email);

            return new Response().ok().body(body);
        } catch (Exception e) {
            return new Response().notFound().exceptionBody(e);
        }
    }

}
