package Requests.objects;

import Interfaces.EmailGateway;
import Interfaces.Request;
import Requests.PluginFactory;
import Responses.Response;
import Storage.MysqlJDBC;
import Users.User;
import Util.StringHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class SignUpRequest extends AbstractRequest implements Request
{
    private static final ObjectMapper mapper = new ObjectMapper();

    public SignUpRequest(HttpServletRequest request)
    {
        super(request);
    }

    @Override
    public Response call()
    {
        try {
            User user = mapper.readValue(this.getRequest().getReader(), User.class);
            user.setHashedPassword(StringHelper.sha256(user.hashedPassword));

            // send email (returns generated token)
            EmailGateway gateway = (EmailGateway) PluginFactory.getEmailPlugin();
            String token = Objects.requireNonNull(gateway).sendVerificationEmail(user.emailAddress);

            // store unverified user
            user.setToken(token);
            MysqlJDBC.getInstance().insertUser(user);

            return new Response().created();
        } catch (Exception e) {
            return new Response().badRequest().exceptionBody(e);
        }
    }

}
