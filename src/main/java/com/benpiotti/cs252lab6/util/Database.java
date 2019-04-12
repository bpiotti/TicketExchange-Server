package com.benpiotti.cs252lab6.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.sql.*;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Database {

    public static final String JAVA_COMP_ENV 	= 	"java:comp/env";

    static final Logger log = LoggerFactory.getLogger(Database.class);

    public static Connection getConnection(String connectionId) throws Exception {
        Connection con = null;
        DataSource ds = null;

//        String env = Config.getEnvironment().toUpperCase();
//        String _connectionId = connectionId.toUpperCase()+ "_" + env;
//        log.debug("_connectionId is {}", _connectionId);

        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup(JAVA_COMP_ENV);
            ds = (DataSource) envCtx.lookup("cs252lab6");
            if (ds != null)
                con = ds.getConnection();
            else
                throw new SQLException("Could not get DataSource object for the connection Id: " + "CS252LAB6_DEV");

        } catch (Exception e) {
            log.error("Error in getConnection {}", e.getMessage());
            e.printStackTrace();
            throw e;
        }
        return con;
    }

    /* Close Connection, ResultSet, and Statement, or any combination of them */
    public static void closeAll (final Object... things) {
        for (final Object thing : things) {
            if (null != thing) {
                try {
                    if (thing instanceof ResultSet) {
                        try {
                            ((ResultSet) thing).close ();
                        } catch (final SQLException e) {
                            /* No Op */
                        }
                    }
                    if (thing instanceof Statement) {
                        try {
                            ((Statement) thing).close ();
                        } catch (final SQLException e) {
                            /* No Op */
                        }
                    }

                    if (thing instanceof PreparedStatement) {
                        try {
                            ((PreparedStatement) thing).close ();
                        } catch (final SQLException e) {
                            /* No Op */
                        }
                    }

                    if (thing instanceof Connection) {
                        try {
                            ((Connection) thing).close ();
                        } catch (final SQLException e) {
                            /* No Op */
                        }
                    }
                    /*
                    if (thing instanceof Lock) {
                        try {
                            ((Lock) thing).unlock ();
                        } catch (final IllegalMonitorStateException e) {

                        }
                    } */
                } catch (final RuntimeException e) {
                    /* No Op */
                }
            }
        }
    }
}
