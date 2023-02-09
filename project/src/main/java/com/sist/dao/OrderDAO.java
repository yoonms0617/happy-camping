package com.sist.dao;

import com.sist.util.DBConn;
import com.sist.util.Pagination;
import com.sist.vo.ItemVO;
import com.sist.vo.OrderItemVO;
import com.sist.vo.OrderVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAO {

    private Connection conn;

    private PreparedStatement ps;

    private final DBConn dbConn;

    public OrderDAO() {
        this.dbConn = DBConn.getInstance();
    }

    // 상품 주문 페이지
    public ItemVO orderCheck(int ino) {
        String itemFindSQL = "SELECT name, image, price, sale FROM HC_ITEM_2 ino = ?";
        ItemVO vo = new ItemVO();
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(itemFindSQL);
            ps.setInt(1, ino);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vo.setName(rs.getString(1));
                vo.setImage(getMainImage(rs.getString(2)));
                vo.setPrice(rs.getInt(3));
                vo.setSale(rs.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
        return vo;
    }

    // 주문 상세 페이지
    public void orderDetail(String mid) {
        String orderDetailSQL = "";
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(orderDetailSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
    }

    // 상품 주문
    public void orderItem() {
        String orderItemSQL = "INSERT INTO";
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(orderItemSQL);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
    }

    // 주문 목록
    public Pagination orderList(String mid, int curPage) {
        String orderListSQL = "SELECT ho.ORDER_DATE, ho.ONO, hoi.IMAGE, hoi.NAME, hoi.PRICE, hoi.QUANTITY, hoi.INO FROM HC_ORDER_2 ho " +
                "INNER JOIN HC_ORDER_ITEM_2 hoi " +
                "ON ho.ONO = hoi.ONO " +
                "WHERE ho.MID = ? " +
                "ORDER BY ho.ONO DESC OFFSET ? ROWS FETCH FIRST 5 ROWS ONLY";
        String totalOrderList = "SELECT COUNT(*) FROM HC_ORDER_2 WHERE mid = ?";
        int rowSize = 5;
        int start = (rowSize * curPage) - (rowSize - 1);
        int totalOrder = 0;
        List<OrderVO> items = new ArrayList<>();
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(totalOrderList);
            ps.setString(1, mid);
            ResultSet rs = ps.executeQuery();
            rs.next();
            totalOrder = rs.getInt(1);
            ps = conn.prepareStatement(orderListSQL);
            ps.setString(1, mid);
            ps.setInt(2, start - 1);
            rs = ps.executeQuery();
            while (rs.next()) {
                OrderVO orderVO = new OrderVO();
                OrderItemVO orderItemVO = new OrderItemVO();
                orderVO.setOrderedAt(rs.getDate(1));
                orderVO.setOno(rs.getInt(2));
                orderItemVO.setImage(getMainImage(rs.getString(3)));
                orderItemVO.setName(rs.getString(4));
                orderItemVO.setPrice(rs.getInt(5));
                orderItemVO.setQuantity(rs.getInt(6));
                orderItemVO.setIno(rs.getInt(7));
                orderVO.setOrderItemVO(orderItemVO);
                items.add(orderVO);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
        return new Pagination(items, curPage, totalOrder, rowSize);
    }

    // 주문 내역 삭제
    public void orderListDelete(String mid, int ono) {
        String orderListDelteSQL = "DELETE FROM HC_ORDER_2 WHERE mid = ? AND ono = ?";
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(orderListDelteSQL);
            ps.setString(1, mid);
            ps.setInt(2, ono);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
    }

    // 주문 코드 생성
    private String createOrderCode(int ino) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(now);
        return "HC" + date + ino;
    }

    // 상품 대표 이미지 가져오기
    private String getMainImage(String images) {
        String[] image = images.split(",");
        return image[0];
    }


}
