package Requests;

import Responses.Response;

import java.util.concurrent.Callable;

public interface IRequest extends Callable<Response> {

    Response call();


}
