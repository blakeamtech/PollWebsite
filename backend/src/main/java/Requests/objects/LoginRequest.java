package Requests.objects;

import Polls.Poll;
import Responses.Response;
import Storage.MysqlJDBC;
import Users.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.Reader;
import java.security.MessageDigest;

public class LoginRequest extends AbstractRequest implements Request {

    private static final ObjectMapper mapper = new ObjectMapper();

    public LoginRequest(HttpServletRequest request){
        super(request);
    }

    public static String sha256(final String base) {
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    /**
     * Implementation of the Run request. Sets the poll status to running.
     * @return Response object containing body and status request.
     */
    @Override
    public Response call() {
        try {
            User user = mapper.readValue(this.getRequest().getReader(), User.class);

            String pass = sha256(user.hashedPassword);
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
