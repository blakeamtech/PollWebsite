package Requests;

import javax.servlet.http.HttpServletRequest;

public class RequestFactory {
    public enum REQUEST_TYPE{
        RESULTS,
        DETAILS,
        VOTE,
        RELEASE,
        UNRELEASE,
        CLEAR,
        CLOSE,
        CREATE,
        UPDATE,
        RUN
    }

    public static IRequest from(HttpServletRequest request){
        REQUEST_TYPE requestType =  getRequestTypeFromServletRequest(request);

        switch (requestType){
            case RUN:
                return new RunRequest();
            case VOTE:
            case CLEAR:
            case CLOSE:
            case CREATE:
            case DETAILS:
            case UPDATE:
            case RELEASE:
            case RESULTS:
            case UNRELEASE:
            default:
                return new InvalidRequest();
        }

    }

    private static REQUEST_TYPE getRequestTypeFromServletRequest(HttpServletRequest request){
        return REQUEST_TYPE.valueOf(request.getServletPath().replace("/", "").strip().trim().toUpperCase());
    }
}
