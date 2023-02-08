package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sist.util.DBConn;
import com.sist.util.Pagination;
import com.sist.vo.BoardVO;
import com.sist.vo.CampReviewVO;
import com.sist.vo.CampVO;
import com.sist.vo.ItemReviewVO;
import com.sist.vo.ItemVO;
import com.sist.vo.MemberVO;

public class MyPageDAO {

    private Connection conn;

    private PreparedStatement ps;

    private final DBConn dbConn;

    public MyPageDAO() {
        this.dbConn = DBConn.getInstance();
    }

    public MemberVO loginMember(String mid) {
        String findLoginMemberSQL = "SELECT name, email, tel FROM HC_MEMBER_2 WHERE mid = ?";
        MemberVO vo = new MemberVO();
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(findLoginMemberSQL);
            ps.setString(1, mid);
            ResultSet rs = ps.executeQuery();
            rs.next();
            vo.setMid(mid);
            vo.setName(rs.getString(1));
            vo.setEmail(rs.getString(2));
            vo.setTel(rs.getString(3));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
        return vo;
    }

    public MemberVO loginMemberAddress(String mid) {
        String findLoginMemberSQL = "SELECT postcode, home_addr, detail_addr FROM HC_MEMBER_2 WHERE mid = ?";
        MemberVO vo = new MemberVO();
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(findLoginMemberSQL);
            ps.setString(1, mid);
            ResultSet rs = ps.executeQuery();
            rs.next();
            vo.setMid(mid);
            vo.setPostcode(rs.getString(1));
            vo.setHomeAddr(rs.getString(2));
            vo.setDetailAddr(rs.getString(3));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
        return vo;
    }

