package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sist.util.DBConn;
import com.sist.util.Pagination;
import com.sist.vo.NoticeVO;

public class NoticeDAO {

    private Connection conn;

    private PreparedStatement ps;

    private final DBConn dbConn;

    public NoticeDAO() {
        this.dbConn = DBConn.getInstance();
    }

    public Pagination noticeList(int curPage) {
        List<NoticeVO> items = new ArrayList<>();
        String pagingQuery = "SELECT nno, title, regdate, writer, hit, num " +
                "FROM (SELECT nno, title, regdate, writer, hit, ROWNUM AS num " +
                "FROM (SELECT /*+ INDEX_DESC(HC_NOTICE_2 HC_NOTICE_NNO_PK) */ nno, title, regdate, writer, hit FROM HC_NOTICE_2)) " +
                "WHERE num BETWEEN ? AND ?";
        String totalNoticeCntQuery = "SELECT COUNT(*) FROM HC_NOTICE_2";
        int totalNoticeCnt = 0;
        int rowSize = 10;
        int start = (rowSize * curPage) - (rowSize - 1);
        int end = rowSize * curPage;
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(totalNoticeCntQuery);
            ResultSet rs = ps.executeQuery();
            rs.next();
            totalNoticeCnt = rs.getInt(1);
            ps = conn.prepareStatement(pagingQuery);
            ps.setInt(1 , start);
            ps.setInt(2, end);
            rs = ps.executeQuery();
            while (rs.next()) {
                NoticeVO vo = new NoticeVO();
                vo.setNno(rs.getInt(1));
                vo.setTitle(rs.getString(2));
                vo.setRegDate(rs.getDate(3));
                vo.setWriter(rs.getString(4));
                vo.setHit(rs.getInt(5));
                items.add(vo);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
        return new Pagination(items, curPage, totalNoticeCnt);
    }

    public void noticeWrite(String title, String content) {
        String noticeWriteSQL = "INSERT INTO HC_NOTICE_2 (nno, title, content) VALUES (HC_NOTICE_SEQ.NEXTVAL, ?, ?)";
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(noticeWriteSQL);
            ps.setString(1, title);
            ps.setString(2, content);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
    }

    public NoticeVO noticeDetail(int no) {
        String noticeFindQuery = "SELECT nno, title, writer, content, regdate FROM HC_NOTICE_2 WHERE nno =?";
        String noticeHitUpdateQuery = "UPDATE HC_NOTICE_2 SET hit = hit + 1 WHERE nno = ?";
        NoticeVO vo = new NoticeVO();
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(noticeFindQuery);
            ps.setInt(1, no);
            ResultSet rs = ps.executeQuery();
            rs.next();
            vo.setNno(rs.getInt(1));
            vo.setTitle(rs.getString(2));
            vo.setWriter(rs.getString(3));
            vo.setContent(rs.getString(4));
            vo.setRegDate(rs.getDate(5));
            ps = conn.prepareStatement(noticeHitUpdateQuery);
            ps.setInt(1, no);
            ps.executeUpdate();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
        return vo;
    }

    public void noticeUpdate(int no, String title, String content) {
        String noticeUpdateQuery = "UPDATE HC_NOTICE_2 SET title = ?, content = ? WHERE nno = ?";
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(noticeUpdateQuery);
            ps.setString(1, title);
            ps.setString(2, content);
            ps.setInt(3, no);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
    }

    public void noticeDelete(int no) {
        String noticeDeleteQuery = "DELETE FROM HC_NOTICE_2 WHERE nno = ?";
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(noticeDeleteQuery);
            ps.setInt(1, no);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
    }

}
