package Requests.objects;

import Responses.Response;
import Storage.MysqlJDBC;

import javax.servlet.http.HttpServletRequest;

public class TokenRequest extends AbstractRequest implements Request
{
    public TokenRequest(HttpServletRequest request)
    {
        super(request);
    }

    @Override
    public Response call() {
        try {
            String type = this.getRequest().getParameter("type");

            if (type.equals("verify") || type.equals("forgot"))
            {
                String token = this.getRequest().getParameter("token");
                MysqlJDBC.getInstance().updateUserToken(token);
            }

            return new Response().ok();
        } catch (Exception e) {
            return new Response().badRequest().exceptionBody(e);
        }
    }
}
