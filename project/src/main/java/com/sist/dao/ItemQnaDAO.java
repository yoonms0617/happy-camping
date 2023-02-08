package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sist.util.DBConn;
import com.sist.vo.ItemQAVO;

public class ItemQnaDAO {
	private Connection conn;

    private PreparedStatement ps;

    private final DBConn dbConn;

    public ItemQnaDAO() {
        this.dbConn = DBConn.getInstance();
    }

	// 아이템 qna 리스트
   	public List<ItemQAVO> itemQnaList(int page){
   		List<ItemQAVO> list = new ArrayList<>();
   		try {
   			conn = dbConn.createConnection();
   			String sql = "select qano,title,mid,TO_CHAR(regdate,'YYYY-MM-DD'), hit,num "
   	                 +"from (select qano,title,mid,regdate, hit,rownum as num "
   	                 +"from (select /*+INDEX_DESC(HC_ITEM_QA_2 hc_item_qa_qano_pk)*/ qano,title,mid,regdate, hit "
   	                 +"from HC_ITEM_QA_2)) "
   	                 +"where num between ? and ?";
   			System.out.println("qna리스트 출력해 디비가져와");
   			ps = conn.prepareStatement(sql);
 			int rowSize = 10;
 			int start = (rowSize*page) - (rowSize-1);
 			int end = rowSize*page;
 			ps.setInt(1, start);
 			ps.setInt(2, end);
 			ResultSet rs = ps.executeQuery();
 			while(rs.next()) {
 				 ItemQAVO vo = new ItemQAVO();
 	              vo.setQano(rs.getInt(1));
 	              vo.setTitle(rs.getString(2));
 	              vo.setMid(rs.getString(3));
 	              vo.setDbday(rs.getDate(4));
 	              vo.setHit(rs.getInt(5));

 	              list.add(vo);
            }
 			rs.close();
 		} catch (Exception e) {
 			e.printStackTrace();
 		}finally {
 			dbConn.closeConnection(ps, conn);
 		}
   		return list;
   	}

   	 // 상품 QnA 총 페이지
     public int itemQnATotalPage() {
         int total=0;
         try {
            conn = dbConn.createConnection();
            String sql = "select ceil(count(*)/10.0) from HC_ITEM_QA_2";
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            total = rs.getInt(1);
            rs.close();
         } catch (Exception e) {
            e.printStackTrace();
         }finally {
         	dbConn.closeConnection(ps, conn);
         }
         return total;
      }

     // QnA 쓰기
     public void itemQnAInsert(ItemQAVO vo)
     {
  	   try
  	   {
  		  conn = dbConn.createConnection();
  		   String sql ="insert into HC_ITEM_QA_2(qano,mid,title,content, password, ino) "
  		   		+ "values(hc_item_qa_seq.nextval, ?,?,?,?,?) ";

  		   ps=conn.prepareStatement(sql);

  		   ps.setString(1, vo.getMid());
  		   ps.setString(2, vo.getTitle());
  		   ps.setString(3, vo.getContent());
  		   ps.setString(4, vo.getPassword());
  		   ps.setInt(5, vo.getIno());

  		   ps.executeUpdate();
  	   }catch(Exception ex)
  	   {
  		   ex.printStackTrace();
  	   }
  	   finally {
       	dbConn.closeConnection(ps, conn);
        }
     }

     // qna 상세보기
     public ItemQAVO itemQnADetail(int qano) {
    	 ItemQAVO vo = new ItemQAVO();
    	 try {
    		 conn = dbConn.createConnection();
    		 String sql = "update hc_item_qa_2 set hit = hit + 1 where qano = ?";
    		 ps = conn.prepareStatement(sql);
    		 ps.setInt(1, qano);
    		 ps.executeUpdate();
    		 ////////////////////////////조회수 증가
    		 sql = "select qano, title, mid, content, regdate "
    				 +"from hc_item_qa_2 where qano = ?";
    		 ps = conn.prepareStatement(sql);
    		 ps.setInt(1, qano);
    		 ResultSet rs = ps.executeQuery();
    		 rs.next();
    		 vo.setQano(rs.getInt(1));
    		 vo.setTitle(rs.getString(2));
    		 vo.setMid(rs.getString(3));
    		 vo.setContent(rs.getString(4));
    		 vo.setRegdate(rs.getDate(5));
    		 rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbConn.closeConnection(ps, conn);
		}
    	 return vo;

     }

     // qna 삭제
     public boolean itemQnaDelete(int qano, String pwd) {
    	 boolean bCheck = false;
    	 try {
    		 conn = dbConn.createConnection();
    	 } catch (Exception e) {
 			e.printStackTrace();
 		}finally {
 			dbConn.closeConnection(ps, conn);
    	 return bCheck;
     }


}
}
