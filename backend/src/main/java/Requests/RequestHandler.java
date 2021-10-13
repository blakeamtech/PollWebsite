package Requests;

import Responses.Response;
import Responses.ResponseWriter;
import Util.RequestExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.Future;

public class RequestHandler {

    public synchronized static void handleGet(HttpServletRequest request, HttpServletResponse response){
        IRequest requestToProcess = RequestFactory.valueOfGetRequest(request);
        Future<Response> potentialResponse = RequestExecutor.executeRequest(requestToProcess);
        ResponseWriter.writeResponse(potentialResponse, response);
    }

    public static void handlePost(HttpServletRequest request, HttpServletResponse response){
        IRequest requestToProcess = RequestFactory.valueOfPostRequest(request);
        Future<Response> potentialResponse = RequestExecutor.executeRequest(requestToProcess);
        ResponseWriter.writeResponse(potentialResponse, response);
    }

    public static void handlePut(HttpServletRequest request, HttpServletResponse response){
        IRequest requestToProcess = RequestFactory.valueOfPutRequest(request);
        Future<Response> potentialResponse = RequestExecutor.executeRequest(requestToProcess);
        ResponseWriter.writeResponse(potentialResponse, response);
    }


}
