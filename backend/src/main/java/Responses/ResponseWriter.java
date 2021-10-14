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
            httpServletResponse.getOutputStream().println(potentialResponse.get(5, TimeUnit.SECONDS).getBody().orElseGet(()->""));
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
            httpServletResponse.setStatus(500);
        }
    }


}
