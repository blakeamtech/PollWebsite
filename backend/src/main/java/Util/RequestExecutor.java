package Util;

import Requests.Request;
import Responses.Response;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * This class handles all incoming requests using a pool of threads.
 * When a request comes in, it is submitted to the pool, which assigns a thread to it if there is one free.
 * When the request is executed, the object will be set in the Future.
 * Otherwise, the future will not return an object with its get() method.
 */
public class RequestExecutor {

    private static final ThreadPoolExecutor requestExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    public static Future<Response> executeRequest(Request request){
        return requestExecutor.submit(request);
    }

}
