package com.sist.dao;

import com.sist.util.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {

    private Connection conn;

    private PreparedStatement ps;

    private final DBConn dbConn;

    public MemberDAO() {
        this.dbConn = DBConn.getInstance();
    }

    public boolean isDuplicateUserId(String userId) {
        String sql = "SELECT COUNT(*) FROM HC_MEMBER_2 WHERE mid = ?";
        boolean flag = true;
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int result = rs.getInt(1);
            if (result == 0) {
                flag = false;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
        return flag;
    }

    public boolean isDuplicateEmail(String email) {
        String sql = "SELECT COUNT(*) FROM HC_MEMBER_2 WHERE email = ?";
        boolean flag = true;
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int result = rs.getInt(1);
            if (result == 0) {
                flag = false;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
        return flag;
    }

}
