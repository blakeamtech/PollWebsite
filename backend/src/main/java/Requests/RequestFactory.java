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

    public static Request valueOfGetRequest(HttpServletRequest request){
        REQUEST_TYPE requestType = getRequestTypeFromServletRequest(request);
        switch (requestType){
            case RESULTS:
                return new ResultsRequest();
            case DETAILS:
                return new DetailsRequest(request);
            default:
                return new InvalidRequest();
        }
    }

    public static Request valueOfPostRequest(HttpServletRequest request){
        REQUEST_TYPE requestType = getRequestTypeFromServletRequest(request);
        switch (requestType) {
            case VOTE:
                return new VoteRequest(request);
            case DETAILS:
                return new DetailsRequest(request);
            case CREATE:
                return new CreateRequest(request);
            default:
                return new InvalidRequest();
        }
    }

    public static Request valueOfPutRequest(HttpServletRequest request){
        REQUEST_TYPE requestType = getRequestTypeFromServletRequest(request);
        switch (requestType){
            case RELEASE:
                return new ReleaseRequest();
            case UNRELEASE:
                return new UnreleaseRequest();
            case CLEAR:
                return new ClearRequest(request);
            case CLOSE:
                return new CloseRequest(request);
            case UPDATE:
                return new UpdateRequest(request);
            case RUN:
                return new RunRequest(request);
            default:
                return new InvalidRequest();
        }
    }

    public static Request valueOfDeleteRequest(HttpServletRequest request){
        return new InvalidRequest(400);
    }

    public static REQUEST_TYPE getRequestTypeFromServletRequest(HttpServletRequest request){
        return REQUEST_TYPE.valueOf(request.getServletPath().replace("/", "").strip().trim().toUpperCase());
    }
}
