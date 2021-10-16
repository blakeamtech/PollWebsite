package Requests;

import javax.servlet.http.HttpSession;

public abstract class PollManagerRequest {

    private final HttpSession httpSession;

    public PollManagerRequest(HttpSession session){
        this.httpSession = session;
    }

    public abstract HttpSession getHttpSession();

}
