package Requests.objects;

import Responses.Response;
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

            return new Response().ok();
        } catch (Exception e) {
            return new Response().badRequest().exceptionBody(e);
        }
    }
}
