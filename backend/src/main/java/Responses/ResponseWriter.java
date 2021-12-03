package Responses;

import Exceptions.InvalidPermissionException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ResponseWriter {

    /**
     * Invalid Constructor as this class is not supposed to be instantiated
     * @throws InvalidPermissionException
     */
    private ResponseWriter() throws InvalidPermissionException {
        throw new InvalidPermissionException();
    }

    /**
     * This method writes a given response to a given HttpServletResponse object
     * @param potentialResponse the given Response object, wrapped in a future
     * @param httpServletResponse the given HttpServletResponse object, passed by the Servlet
     */
    public static void writeResponse(Future<Response> potentialResponse, HttpServletResponse httpServletResponse) {
        try {
            Response res = potentialResponse.get();
            httpServletResponse.getOutputStream().println(res.getBody().orElseGet(()->""));
            addHeaders(res, httpServletResponse);
            httpServletResponse.setStatus(res.getStatusCode().get());
        } catch (IOException | InterruptedException | ExecutionException e) {
            httpServletResponse.setStatus(500);
            httpServletResponse.setHeader("What", e.getMessage());
        }
    }

    /**
     * Method which adds headers present in a PollManager generated response to a HttpServletResponse object
     * @param response
     * @param httpServletResponse
     */
    public static void addHeaders(Response response, HttpServletResponse httpServletResponse){
        response.getHeaders().keySet().forEach(
                key -> httpServletResponse.addHeader(key, response.getHeaders().get(key))
        );
    }

}
