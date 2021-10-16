package Requests;

import Responses.Response;

import javax.servlet.http.HttpSession;

public class ClearRequest extends PollManagerRequest implements Request {
    
    ClearRequest(HttpSession session){
        super(session);
   }

    @Override
    public HttpSession getHttpSession() {
        return null;
    }

    ;

    @Override
    public Response call() {
        return null;
    }
}
