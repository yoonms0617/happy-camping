package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sist.util.DBConn;
import com.sist.util.Pagination;
import com.sist.vo.ItemLikeVO;

public class ItemLikeDAO {

    private Connection conn;

    private PreparedStatement ps;

    private final DBConn dbConn;

    public ItemLikeDAO() {
        this.dbConn = DBConn.getInstance();
    }

    public void itemLike(int ino, String mid) {
        String itemFindSQL = "SELECT name, image, price FROM HC_ITEM_2 WHERE ino = ?";
        String itemLikeSQL = "INSERT INTO HC_ITEM_LIKE_2 (lno, image, name, price, ino, mid) VALUES (HC_ITEM_LIKE_SEQ.NEXTVAL, ?, ? ,? ,?, ?)";
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(itemFindSQL);
            ps.setInt(1, ino);
            ResultSet rs = ps.executeQuery();
            rs.next();
            ItemLikeVO vo = new ItemLikeVO();
            vo.setName(rs.getString(1));
            vo.setImage(getMainImage(rs.getString(2)));
            vo.setPrice(rs.getInt(3));
            rs.close();
            ps = conn.prepareStatement(itemLikeSQL);
            ps.setString(1, vo.getImage());
            ps.setString(2, vo.getName());
            ps.setInt(3, vo.getPrice());
            ps.setInt(4, ino);
            ps.setString(5, mid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
    }

    public void itemUnLike(int ino, String mid) {
        String itemUnlikeSQL = "DELETE FROM HC_ITEM_LIKE_2 WHERE ino = ? AND mid = ?";
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(itemUnlikeSQL);
            ps.setInt(1, ino);
            ps.setString(2, mid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
    }

    public String isLike(int ino, String mid) {
        String itemIsLikeSQL = "SELECT COUNT(mid), COUNT(ino) FROM HC_ITEM_LIKE_2 WHERE mid = ? AND ino = ?";
        String isLike = "N";
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(itemIsLikeSQL);
            ps.setString(1, mid);
            ps.setInt(2, ino);
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

    public Pagination itemLikeList(String mid, int curPage) {
        String itemLikeListSQL = "SELECT lno, name, image, price, ino FROM HC_ITEM_LIKE_2 WHERE mid = ? " +
                "ORDER BY lno DESC OFFSET ? ROW FETCH FIRST 5 ROWS ONLY";
        String totalItemLikeSQL = "SELECT COUNT(*) FROM HC_ITEM_LIKE_2 WHERE mid = ?";
        int rowSize = 5;
        int start = (rowSize * curPage) - (rowSize - 1);
        int totalItemLike = 0;
        List<ItemLikeVO> items = new ArrayList<>();
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(totalItemLikeSQL);
            ps.setString(1, mid);
            ResultSet rs = ps.executeQuery();
            rs.next();
            totalItemLike = rs.getInt(1);
            ps = conn.prepareStatement(itemLikeListSQL);
            ps.setString(1, mid);
            ps.setInt(2, start - 1);
            rs = ps.executeQuery();
            while (rs.next()) {
                ItemLikeVO vo = new ItemLikeVO();
                vo.setLno(rs.getInt(1));
                vo.setName(rs.getString(2));
                vo.setImage(getMainImage(rs.getString(3)));
                vo.setPrice(rs.getInt(4));
                vo.setIno(rs.getInt(5));
                vo.setMid(mid);
                items.add(vo);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
        return new Pagination(items, curPage, totalItemLike, rowSize);
    }

    private String getMainImage(String images) {
        String[] image = images.split(",");
        return image[0];
    }

}
