package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sist.util.DBConn;
import com.sist.util.Pagination;
import com.sist.vo.CampVO;

public class CampDAO {

    private Connection conn;

    private PreparedStatement ps;

    private final DBConn dbconn;

    private final Map<String, String> orderQuery = new HashMap<>();

    private final Map<String, String> searchQuery = new HashMap<>();

    public CampDAO() {
        this.dbconn = DBConn.getInstance();
        orderQuery.put("new", "cno ASC");
        orderQuery.put("hit", "hit DESC");
        searchQuery.put("all", "WHERE name LIKE ? OR address LIKE ? ");
        searchQuery.put("name", "WHERE name LIKE ? AND address LIKE ?");
        searchQuery.put("addr", "WHERE address LIKE ? AND name LIKE ? ");
    }

    public List<CampVO> campItems() {
        String sql = "select cno, image, name, tel , address, num "
                + "from (select cno, image, name, tel , address, rownum as num "
                + "from (select /*+index_asc(HC_CAMP_2 hc_camp_cno_pk)*/cno, image, name, tel , address "
                + "from HC_CAMP_2))"
                + " where num between 1 and 15";
        List<CampVO> list = new ArrayList<>();
        try {
            conn = dbconn.createConnection();
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CampVO vo = new CampVO();
                vo.setCno(rs.getInt(1));
                String image = rs.getString(2);
                if (image.equals("https://www.gocamping.or.kr/img/2018/layout/noimg.jpg")) // 이미지가 없는 부분
                    continue;
                else {
                    vo.setImage(rs.getString(2));
                }
                vo.setName(rs.getString(3));
                vo.setTel(rs.getString(4));
                vo.setAddress(rs.getString(5));
                list.add(vo);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbconn.closeConnection(ps, conn);
        }
        return list;
    }

    public Pagination campList(int curPage, String orderType, String searchType, String searchKeyword) {
        List<CampVO> campList = new ArrayList<>();
        String sql = "SELECT cno, image, name, tel , address, hit, num "
                + "FROM (SELECT cno, image, name, tel , address, hit, rownum as num "
                + "FROM (SELECT /*+index(HC_CAMP_2 hc_camp_cno_pk)*/ cno, image, name, tel, address, hit "
                + "FROM HC_CAMP_2 " + searchQuery.get(searchType) + "ORDER BY " + orderQuery.get(orderType) + ")) "
                + "WHERE num BETWEEN ? AND ?";
        String allCampCnt = "SELECT COUNT(*) FROM HC_CAMP_2 " + searchQuery.get(searchType);
        String sub = "%%";
        if (searchType.equals("all")) {
            sub = "%" + searchKeyword + "%";
        }
        int rowSize = 10;
        int start = (rowSize * curPage) - (rowSize - 1);
        int end = rowSize * curPage;
        int totalCmp = 0;
        try {
            conn = dbconn.createConnection();
            ps = conn.prepareStatement(allCampCnt);
            ps.setString(1, "%" + searchKeyword + "%");
            ps.setString(2, sub);
            ResultSet rs = ps.executeQuery();
            rs.next();
            totalCmp = rs.getInt(1);
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + searchKeyword + "%");
            ps.setString(2, sub);
            ps.setInt(3, start);
            ps.setInt(4, end);
            rs = ps.executeQuery();
            while (rs.next()) {
                CampVO vo = new CampVO();
                vo.setCno(rs.getInt(1));
                vo.setImage(rs.getString(2));
                String[] addr = rs.getString(5).split(" ");
                vo.setName(createName(rs.getString(3), addr));
                vo.setTel(rs.getString(4));
                vo.setAddress(rs.getString(5));
                vo.setHit(rs.getInt(6));
                campList.add(vo);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dbconn.closeConnection(ps, conn);
        }
        return new Pagination(campList, curPage, totalCmp, 10);
    }

    public CampVO campDetail(int cno) {
        String sql = "SELECT image, name, address, homepage, tel, camp_type, period, day, camp_env FROM HC_CAMP_2 WHERE cno = ?";
        String updateHit = "UPDATE HC_CAMP_2 SET hit = hit + 1 WHERE cno = ?";
        CampVO campVO = new CampVO();
        try {
            conn = dbconn.createConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cno);
            ResultSet rs = ps.executeQuery();
            rs.next();
            campVO.setImage(rs.getString(1));
            campVO.setName(rs.getString(2));
            campVO.setAddress(rs.getString(3));
            campVO.setHomePage(rs.getString(4));
            campVO.setTel(rs.getString(5));
            campVO.setCampType(rs.getString(6));
            campVO.setPeriod(rs.getString(7));
            campVO.setDay(rs.getString(8));
            campVO.setCampEnv(rs.getString(9));
            ps = conn.prepareStatement(updateHit);
            ps.setInt(1, cno);
            ps.executeUpdate();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbconn.closeConnection(ps, conn);
        }
        return campVO;
    }

    private String createName(String name, String[] addr) {
        return "[" + addr[0] + " " + addr[1] + "] " + name;
    }

}