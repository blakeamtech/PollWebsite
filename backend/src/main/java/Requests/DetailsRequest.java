package Requests;

import Exceptions.AssignmentException;
import Responses.Response;
import Users.PollManager;

import javax.servlet.http.HttpServletRequest;
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

    @Override
    public Response call() {
        Response toReturn = new Response();
        String pollTitle = PollManager.getPollTitle();
        String extension = (getRequest().getAttribute("extension") == null)
                ? ".txt"
                : getRequest().getAttribute("extension").toString();
        toReturn.addHeader("Content-Disposition", String.format("attachment; filename=\"%s\".%s", pollTitle, extension));

        try{
            File f = new File(String.format("%s-%d.%s", pollTitle, PollManager.getPollReleasedTimestamp() , extension));

            PollManager.downloadPollDetails(new PrintWriter(f), pollTitle);

            byte[] fileAsBytes = Files.readAllBytes(f.toPath());

            toReturn.setBody(new String(fileAsBytes));

            return toReturn;
        } catch (AssignmentException | IOException e) {

            return new Response().serverError().body(e.getMessage());
        }

    }
}
