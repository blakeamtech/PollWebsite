package Requests;

import Exceptions.AssignmentException;
import Responses.Response;
import Users.PollManager;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;


/**
 * Like the Results request, the Details request requires no permissions
 */
public class DetailsRequest extends AbstractRequest implements Request {

    DetailsRequest(HttpServletRequest request){
        super(request);
    };


    /**
     * Implementation of the Details request. Returns the details as file
     * @return Response object containing body and status request.
     */
    @Override
    public Response call() {
        Response toReturn = new Response();
        String extension = (getRequest().getAttribute("extension") == null)
                ? ".txt"
                : getRequest().getAttribute("extension").toString();
        toReturn.addHeader("Content-disposition", String.format("attachment; filename=%s%s", pollTitle, extension));

        try{
            JSONObject obj = PollManager.downloadPollDetails();
            toReturn.setBody(obj.toString(2));

            return toReturn;
        } catch (AssignmentException e) {

            return new Response().serverError().exceptionBody(e);
        }

    }
}
