package Requests.objects;

import Responses.Response;
import Storage.MysqlJDBC;
import Users.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.MessageDigest;
import java.sql.SQLException;

public class ChangePasswordRequest  extends AbstractRequest implements Request
{
    private static final ObjectMapper mapper = new ObjectMapper();

    public ChangePasswordRequest(HttpServletRequest request) {
        super(request);
    }

    public static String sha256(final String base)
    {
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
        }
        catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Response call() {
        try {
            User user = mapper.readValue(this.getRequest().getReader(), User.class);

            if (!user.emailAddress.equals("")) {
                User found = MysqlJDBC.getInstance().selectUserFromEmail(user.emailAddress);
                found.setHashedPassword(sha256(user.hashedPassword));

                MysqlJDBC.getInstance().updateUser(found);
            }
            else
            {
                User found = MysqlJDBC.getInstance().selectUserFromToken(user.token);
                found.setHashedPassword(sha256(user.hashedPassword));

                MysqlJDBC.getInstance().updateUser(found);
            }

            return new Response().ok();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            Response resp = new Response();
            resp.setBody(e.getMessage());
            return resp.badRequest().exceptionBody(e);
        }
    }
}