    public Pagination campReviewList(String mid, int curPage) {
        String campReviewSQL = "SELECT hcr.CLNO, hcr.CONTENT, hcr.REGDATE, hcr.CNO, hc.IMAGE, hc.NAME, hc.ADDRESS FROM HC_CAMP_REVIEW_2 hcr " +
                "INNER JOIN HC_CAMP_2 hc ON hcr.CNO = hc.CNO WHERE hcr.MID = ? " +
                "ORDER BY CLNO DESC OFFSET ? ROWS FETCH FIRST 5 ROWS ONLY";
        String totalCampReviewSQL = "SELECT COUNT(*) FROM HC_CAMP_REVIEW_2 WHERE mid = ?";
        int rowSize = 5;
        int start = (rowSize * curPage) - (rowSize - 1);
        int totalCampReview = 0;
        List<CampReviewVO> items = new ArrayList<>();
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(totalCampReviewSQL);
            ps.setString(1, mid);
            ResultSet rs = ps.executeQuery();
            rs.next();
            totalCampReview = rs.getInt(1);
            ps = conn.prepareStatement(campReviewSQL);
            ps.setString(1, mid);
            ps.setInt(2, start - 1);
            rs = ps.executeQuery();
            while (rs.next()) {
                CampReviewVO campReviewVO = new CampReviewVO();
                CampVO campVO = new CampVO();
                campReviewVO.setClno(rs.getInt(1));
                campReviewVO.setContent(rs.getString(2));
                campReviewVO.setRegDate(rs.getDate(3));
                campReviewVO.setCno(rs.getInt(4));
                campVO.setImage(rs.getString(5));
                campVO.setName(rs.getString(6));
                campVO.setAddress(rs.getString(7));
                campReviewVO.setCampVO(campVO);
                items.add(campReviewVO);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
        return new Pagination(items, curPage, totalCampReview, rowSize);
    }

    public Pagination itemReviewList(String mid, int curPage) {
        String itemReviewSQL = "SELECT hir.rno, hir.writer, hir.content, hi.ino, hi.image, hi.name, hir.updated FROM HC_ITEM_REVIEW_2 hir " +
                "INNER JOIN HC_ITEM_2 hi ON hir.INO = hi.INO WHERE hir.MID = ? " +
                "ORDER BY hir.RNO DESC OFFSET ? ROW FETCH FIRST 5 ROWS ONLY";
        String totalItemReviewSQL = "SELECT COUNT(*) FROM HC_CAMP_REVIEW_2 WHERE mid = ?";
        int rowSize = 5;
        int start = (rowSize * curPage) - (rowSize - 1);
        int totalItemReview = 0;
        List<ItemReviewVO> items = new ArrayList<>();
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(totalItemReviewSQL);
            ps.setString(1, mid);
            ResultSet rs = ps.executeQuery();
            rs.next();
            totalItemReview = rs.getInt(1);
            ps = conn.prepareStatement(itemReviewSQL);
            ps.setString(1, mid);
            ps.setInt(2, start - 1);
            rs = ps.executeQuery();
            while (rs.next()) {
                ItemReviewVO itemReviewVO = new ItemReviewVO();
                ItemVO itemVO = new ItemVO();
                itemReviewVO.setRno(rs.getInt(1));
                itemReviewVO.setWriter(rs.getString(2));
                itemReviewVO.setContent(rs.getString(3));
                itemVO.setIno(rs.getInt(4));
                String image = rs.getString(5);
                itemVO.setImage(image.substring(0, image.indexOf(",")));
                itemVO.setName(rs.getString(6));
                itemReviewVO.setUpdated(rs.getDate(7));
                itemReviewVO.setItemVO(itemVO);
                items.add(itemReviewVO);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
        return new Pagination(items, curPage, totalItemReview, rowSize);
    }

    public Pagination boardList(String mid, int curPage) {
        String boardListSQL = "SELECT bno, title, writer, regdate FROM HC_BOARD_2 WHERE mid = ? " +
                "ORDER BY bno DESC OFFSET ? ROW FETCH FIRST 10 ROWS ONLY";
        String totalBoardListSQL = "SELECT COUNT(*) FROM HC_BOARD_2 WHERE mid = ?";
        int rowSize = 10;
        int start = (rowSize * curPage) - (rowSize - 1);
        System.out.println(start);
        int totalBoardList = 0;
        List<BoardVO> items = new ArrayList<>();
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(totalBoardListSQL);
            ps.setString(1, mid);
            ResultSet rs = ps.executeQuery();
            rs.next();
            totalBoardList = rs.getInt(1);
            ps = conn.prepareStatement(boardListSQL);
            ps.setString(1, mid);
            ps.setInt(2, start - 1);
            rs = ps.executeQuery();
            while (rs.next()) {
                BoardVO vo = new BoardVO();
                vo.setBno(rs.getInt(1));
                vo.setTitle(rs.getString(2));
                vo.setWriter(rs.getString(3));
                vo.setRegDate(rs.getDate(4));
                items.add(vo);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
        System.out.println("items.size() = " + items.size());
        return new Pagination(items, curPage, totalBoardList, rowSize);
    }

    public String changePassword(String mid, String oldPassword, String newPassword) {
        String loginMemberPwdSQL = "SELECT password FROM HC_MEMBER_2 WHERE mid = ?";
        String changePwdSQL = "UPDATE HC_MEMBER_2 SET password = ? WHERE mid = ?";
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(loginMemberPwdSQL);
            ps.setString(1, mid);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String curPwd = rs.getString(1);
            rs.close();
            if (!curPwd.equals(oldPassword)) {
                return "NN";
            }
            if (newPassword.equals(curPwd)) {
                return "NNN";
            }
            ps = conn.prepareStatement(changePwdSQL);
            ps.setString(1, newPassword);
            ps.setString(2, mid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
        return "YY";
    }

    public void changeAddress(String mid, String postCode, String homeAddress, String detailAddress) {
        String updateAddressSQL = "UPDATE HC_MEMBER_2 SET postcode = ?, home_addr = ?, detail_addr = ? WHERE mid = ?";
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(updateAddressSQL);
            ps.setString(1, postCode);
            ps.setString(2, homeAddress);
            ps.setString(3, detailAddress);
            ps.setString(4, mid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
    }

    public void deleteAccount(String mid) {
        String deleteMemberSQL = "DELETE HC_MEMBER_2 WHERE mid = ?";
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(deleteMemberSQL);
            ps.setString(1, mid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
    }

}
