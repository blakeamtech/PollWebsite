package Requests;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The abstract request contains code that will be re-used by request implementations
 * Namely: the constructor which takes in a HttpServletRequest, the HttpSession getter, and the HttpServletRequest getter
 */
public abstract class AbstractRequest {

    private final HttpSession httpSession;
    private final HttpServletRequest request;

    AbstractRequest(HttpServletRequest request){
        this.httpSession = request.getSession();
        this.request = request;
    }

    public Optional<HttpSession> getHttpSession(){
        if(this.httpSession == null){
            return Optional.empty();
        }

        return Optional.of(this.httpSession);
    }

    public HttpServletRequest getRequest(){
        return this.request;
    }
}
