package com.sist.dao;

import java.sql.*;
import java.util.*;

import com.sist.util.DBConn;
import com.sist.vo.CampVO;

public class CampDAO {

    private Connection conn;

    private PreparedStatement ps;

    private final DBConn dbconn;

    private Object contine;

    public CampDAO() {
        this.dbconn = DBConn.getInstance();
    }

    public List<CampVO> campItems() {

        String sql = "select cno, image, name, tel , address, num "
                + "from (select cno, image, name, tel , address, rownum as num "
                + "from (select /*+index_asc(HC_CAMP_2 hc_camp_cno_pk)*/cno, image, name, tel , address "
                + "from HC_CAMP_2 ))"
                + " where num between 1 and 15";

        List<CampVO> list = new ArrayList<CampVO>();
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
}