package com.sist.dao;

import java.sql.*;
import java.util.*;

import com.sist.util.DBConn;
import com.sist.vo.*;
public class CartDAO {

	private Connection conn;
    private PreparedStatement ps;
    private final DBConn dbConn;

    public CartDAO() {
        this.dbConn = DBConn.getInstance();
    }
    
	public List<CartVO> goodsCartListData(String mid)
	{
		
		List<CartVO> list = new ArrayList<CartVO>();
		try
		{
			conn = dbConn.createConnection();
            String sql = "SELECT cno,i.ino,mid,image,name,price,quantity,i.sale "
            		+ "FROM hc_cart_2 c, hc_item_2 i "
            		+ "WHERE c.cno=i.ino "
            		+ "AND mid=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, mid);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
            	CartVO vo = new CartVO();
            	vo.setCno(rs.getInt(1));
            	vo.getIvo().setIno(rs.getInt(2));
            	vo.setMid(rs.getString(3));
            	vo.setImage(rs.getString(4));
            	vo.setName(rs.getString(5));
            	vo.setPrice(rs.getInt(6));
            	vo.setQuantity(rs.getInt(7));
            	vo.getIvo().setSale(rs.getInt(8));
            	list.add(vo);
            }
            rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			dbConn.closeConnection(ps, conn);
		}
		return list;
	}
	
	public void goodsCartInsert(CartVO vo)
	{
		try
		{
			conn = dbConn.createConnection();
            String sql = "INSERT INTO hc_cart_2 VALUES"
            		+ "(hc_cart_cno_seq_2.nextval,?,?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1, vo.getImage());
            ps.setString(2, vo.getName());
            ps.setInt(3, vo.getPrice());
            ps.setInt(4, vo.getQuantity());
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
	
	public void goodsCartSelectDelete(int cno)
	{
		try
		{
			conn=dbConn.createConnection();
			String sql = "DELETE FROM hc_cart_2 "
					+ "WHERE cno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cno);
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
	
	public void goodsCartAllDelete()
	{
		try
		{
			conn=dbConn.createConnection();
			String sql = "DELETE FROM hc_cart_2";
			ps=conn.prepareStatement(sql);
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

}
