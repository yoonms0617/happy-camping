package com.sist.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConn {

    private DataSource dataSource;

    private DBConn() {
        try {
            Context context = new InitialContext();
            Context env = (Context) context.lookup("java:comp/env");
            dataSource = (DataSource) env.lookup("jdbc/oracle");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static DBConn getInstance() {
        return LazyHolder.INSTANCE;
    }

    public Connection createConnection() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void closeConnection(PreparedStatement ps, Connection conn) {
        try {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static class LazyHolder {
        private static final DBConn INSTANCE = new DBConn();
    }

}
