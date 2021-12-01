package Requests.objects;

import Responses.Response;
import Storage.Config;
import Storage.MysqlJDBC;
import Users.User;
import Util.Email;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.util.concurrent.ThreadLocalRandom;

public class SignUpRequest extends AbstractRequest implements Request
{
    private static final ObjectMapper mapper = new ObjectMapper();

    public SignUpRequest(HttpServletRequest request)
    {
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
    public Response call()
    {
        try {
            User user = mapper.readValue(this.getRequest().getReader(), User.class);
            user.setHashedPassword(sha256(user.hashedPassword));
            String token = getToken();
            user.setToken(token);

            MysqlJDBC.getInstance().insertUser(user);
            Email email = new Email(user.emailAddress, "Verify Your Email", "Verification", token);
            email.send();

            return new Response().ok();
        } catch (Exception e) {
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
