package Requests;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public abstract class AbstractRequest {

    private final HttpSession httpSession;
    private final HttpServletRequest request;

    AbstractRequest(HttpServletRequest request){
        this.httpSession = request.getSession();
        this.request = request;
    }

    public Optional<HttpSession> getHttpSession(){
        return Optional.of(this.httpSession);
    }

    public HttpServletRequest getRequest(){
        return this.request;
    }
}
