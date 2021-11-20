package Requests.objects;

import Responses.Response;
import Storage.MysqlJDBC;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

public class LoginRequest extends AbstractRequest implements Request {

    public LoginRequest(HttpServletRequest request){
        super(request);
    }

    /**
     * Implementation of the Run request. Sets the poll status to running.
     * @return Response object containing body and status request.
     */
    @Override
    public Response call() {
        try {

            JSONObject object = new JSONObject(getRequest().getReader());

            MysqlJDBC.getInstance().selectUserFromEmail(object.getString("email"));

            return new Response().ok();
        } catch (Exception e) {
            return new Response().badRequest().exceptionBody(e);
        }
    }

}
