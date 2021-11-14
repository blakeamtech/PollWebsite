package Storage.commands;

import Storage.responses.DBResponse;

import java.util.concurrent.Callable;

public interface DBCommand extends Callable<DBResponse> {

    DBResponse call();

}
