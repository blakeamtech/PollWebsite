package Requests.objects;

import Interfaces.Request;
import Responses.Response;
import Storage.MysqlJDBC;
import Users.User;
import Util.StringHelper;
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

    @Override
    public Response call() {
        try {
            User user = mapper.readValue(this.getRequest().getReader(), User.class);

            if (!user.emailAddress.equals("")) {
                User found = MysqlJDBC.getInstance().selectUserFromEmail(user.emailAddress);
                found.setHashedPassword(StringHelper.sha256(user.hashedPassword));

                MysqlJDBC.getInstance().updateUser(found);
            }
            else
            {
                User found = MysqlJDBC.getInstance().selectUserFromToken(user.token);
                found.setHashedPassword(StringHelper.sha256(user.hashedPassword));

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
