package Requests;

import Responses.Response;
import Responses.ResponseWriter;
import Util.RequestExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.Future;

public class RequestHandler {

    private RequestHandler(){};

    public static void handleGet(HttpServletRequest request, HttpServletResponse response){
        Request requestToProcess = RequestFactory.valueOfGetRequest(request);
        writeResponse(response, requestToProcess);
    }

    public static void handlePost(HttpServletRequest request, HttpServletResponse response){
        Request requestToProcess = RequestFactory.valueOfPostRequest(request);
        writeResponse(response, requestToProcess);
    }

    public static void handlePut(HttpServletRequest request, HttpServletResponse response){
        Request requestToProcess = RequestFactory.valueOfPutRequest(request);
        writeResponse(response, requestToProcess);
    }

    private synchronized static void writeResponse(HttpServletResponse response, Request requestToProcess){
        Future<Response> potentialResponse = RequestExecutor.executeRequest(requestToProcess);
        ResponseWriter.writeResponse(potentialResponse, response);
        response.addHeader("Access-Control-Allow-Origin", "*");
    }
}
