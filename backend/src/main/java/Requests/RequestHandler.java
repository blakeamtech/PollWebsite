package Requests;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHandler {

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

    public static void handleGet(HttpServletRequest request, HttpServletResponse response){
        REQUEST_TYPE requestType = getRequestTypeFromServletRequest(request);
        switch (requestType){
            case RESULTS:

        }
    }

    public static void handlePost(){

    }

    public static void handlePut(){

    }

    private static REQUEST_TYPE getRequestTypeFromServletRequest(HttpServletRequest request){
        return REQUEST_TYPE.valueOf(request.getServletPath().replace("/", "").strip().trim().toUpperCase());
    }
}
