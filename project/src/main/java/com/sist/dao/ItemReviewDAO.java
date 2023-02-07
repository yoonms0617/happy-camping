package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sist.util.DBConn;
import com.sist.util.Pagination;
import com.sist.vo.ItemReviewVO;

public class ItemReviewDAO {

    private Connection conn;

    private PreparedStatement ps;

    private final DBConn dbConn;

    public ItemReviewDAO() {
    	this.dbConn = DBConn.getInstance();
    }

    public Pagination itemReview(int ino, int curPage) {
    	String sql = "select rno, writer, content, created, mid, ino, num "
    			+"from (select rno, writer, content, created, mid, ino, ROWNUM AS num "
    			+"from (select /*+index_desc(hc_item_review_2 HC_ITEM_REVIEW_RNO_PK)*/ rno, writer, content, created, ino, mid "
    			+"from hc_item_review_2 where ino = ?)) "
    			+"where num between ? and ?";
    	List<ItemReviewVO> list = new ArrayList<>();
    	 int rowSize = 10;
         int start = (rowSize * curPage) - (rowSize - 1);
         int end = rowSize * curPage;
         int totalPage = getTotalPage(ino, rowSize);

         try {
        	 conn = dbConn.createConnection();
        	 ps = conn.prepareStatement(sql);
        	 ps.setInt(1, ino);
        	 ps.setInt(2, start);
        	 ps.setInt(3, end);
        	 ResultSet rs = ps.executeQuery();
        	 while(rs.next()) {
        		 ItemReviewVO vo = new ItemReviewVO();
        		 vo.setRno(rs.getInt(1));
        		 vo.setWriter(rs.getString(2));
        		 vo.setContent(rs.getString(3));
        		 vo.setCreated(rs.getDate(4));
        		 vo.setMid(rs.getString(5));
        		 vo.setIno(rs.getInt(6));
        		 list.add(vo);
        	 }
        	 rs.close();
		} catch (Exception e) {
			 e.printStackTrace();
		}finally {
			dbConn.closeConnection(ps, conn);
		}
    	return new Pagination(list, curPage, totalPage, 10);
    }

    private int getTotalPage(int ino, int rowSize) {
    	String sql = "select count(*) from hc_item_review_2 where ino = ?";
    	int totalPage = 0;
    	try {
    		 conn = dbConn.createConnection();
        	 ps = conn.prepareStatement(sql);
        	 ps.setInt(1, ino);
        	 ResultSet rs = ps.executeQuery();
        	 rs.next();
        	 totalPage = rs.getInt(1);
        	 rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbConn.closeConnection(ps, conn);
		}
    	if (totalPage % rowSize != 0) {
            totalPage = totalPage / rowSize + 1;
        } else {
            totalPage = totalPage / rowSize;
        }

    	return totalPage;
    }

    public void writeItemReview(String mid, String content, int ino) {
    	String sql = "insert into hc_item_review_2 (rno, writer, content, mid, ino) "
    			+ "values (hc_item_review_seq.nextval, ?, ?, ?, ?)";
    	try {
    		 conn = dbConn.createConnection();
    		 ps = conn.prepareStatement(sql);
             ps.setString(1, mid);
             ps.setString(2, content);
             ps.setString(3, mid);
             ps.setInt(4, ino);
             ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbConn.closeConnection(ps, conn);
		}
    }

    public void updateItemReview(String content, int rno) {
    	String sql = "update hc_item_review_2 set content = ?, created = sysdate where rno = ?";
    	 try {
             conn = dbConn.createConnection();
             ps = conn.prepareStatement(sql);
             ps.setString(1, content);
             ps.setInt(2, rno);
             ps.executeUpdate();
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             dbConn.closeConnection(ps, conn);
         }
    }

    public void deleteItemReview(int rno) {
    	String sql = "delete from hc_item_review_2 where rno = ?";
    	try {
    		 conn = dbConn.createConnection();
    		 ps = conn.prepareStatement(sql);
             ps.setInt(1, rno);
             ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbConn.closeConnection(ps, conn);
		}
    }

}







