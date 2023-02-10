package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sist.util.DBConn;
import com.sist.util.Pagination;
import com.sist.vo.CampLikeVO;
import com.sist.vo.CampVO;

public class CampLikeDAO {

    private Connection conn;

    private PreparedStatement ps;

    private final DBConn dbConn;

    public CampLikeDAO() {
        this.dbConn = DBConn.getInstance();
    }

    public void campLike(int cno, String mid) {
        String campFindSQL = "SELECT name, image FROM HC_CAMP_2 WHERE cno = ?";
        String campLikeSQL = "INSERT INTO HC_CAMP_LIKE_2 (crno, image, name, mid, cno) VALUES (HC_CAMP_LIKE_SEQ.NEXTVAL, ?, ? ,? ,?)";
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(campFindSQL);
            ps.setInt(1, cno);
            ResultSet rs = ps.executeQuery();
            rs.next();
            CampVO campVO = new CampVO();
            campVO.setName(rs.getString(1));
            campVO.setImage(rs.getString(2));
            rs.close();
            ps = conn.prepareStatement(campLikeSQL);
            ps.setString(1, campVO.getImage());
            ps.setString(2, campVO.getName());
            ps.setString(3, mid);
            ps.setInt(4, cno);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
    }

    public void campUnlike(int cno, String mid) {
        String campUnlikeSQL = "DELETE FROM HC_CAMP_LIKE_2 WHERE cno = ? AND mid = ?";
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(campUnlikeSQL);
            ps.setInt(1, cno);
            ps.setString(2, mid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
    }

    public String isLike(int cno, String mid) {
        String campIsLikeSQL = "SELECT COUNT(mid), COUNT(cno) FROM HC_CAMP_LIKE_2 WHERE mid = ? AND cno = ?";
        String isLike = "N";
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(campIsLikeSQL);
            ps.setString(1, mid);
            ps.setInt(2, cno);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int midCnt = rs.getInt(1);
            int cnoCnt = rs.getInt(2);
            System.out.println("midCnt = " + midCnt);
            System.out.println("cnoCnt = " + cnoCnt);
            if ((midCnt  + cnoCnt) == 2) {
                isLike = "Y";
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
        return isLike;
    }

    public Pagination campLikeList(String mid, int curPage) {
        String campLikeListSQL = "SELECT crno, name, image, cno FROM HC_CAMP_LIKE_2 WHERE mid = ? " +
                "ORDER BY crno DESC OFFSET ? ROW FETCH FIRST 5 ROWS ONLY";
        String totalCampLikeSQL = "SELECT COUNT(*) FROM HC_CAMP_LIKE_2 WHERE mid = ?";
        int rowSize = 5;
        int start = (rowSize * curPage) - (rowSize - 1);
        int totalCampLike = 0;
        List<CampLikeVO> items = new ArrayList<>();
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(totalCampLikeSQL);
            ps.setString(1, mid);
            ResultSet rs = ps.executeQuery();
            rs.next();
            totalCampLike = rs.getInt(1);
            ps = conn.prepareStatement(campLikeListSQL);
            ps.setString(1, mid);
            ps.setInt(2, start - 1);
            rs = ps.executeQuery();
            while (rs.next()) {
                CampLikeVO vo = new CampLikeVO();
                vo.setCrno(rs.getInt(1));
                vo.setName(rs.getString(2));
                vo.setImage(rs.getString(3));
                vo.setMid(mid);
                vo.setCno(rs.getInt(4));
                items.add(vo);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
        return new Pagination(items, curPage, totalCampLike, rowSize);
    }

}
