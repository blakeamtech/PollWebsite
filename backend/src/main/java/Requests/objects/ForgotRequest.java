package Requests.objects;

import Responses.Response;
import Storage.Config;
import Storage.MysqlJDBC;
import Users.User;
import Util.Email;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

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
            String token = getToken();
            found.setToken(token);

            MysqlJDBC.getInstance().updateUser(found);

            Email email = new Email(user.emailAddress, "Change your Password", "Forgot", token);
            email.send();

            return new Response().ok();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            return new Response().badRequest().exceptionBody(e);
        }
    }

    private String getToken()
    {
        StringBuilder str = new StringBuilder();
        String allowedChars = Config.ID_ALLOWED_CHARACTERS.value.toString();
        for(int i = 0 ; i < 16; i++){
            int index = ThreadLocalRandom.current().nextInt(allowedChars.length());
            str.append(allowedChars.charAt(index));
        }

        return str.toString();
    }
}
