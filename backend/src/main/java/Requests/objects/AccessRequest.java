package Requests.objects;

import Interfaces.Request;
import Responses.Response;

import javax.servlet.http.HttpServletRequest;

public class AccessRequest extends AbstractRequest implements Request {

    public AccessRequest(HttpServletRequest request){
        super(request);
    }

    /**
     * Implementation of the Access request.
     * @return Response object containing body and status request.
     */
    @Override
    public Response call() {
        try {

            /** IMPLEMENTATION:
             *
             * Endpoint: http://localhost:8080/access
             *
             * - See if Poll ID exists
             *  - If Yes:
             *      - Store pin with poll or vice-versa
             *      - return entire poll state (output e.g. below)
             *  - If No:
             *      - return 404 not found status.
             *
             * INPUT BODY:
             *  - E.g.: { "id": "1234567890", "pin": "123456" }
             *
             * OUTPUT BODY:
             *  E.g.: return data should look like:
             *      {
             *         "id": "1283ADE870",
             *         "state": "running",
             *         "title": "Kappa 123",
             *         "question": "Yes or No?",
             *         "choices": ["yes", "no"],
             *         "pin_voteid": {
             *             "273648": "yes",
             *             "834294": "no"
             *         },
             *         "votes": {
             *             "yes": "1",
             *             "no": "1"
             *         }
             *     };
             */

            return new Response().notFound();
        } catch (Exception e) {
            return new Response().badRequest().exceptionBody(e);
        }
    }

}
