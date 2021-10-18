package Responses;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ResponseWriter {

    public static void writeResponse(Future<Response> potentialResponse, HttpServletResponse httpServletResponse) {
        try {
            Response res = potentialResponse.get();
            httpServletResponse.getOutputStream().println(res.getBody().orElseGet(()->""));
            addHeaders(res, httpServletResponse);
        } catch (IOException | InterruptedException | ExecutionException e) {
            httpServletResponse.setStatus(500);
        }
    }

    public static void addHeaders(Response response, HttpServletResponse httpServletResponse){
        response.getHeaders().keySet().forEach(
                key ->{
                    httpServletResponse.addHeader(key, response.getHeaders().get(key));
                }
        );
    }

}
