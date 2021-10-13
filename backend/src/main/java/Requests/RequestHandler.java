package Requests;

import Responses.Response;
import Responses.ResponseWriter;
import Util.RequestExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RequestHandler {

    public synchronized static void handleGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ExecutionException, InterruptedException, TimeoutException {
        IRequest requestToProcess = RequestFactory.valueOfGetRequest(request);
        Future<Response> potentialResponse = RequestExecutor.executeRequest(requestToProcess);
        ResponseWriter.writeResponse(potentialResponse, response);
    }

    public static void handlePost(){

    }

    public static void handlePut(){

    }


}
