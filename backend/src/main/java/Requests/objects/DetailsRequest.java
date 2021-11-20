package Requests.objects;

import Exceptions.AssignmentException;
import Responses.Response;
import Users.PollManager;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;


/**
 * Like the Results request, the Details request requires no permissions
 */
public class DetailsRequest extends AbstractRequest implements Request {

    public DetailsRequest(HttpServletRequest request) {
        super(request);
    }
    /**
     * Implementation of the Details request. Returns the details as file
     *
     * @return Response object containing body and status request.
     */
    @Override
    public Response call() {
        try {
            Response toReturn = new Response();
            String pollTitle = PollManager.getPollTitle(this.getPollId()).orElseGet(() -> "empty");
            String extension = (getRequest().getAttribute("extension") == null)
                    ? ".txt"
                    : getRequest().getAttribute("extension").toString();
            toReturn.addHeader("Content-disposition", String.format("attachment; filename=%s-%d%s", pollTitle, System.currentTimeMillis(), extension));
            String choice = this.getRequest().getParameter("choice");
            if (choice.equals("JSON")) {
                JSONObject obj = PollManager.downloadJSonPollDetails(this.getPollId());
                toReturn.setBody(obj.toString(2)); // For use with JSON
            } else if (choice.equals("TEXT")) {
                String str = PollManager.downloadStringPollDetails(this.getPollId());
                toReturn.setBody(str);
            } else {
                String xml = PollManager.downloadXMLPollDetails(this.getPollId());
                toReturn.setBody(xml);
            }
            return toReturn;
        } catch (AssignmentException | SQLException | ClassNotFoundException e) {
            return new Response().serverError().exceptionBody(e);
        }
    }
}
