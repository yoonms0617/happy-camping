package com.sist.dao;

import com.sist.util.DBConn;
import com.sist.vo.ItemVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    private Connection conn;

    private PreparedStatement ps;

    private final DBConn dbConn;

    public ItemDAO() {
        this.dbConn = DBConn.getInstance();
    }
    public List<ItemVO> hotSaleItem() {
        String sql =
                "SELECT ino, image, name, price, sale " +
                "FROM (SELECT ino, image, name, price, sale FROM HC_ITEM_2 WHERE sale >= 60 ORDER BY sale DESC) " +
                "WHERE ROWNUM <= 10";
        List<ItemVO> hotSaleItems = new ArrayList<>();
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ItemVO itemVO = new ItemVO();
                itemVO.setIcno(rs.getInt(1));
                String mainImage = getMainImage(rs.getString(2));
                itemVO.setImage(mainImage);
                itemVO.setName(rs.getString(3));
                itemVO.setPrice(rs.getInt(4));
                itemVO.setSale(rs.getInt(5));
                hotSaleItems.add(itemVO);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
        return hotSaleItems;
    }

    public List<ItemVO> newItemList() {
        String sql =
                "SELECT ino, image, name, price " +
                        "FROM (SELECT ino, image, name, price FROM HC_ITEM_2 ORDER BY ino DESC) " +
                        "WHERE ROWNUM <= 10";
        List<ItemVO> newItems = new ArrayList<>();
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ItemVO itemVO = new ItemVO();
                itemVO.setIcno(rs.getInt(1));
                String mainImage = getMainImage(rs.getString(2));
                itemVO.setImage(mainImage);
                itemVO.setName(rs.getString(3));
                itemVO.setPrice(rs.getInt(4));
                newItems.add(itemVO);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
        return newItems;
    }

    private String getMainImage(String images) {
        String[] image = images.split(",");
        return image[0];
    }

}
