package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.sist.vo.MemberVO;
import com.sist.util.DBConn;
import com.sist.util.Pagination;
import com.sist.vo.BoardVO;
import com.sist.vo.BoardReplyVO;
public class BoardDAO {

    private Connection conn;

    private PreparedStatement ps;

    private final DBConn dbConn;

    public BoardDAO() {
        this.dbConn = DBConn.getInstance();
    }

    public Pagination boardList(int curPage) {
        List<BoardVO> items = new ArrayList<>();
        String pagingQuery = "SELECT bno, title, regdate, writer, hit, num " +
                "FROM (SELECT bno, title, regdate, writer, hit, ROWNUM AS num " +
                "FROM (SELECT /*+ INDEX_DESC(HC_BOARD_2 HC_BOARD_BNO_PK) */ bno, title, regdate, writer, hit FROM HC_BOARD_2)) " +
                "WHERE num BETWEEN ? AND ?";
        String totalBoardCntQuery = "SELECT COUNT(*) FROM HC_BOARD_2";
        int totalBoardCnt = 0;
        int rowSize = 10;
        int start = (rowSize * curPage) - (rowSize - 1);
        int end = rowSize * curPage;
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(totalBoardCntQuery);
            ResultSet rs = ps.executeQuery();
            rs.next();
            totalBoardCnt = rs.getInt(1);
            ps = conn.prepareStatement(pagingQuery);
            ps.setInt(1 , start);
            ps.setInt(2, end);
            rs = ps.executeQuery();
            while (rs.next()) {
                BoardVO vo = new BoardVO();
                vo.setBno(rs.getInt(1));
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
        return new Pagination(items, curPage, totalBoardCnt , 10);
    }

    public void boardWrite(String title, String content, String writer) {
        String boardWriteSQL = "INSERT INTO HC_BOARD_2 (bno, title, content, writer, mid) VALUES (HC_BOARD_SEQ.NEXTVAL, ?, ?, ?, ?)";
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(boardWriteSQL);
            ps.setString(1, title);
            ps.setString(2, content);
            ps.setString(3, writer);
            ps.setString(4, writer);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
    }

    public BoardVO boardDetail(int no) {
        String boardFindQuery = "SELECT bno, title, writer, content, regdate FROM HC_BOARD_2 WHERE bno =?";
        String boardUpdateQuery = "UPDATE HC_BOARD_2 SET hit = hit + 1 WHERE bno = ?";
        BoardVO vo = new BoardVO();
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(boardFindQuery);
            ps.setInt(1, no);
            ResultSet rs = ps.executeQuery();
            rs.next();
            vo.setBno(rs.getInt(1));
            vo.setTitle(rs.getString(2));
            vo.setWriter(rs.getString(3));
            vo.setContent(rs.getString(4));
            vo.setRegDate(rs.getDate(5));
            ps = conn.prepareStatement(boardUpdateQuery);
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

    public void boardUpdate(int no, String title, String content) {
        String boardUpdateQuery = "UPDATE HC_BOARD_2 SET title = ?, content = ? WHERE bno = ?";
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(boardUpdateQuery);
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

    public void boardDelete(int no) {
        String boardDeleteQuery = "DELETE FROM HC_BOARD_2 WHERE bno = ?";
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(boardDeleteQuery);
            ps.setInt(1, no);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
    }

}























