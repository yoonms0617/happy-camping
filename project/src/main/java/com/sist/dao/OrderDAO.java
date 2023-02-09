package com.sist.dao;

import com.sist.util.DBConn;
import com.sist.util.Pagination;
import com.sist.vo.CartVO;
import com.sist.vo.DeliveryVO;
import com.sist.vo.ItemVO;
import com.sist.vo.MemberVO;
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
    public CartVO orderCheck(int cno) {
        String itemFindSQL = "SELECT image, name, price, quantity, ino FROM HC_CART_2 WHERE cno = ?";
        CartVO vo = new CartVO();
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(itemFindSQL);
            ps.setInt(1, cno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vo.setImage(getMainImage(rs.getString(1)));
                vo.setName(rs.getString(2));
                vo.setPrice(rs.getInt(3));
                vo.setQuantity(rs.getInt(4));
                vo.setIno(rs.getInt(5));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
        return vo;
    }

    // 주문 상세 페이지
    public OrderVO orderDetail(int ono) {
        String orderDetailSQL =
                "SELECT ho.ONO, ho.CODE, ho.STATUS, ho.PRICE, ho.ORDER_DATE, hoi.IMAGE, " +
                "hoi.NAME, hoi.PRICE, hoi.QUANTITY, hd.NAME, hd.POSTCODE, hd.HOME_ADDR, hd.DETAIL_ADDR FROM HC_ORDER_2 ho " +
                "INNER JOIN HC_ORDER_ITEM_2 hoi " +
                "ON ho.ONO = hoi.ONO " +
                "INNER JOIN HC_DELIVERY_2 hd " +
                "ON ho.ONO = hd.DNO " +
                "WHERE ho.ONO = ?";
        OrderVO vo = new OrderVO();
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(orderDetailSQL);
            ps.setInt(1, ono);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DeliveryVO deliveryVO = new DeliveryVO();
                OrderItemVO orderItemVO = new OrderItemVO();
                vo.setOno(rs.getInt(1));
                vo.setCode(rs.getString(2));
                vo.setStatus(rs.getString(3));
                vo.setPrice(rs.getInt(4));
                vo.setOrderedAt(rs.getDate(5));
                orderItemVO.setImage(getMainImage(rs.getString(6)));
                orderItemVO.setName(rs.getString(7));
                orderItemVO.setPrice(rs.getInt(8));
                orderItemVO.setQuantity(rs.getInt(9));
                deliveryVO.setName(rs.getString(10));
                deliveryVO.setPostcode(rs.getString(11));
                deliveryVO.setHomeAddr(rs.getString(12));
                deliveryVO.setDetailAddr(rs.getString(13));
                vo.setOrderItemVO(orderItemVO);
                vo.setDeliveryVO(deliveryVO);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
        return vo;
    }

    // 상품 주문
    public void orderItem(MemberVO member, OrderItemVO orderItem) {
        String orderSQL = "INSERT INTO HC_ORDER_2 (ONO, CODE, STATUS, PRICE, MID) VALUES (HC_ORDER_SEQ.NEXTVAL, ?, ?, ?, ?)";
        String orderItemSQL = "INSERT INTO HC_ORDER_ITEM_2 (OINO, IMAGE, NAME, PRICE, QUANTITY, INO, ONO) VALUES (HC_ORDER_ITEM_SEQ.NEXTVAL, ?, ?, ?, ?, ?, HC_ORDER_SEQ.CURRVAL)";
        String orderDeliverySQL = "INSERT INTO HC_DELIVERY_2 (DNO, NAME, TEL, POSTCODE, HOME_ADDR, DETAIL_ADDR) VALUES (HC_ORDER_SEQ.CURRVAL, ?, ?, ?, ?, ?)";
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(orderItemSQL);
            ps.setString(1, orderItem.getImage());
            ps.setString(2, orderItem.getName());
            ps.setInt(3, orderItem.getPrice());
            ps.setInt(4, orderItem.getQuantity());
            ps.setInt(5, orderItem.getIno());
            ps.executeUpdate();
            ps = conn.prepareStatement(orderSQL);
            ps.setString(1, createOrderCode(orderItem.getIno()));
            ps.setString(2, "주문 완료");
            ps.setInt(3, orderItem.getPrice() * orderItem.getQuantity());
            ps.setString(4, member.getMid());
            ps.executeUpdate();
            ps = conn.prepareStatement(orderDeliverySQL);
            ps.setString(1, member.getName());
            ps.setString(2, member.getTel());
            ps.setString(3, member.getPostcode());
            ps.setString(4, member.getHomeAddr());
            ps.setString(5, member.getDetailAddr());
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
