package Requests;

import Responses.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateRequest implements IRequest{

    private HttpServletRequest request;
    private HttpServletResponse response;

    UpdateRequest(){};

    public UpdateRequest setHttpRequestObject(HttpServletRequest request){
        this.request = request;
        return this;
    }

    public UpdateRequest setHttpResponseObject(HttpServletResponse response){
        this.response = response;
        return this;
    }

    @Override
    public Response call() {
        return null;
    }
}
