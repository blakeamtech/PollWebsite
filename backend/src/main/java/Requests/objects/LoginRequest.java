package Requests.objects;

import Interfaces.Request;
import Responses.Response;
import Storage.MysqlJDBC;
import Users.User;
import Util.StringHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;

public class LoginRequest extends AbstractRequest implements Request {

    private static final ObjectMapper mapper = new ObjectMapper();

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
            User user = mapper.readValue(this.getRequest().getReader(), User.class);

            String pass = StringHelper.sha256(user.hashedPassword);
            User foundUser = MysqlJDBC.getInstance().selectUserFromEmail(user.emailAddress);
            if (foundUser.hashedPassword.equals(pass) && foundUser.verified) {
                return new Response().ok();
            }
            else {
                throw new Exception("Make sure your account is verified!");
            }
        } catch (Exception e) {
            return new Response().unauthorized().exceptionBody(e);
        }
    }



}
