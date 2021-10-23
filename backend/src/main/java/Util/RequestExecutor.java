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

    /**
     * A thread pool executor offers a pool of threads to which an object implementing Callable can be passed.
     * This object is then executed inside the thread, and a Response object, wrapped in a Future, is returned
     */
    private static final ThreadPoolExecutor requestExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    /**
     * This method executes a given object implementing the Request interface, which also implements the callable interface
     * @param request a given Request object
     * @return returns a Response wrapped in a Future
     */
    public static Future<Response> executeRequest(Request request){
        return requestExecutor.submit(request);
    }

}
