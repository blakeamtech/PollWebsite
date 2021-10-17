package Requests;

import Responses.Response;

import javax.servlet.http.HttpServletRequest;

public class CloseRequest extends AbstractRequest implements Request {

    CloseRequest(HttpServletRequest request){
        super(request);
    };

    @Override
    public Response call() {
        return null;
    }
}
