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
        String pollTitle = PollManager.getPollTitle().orElseGet(() ->"empty");
        String extension = (getRequest().getAttribute("extension") == null)
                ? ".txt"
                : getRequest().getAttribute("extension").toString();
        toReturn.addHeader("Content-disposition", String.format("attachment; filename=%s-%d%s", pollTitle, PollManager.getPollReleasedTimestamp(), extension));
        String choice = this.getRequest().getParameter("choice");

        try{
            if (choice.equals("JSON")) {
                JSONObject obj = PollManager.downloadJSonPollDetails();
                toReturn.setBody(obj.toString(2)); // For use with JSON
            }
            else if (choice.equals("TEXT")){
                String str = PollManager.downloadStringPollDetails();
                toReturn.setBody(str);
            }
            else{
                String xml = PollManager.downloadXMLPollDetails();
                toReturn.setBody(xml);
            }
            return toReturn;
        } catch (AssignmentException e) {

            return new Response().serverError().exceptionBody(e);
        }

    }
}
