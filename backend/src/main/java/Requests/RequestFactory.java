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

    public static IRequest valueOfGetRequest(HttpServletRequest request){
        REQUEST_TYPE requestType = getRequestTypeFromServletRequest(request);
        switch (requestType){
            case RESULTS:
                return new ResultsRequest();
            case DETAILS:
                return new DetailsRequest();
            default:
                return new InvalidRequest();
        }
    }

    public static IRequest valueOfPostRequest(HttpServletRequest request){
        REQUEST_TYPE requestType = getRequestTypeFromServletRequest(request);
        switch (requestType) {
            case VOTE:
                return new VoteRequest();
            case CREATE:
                return new CreateRequest(request);
            default:
                return new InvalidRequest();
        }
    }

    public static IRequest valueOfPutRequest(HttpServletRequest request){
        REQUEST_TYPE requestType = getRequestTypeFromServletRequest(request);
        switch (requestType){
            case RELEASE:
                return new ReleaseRequest();
            case UNRELEASE:
                return new UnreleaseRequest();
            case CLEAR:
                return new ClearRequest();
            case CLOSE:
                return new CloseRequest();
            case UPDATE:
                return new UpdateRequest();
            case RUN:
                return new RunRequest();
            default:
                return new InvalidRequest();
        }
    }

    public static IRequest valueOfDeleteRequest(HttpServletRequest request){
        return new InvalidRequest(400);
    }

    public static REQUEST_TYPE getRequestTypeFromServletRequest(HttpServletRequest request){
        return REQUEST_TYPE.valueOf(request.getServletPath().replace("/", "").strip().trim().toUpperCase());
    }
}
