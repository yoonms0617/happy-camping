package com.sist.dao;

import com.sist.util.DBConn;
import com.sist.util.Pagination;
import com.sist.vo.CampReviewVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class CampReviewDAO {

    private Connection conn;

    private PreparedStatement ps;

    private final DBConn dbConn;

    public CampReviewDAO() {
        this.dbConn = DBConn.getInstance();
    }

    public Pagination campReview(int cno, int curPage) {
        String sql = "SELECT clno, writer, content, regdate, mid, cno, num " +
                "FROM (SELECT clno, writer, content, regdate, mid, cno, ROWNUM AS num " +
                "FROM (SELECT /*+INDEX_DESC(HC_CAMP_REVIEW_2 HC_CAMP_REVIEW_CLNO_PK)*/ clno, writer, content, regdate, cno, mid FROM HC_CAMP_REVIEW_2 WHERE cno = ?)) " +
                "WHERE num BETWEEN ? AND ?";
        List<CampReviewVO> reviews = new ArrayList<>();
        int rowSize = 5;
        int start = (rowSize * curPage) - (rowSize - 1);
        int end = rowSize * curPage;
        int totalPage = getTotalPage(cno, rowSize);
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cno);
            ps.setInt(2, start);
            ps.setInt(3, end);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CampReviewVO vo = new CampReviewVO();
                vo.setClno(rs.getInt(1));
                vo.setWriter(rs.getString(2));
                vo.setContent(rs.getString(3));
                vo.setRegDate(rs.getDate(4));
                vo.setMid(rs.getString(5));
                vo.setCno(rs.getInt(6));
                reviews.add(vo);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
        return new Pagination(reviews, curPage, totalPage);
    }

    private int getTotalPage(int cno, int rowSize) {
        String sql = "SELECT COUNT(*) FROM HC_CAMP_REVIEW_2 WHERE cno = ?";
        int totalPage = 0;
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cno);
            ResultSet rs = ps.executeQuery();
            rs.next();
            totalPage = rs.getInt(1);
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
        if (totalPage % rowSize != 0) {
            totalPage = totalPage / rowSize + 1;
        } else {
            totalPage = totalPage / rowSize;
        }
        return totalPage;
    }

    public void writeCampReview(String mid, String content, int cno) {
        String sql = "INSERT INTO HC_CAMP_REVIEW_2 (clno, writer, content, mid, cno) VALUES (HC_CAMP_REVIEW_SEQ.NEXTVAL, ?, ?, ?, ?)";
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, mid);
            ps.setString(2, content);
            ps.setString(3, mid);
            ps.setInt(4, cno);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
    }

    public void updateCampReview(String content, int clno) {
        String sql = "UPDATE HC_CAMP_REVIEW_2 SET content = ?, regdate = SYSDATE WHERE clno = ?";
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, content);
            ps.setInt(2, clno);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
    }

    public void deleteCampReview(int clno) {
        String sql = "DELETE FROM HC_CAMP_REVIEW_2 WHERE clno = ?";
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, clno);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
    }


}
