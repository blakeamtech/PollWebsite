package Requests.objects;

import Interfaces.EmailGateway;
import Interfaces.Request;
import Requests.PluginFactory;
import Responses.Response;
import Storage.MysqlJDBC;
import Users.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class ForgotRequest extends AbstractRequest implements Request
{
    private static final ObjectMapper mapper = new ObjectMapper();
    public ForgotRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Response call() {
        try {
            User user = mapper.readValue(this.getRequest().getReader(), User.class);
            User found = MysqlJDBC.getInstance().selectUserFromEmail(user.emailAddress);

            // send email (factory -> returns plugin (gateway) -> send -> returns generated token)
            EmailGateway gateway = (EmailGateway) PluginFactory.getEmailPlugin();
            String token = Objects.requireNonNull(gateway).sendForgotPasswordEmail(user.emailAddress);

            // update user token
            found.setToken(token);
            MysqlJDBC.getInstance().updateUser(found);

            return new Response().ok();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            return new Response().badRequest().exceptionBody(e);
        }
    }
}
