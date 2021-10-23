package Requests;

import Exceptions.AssignmentException;
import Responses.Response;
import Users.PollManager;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;


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
        toReturn.addHeader("Content-Disposition",
                String.format("attachment; filename=%s-%d%s",
                        pollTitle,
                        PollManager.getPollReleasedTimestamp(),
                        extension));

        try{
            // Print writer writes directly to ByteArrayOutputStream
            // BOS is then returned as body.
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            PrintWriter pw = new PrintWriter(bos, true);

            PollManager.downloadPollDetails(pw, pollTitle);

            byte[] fileAsBytes = bos.toByteArray();

            toReturn.setBody(new String(fileAsBytes));

            return toReturn;
        } catch (AssignmentException e) {

            return new Response().serverError().exceptionBody(e);
        }

    }
}
