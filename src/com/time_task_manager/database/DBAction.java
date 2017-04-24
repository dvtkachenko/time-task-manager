package com.time_task_manager.database;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * Created by diman on 19.04.2017.
 */


public interface DBAction {
    boolean openDBConnection(DBConnectionInfo dbci) throws Exception;
    void closeDBConnection() throws Exception;
}
