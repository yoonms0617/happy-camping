package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sist.util.DBConn;
import com.sist.vo.MemberVO;

public class MemberDAO {

    private Connection conn;

    private PreparedStatement ps;

    private final DBConn dbConn;

    public MemberDAO() {
        this.dbConn = DBConn.getInstance();
    }
    // 1. 회원가입
    public void memberInsert(MemberVO vo)
    {
    	try {
    		conn=dbConn.createConnection();
    		// 일반회원, 관리자
    		String sql ="insert into HC_MEMBER_2 values(?,?,?,?,?,?,?,?,?,?,'일반회원') ";
    		ps=conn.prepareStatement(sql);
    		ps.setString(1, vo.getMid());
    		ps.setString(2, vo.getPassword());
    		ps.setString(3, vo.getName());
    		ps.setString(4, vo.getEmail());
    		ps.setString(5, vo.getTel());
    		ps.setString(6, vo.getBirth());
    		ps.setString(7, vo.getPostcode());
    		ps.setString(8, vo.getHomeAddr());
    		ps.setString(9, vo.getDetailAddr());
    		ps.setString(10, vo.getSex());
    		ps.executeUpdate();

    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	finally
    	{
    		dbConn.closeConnection(ps, conn);

    	}
    }

    public boolean memberIdCheck(String userId) {
        String sql = "SELECT COUNT(*) FROM HC_MEMBER_2 WHERE mid = ?";
        boolean flag = true;
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int result = rs.getInt(1);
            if (result == 0) {
                flag = false;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
        return flag;
    }

    public boolean memberEmailCheck(String email) {
        String sql = "SELECT COUNT(*) FROM HC_MEMBER_2 WHERE email = ?";
        boolean flag = true;
        try {
            conn = dbConn.createConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int result = rs.getInt(1);
            if (result == 0) {
                flag = false;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
        return flag;
    }

    public MemberVO memberLogin(String mid, String password)
    {
    	MemberVO vo = new MemberVO();
    	try
    	{
    		conn=dbConn.createConnection();
    		String sql ="select count(*) from HC_MEMBER_2 where mid=?";
    		ps=conn.prepareStatement(sql);
    		ps.setString(1, mid);
    		ResultSet rs= ps.executeQuery();
    		rs.next();
    		int count =rs.getInt(1);
    		rs.close();

    		if(count==0)
    		{
    			vo.setMsg("NOID");
    		}
    		else
    		{
    			sql ="select mid, password, role,name from HC_MEMBER_2 where mid=?";
    			ps=conn.prepareStatement(sql);
    			ps.setString(1, mid);
    			rs=ps.executeQuery();
    			rs.next();
    			String db_mid =rs.getString(1);
    			String db_password=rs.getString(2);
    			String db_role=rs.getString(3);
    			String db_name=rs.getString(4);
    			rs.close();

    		    if(db_password.equals(password))
    		    {

    		    	vo.setMsg("OK");
    		    	vo.setMid(db_mid);
    		    	vo.setPassword(db_password);
    		    	vo.setRole(db_role);
    		    	vo.setName(db_name);
    		    }
    		    else
    		    {
    		    	vo.setMsg("NOPWD");
    		    }
    		}
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	finally
    	{
    		dbConn.closeConnection(ps, conn);
    	}
    	return vo;
    }

    // 아이디 찾기 => 전화번호로 찾기
    public String memberIdtelfind(String name,String tel) {
        String msg="";
        try {
           conn = dbConn.createConnection();
           String sql = "select count(*) from HC_MEMBER_2 where name =? and tel=?";
           ps = conn.prepareStatement(sql);
           ps.setString(1, name);
           ps.setString(2, tel);
           ResultSet rs = ps.executeQuery();
           rs.next();
           int count = rs.getInt(1);
           rs.close();

           if(count == 0) {
        	   msg="NO";
           }else {
              sql = "SELECT RPAD(substr(mid,1,3),LENGTH(mid), '*') from HC_MEMBER_2 "
                  + "WHERE name =? and tel=?";
              ps = conn.prepareStatement(sql);
              ps.setString(1, name);
              ps.setString(2, tel);
              rs=ps.executeQuery();
              rs.next();
              msg = rs.getString(1);
              rs.close();
           }
        } catch (Exception e)
        {
           e.printStackTrace();
        }finally {
           dbConn.closeConnection(ps, conn);
        }
        return msg;
     }

    // 아이디 찾기 -> 이메일로 찾기
    public String memberIdemailfind(String name,String email) {
        String msg="";
        try {
            conn = dbConn.createConnection();
            String sql = "select count(*) from HC_MEMBER_2 where name =? and email=?";
           ps = conn.prepareStatement(sql);
           ps.setString(1, name);
           ps.setString(2, email);
           ResultSet rs = ps.executeQuery();
           rs.next();
           int count = rs.getInt(1);
           rs.close();

           if(count == 0) {
        	   msg="NO";
           }else {
              sql = "SELECT RPAD(substr(mid,1,3),LENGTH(mid), '*') from HC_MEMBER_2 "
                    +"where name =? and email=?";
              ps = conn.prepareStatement(sql);
              ps.setString(1, name);
              ps.setString(2, email);
              rs=ps.executeQuery();
              rs.next();
              msg = rs.getString(1);
              rs.close();
           }
        }
        catch (Exception e)
        {
           e.printStackTrace();
        }
        finally
        {
           dbConn.closeConnection(ps, conn);
        }
        return msg;
     }
}
























