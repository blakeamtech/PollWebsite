package Requests;

import Exceptions.InvalidPermissionException;
import Requests.objects.Request;
import Responses.Response;
import Responses.ResponseWriter;
import Util.RequestExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.Future;

/**
 * This class handles a given Request caught by the Servlet.
 */
public class RequestHandler {

    /**
     * The class is not supposed to have any instances, and therefore throws exceptions if that is the case.
     * @throws InvalidPermissionException
     */
    private RequestHandler() throws InvalidPermissionException {
        throw new InvalidPermissionException();
    };

    /**
     * Handles get requests
     * @param request HttpServletRequest passed from the servlet
     * @param response HttpServletResponse passed from the servlet
     */
    public static void handleGet(HttpServletRequest request, HttpServletResponse response){
        Request requestToProcess = RequestFactory.valueOfGetRequest(request);
        writeResponse(response, requestToProcess);
    }

    /**
     * Handles post requests
     * @param request HttpServletRequest passed from the servlet
     * @param response HttpServletResponse passed from the servlet
     */
    public static void handlePost(HttpServletRequest request, HttpServletResponse response){
        Request requestToProcess = RequestFactory.valueOfPostRequest(request);
        writeResponse(response, requestToProcess);
    }

    /**
     * Handles put requests
     * @param request HttpServletRequest passed from the servlet
     * @param response HttpServletResponse passed from the servlet
     */
    public static void handlePut(HttpServletRequest request, HttpServletResponse response){
        Request requestToProcess = RequestFactory.valueOfPutRequest(request);
        writeResponse(response, requestToProcess);
    }

    /**
     * Writes the response to the HttpServletResponse.
     * Adds additional headers for CORS.
     * @param response HttpServletRequest passed from the servlet
     * @param requestToProcess request handled, from which we retrieve a resposne
     */
    private synchronized static void writeResponse(HttpServletResponse response, Request requestToProcess){
        Future<Response> potentialResponse = RequestExecutor.executeRequest(requestToProcess);
        ResponseWriter.writeResponse(potentialResponse, response);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "*");
        response.addHeader("Access-Control-Allow-Headers", "*");
    }
}
