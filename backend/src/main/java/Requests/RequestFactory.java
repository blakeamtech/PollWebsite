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
                return new VoteRequest();
            case CLEAR:
                return new ClearRequest();
            case CLOSE:
                return new CloseRequest();
            case CREATE:
                return new CreateRequest();
            case DETAILS:
                return new DetailsRequest();
            case UPDATE:
                return new UpdateRequest();
            case RELEASE:
                return new ReleaseRequest();
            case RESULTS:
                return new ResultsRequest();
            case UNRELEASE:
                return new UnreleaseRequest();
            default:
                return new InvalidRequest();
        }

    }

    private static REQUEST_TYPE getRequestTypeFromServletRequest(HttpServletRequest request){
        return REQUEST_TYPE.valueOf(request.getServletPath().replace("/", "").strip().trim().toUpperCase());
    }
}
