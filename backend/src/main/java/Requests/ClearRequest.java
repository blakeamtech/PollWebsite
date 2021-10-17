package Requests;

import Responses.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ClearRequest extends AbstractRequest implements Request {

    ClearRequest(HttpServletRequest request){
        super(request);
    }

    @Override
    public Response call() {
        return null;
    }
}
