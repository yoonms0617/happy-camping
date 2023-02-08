package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sist.util.DBConn;
import com.sist.vo.ItemQAVO;
import com.sist.vo.ItemReviewVO;
import com.sist.vo.ItemVO;

public class ItemDAO {

    private Connection conn;

    private PreparedStatement ps;

    private final DBConn dbConn;

    private List<List<Integer>> typeList = Arrays.asList(
            Arrays.asList(202, 203, 204, 207, 261, 215, 209, 276),
            Arrays.asList(211, 212, 217, 224),
            Arrays.asList(218, 219, 220),
            Arrays.asList(99, 68, 71, 69, 284, 283, 70, 285, 72),
            Arrays.asList(66, 63, 190, 67, 65, 64, 299),
            Arrays.asList(138, 83, 84, 85, 86, 303, 186),
            Arrays.asList(73, 74, 301, 82, 75, 78, 80, 300, 191, 315, 81),
            Arrays.asList(87, 298, 88, 89, 296, 297, 90, 91),
            Arrays.asList(189, 178, 179, 180, 269, 264, 181),
            Arrays.asList(295, 286, 292, 293, 290, 291, 94, 294, 137, 287, 289),
            Arrays.asList(246, 93, 248, 187, 192, 247, 350, 95, 302, 96),
            Arrays.asList(270, 271, 272, 274, 288, 273, 275, 318));

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
                itemVO.setIno(rs.getInt(1));
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
                itemVO.setIno(rs.getInt(1));
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

    //캠핑용품 상위카테고리//
    public List<ItemVO> itemCategoryList(int page, int type) {
        ArrayList<ItemVO> list = new ArrayList<>();
        int[] table = {201, 210, 282, 28, 25, 44, 26, 42, 92, 45, 47, 169};
        try {
            conn = dbConn.createConnection();
            String sql = "select ino, image, name, price, num " + "from (select ino, image, name, price, rownum as num "
                    + "from (select ino, image, name, price "
                    + "from hc_item_2 where icno in(select icno from hc_item_category_2 where pcode=" + table[type]
                    + "))) " + "where num between ? and ?";
            ps = conn.prepareStatement(sql);
            int rowSize = 20;
            int start = (rowSize * page) - (rowSize - 1);
            int end = rowSize * page;
            ps.setInt(1, start);
            ps.setInt(2, end);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ItemVO vo = new ItemVO();
                vo.setIno(rs.getInt(1));
                String mainImage = getMainImage(rs.getString(2));
                vo.setImage(mainImage);
                vo.setName(rs.getString(3));
                vo.setPrice(rs.getInt(4));
                list.add(vo);
            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
        return list;

    }

    public int itemTotalPage(int type) {
        int total = 0;
        int[] table = {201, 210, 282, 28, 25, 44, 26, 42, 92, 45, 47, 169};
        try {
            conn = dbConn.createConnection();
            String sql = "select ceil(count(*)/20.0) from hc_item_2 "
                    + "where icno in(select icno from hc_item_category_2 where pcode=" + table[type] + ")";
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            total = rs.getInt(1);
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
        return total;
    }

    //캠핑용품 하위카테고리//
    public List<ItemVO> itemCategoryList2(int page, int type, int type1) {
        List<ItemVO> list = new ArrayList<>();
        try {
            conn = dbConn.createConnection();
            String sql = "select ino, image, name, price, num " + "from (select ino, image, name, price, rownum as num "
                    + "from (select ino, image, name, price "
                    + "from hc_item_2 where icno in(select icno from hc_item_category_2 where code="
                    + this.typeList.get(type).get(type1) + "))) " + "where num between ? and ?";
            ps = conn.prepareStatement(sql);
            int rowSize = 20;
            int start = (rowSize * page) - (rowSize - 1);
            int end = rowSize * page;
            ps.setInt(1, start);
            ps.setInt(2, end);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ItemVO vo = new ItemVO();
                vo.setIno(rs.getInt(1));
                String mainImage = getMainImage(rs.getString(2));
                vo.setImage(mainImage);
                vo.setName(rs.getString(3));
                vo.setPrice(rs.getInt(4));
                list.add(vo);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
        return list;
    }

    public int itemTotlaPage2(int type, int type1) {
        int total = 0;
        try {
            conn = dbConn.createConnection();
            String sql = "select ceil(count(*)/20.0) from hc_item_2 "
                    + "where icno in(select icno from hc_item_category_2 where code=" + this.typeList.get(type).get(type1)
                    + ")";
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            total = rs.getInt(1);
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbConn.closeConnection(ps, conn);
        }
        return total;
    }

    private String getMainImage(String images) {
        String[] image = images.split(",");
        return image[0];
    }

  //캠핑용품 디테일// // 서치후 디테일
  	public ItemVO itemDetailData(int ino) {
  		ItemVO vo = new ItemVO();
  		try {
  			conn = dbConn.createConnection();
  			String sql = "SELECT ino, image, name,description, brand, price, like_cnt, jjim_cnt, sale "
  					+"FROM hc_item_2 WHERE ino=?";
  			ps = conn.prepareStatement(sql);
  			ps.setInt(1, ino);

  			ResultSet rs = ps.executeQuery();
  			rs.next();
  			vo.setIno(rs.getInt(1));
  			String image = rs.getString(2);
  		    image = image.substring(0, image.indexOf(","));
  		    vo.setImage(image);
  			vo.setName(rs.getString(3));
  			vo.setDescription(rs.getString(4));
  			vo.setBrand(rs.getString(5));
  			vo.setPrice(rs.getInt(6));
  			vo.setLikeCnt(rs.getInt(7));
  			vo.setJjimCnt(rs.getInt(8));
  			vo.setSale(rs.getInt(9));
  			rs.close();


  		} catch (Exception e) {
  			e.printStackTrace();
  		}finally {
  			dbConn.closeConnection(ps, conn);
  		}
  		return vo;
  	}



 // 검색후 아이템 출력
    public List<ItemVO> itemsearchData(int page, String ss)
    {
    	List<ItemVO> list=new ArrayList<>();
    	try
    	{
    		conn=dbConn.createConnection();
    		String sql="select ino,image,name,price,sale,num "
    				 + "from (select ino,image,name,price,sale,rownum as num "
    				 + "from (select ino,image,name,price,sale "
    				 + "from HC_ITEM_2 "
    				 + "where name LIKE '%'||?||'%')) "
    				 + "where num between ? and ?";
    	   ps=conn.prepareStatement(sql);
    	   int rowSize=20;
    	   int start=(rowSize*page)-(rowSize-1);
    	   int end=rowSize*page;
    	   ps.setString(1, ss);
		   ps.setInt(2, start);
		   ps.setInt(3, end);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   ItemVO vo=new ItemVO();
			   vo.setIno(rs.getInt(1));
			   String image =rs.getString(2);
			   image=image.substring(0,image.indexOf(",")); // 여러 이미지
			   vo.setImage(image);
			   vo.setName(rs.getString(3));
			   vo.setPrice(rs.getInt(4));
			   vo.setSale(rs.getInt(5));
			   list.add(vo);
		   }
		   rs.close();
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    	finally
    	{
    		dbConn.closeConnection(ps, conn);
    	}
    	return list;
    }
 // 검색후 아이템 수(수정)
    public int itemsearchTotal(String ss)
    {
    	int total=0;
 	   	try
 	    {
 		   conn=dbConn.createConnection();
 		   String sql="SELECT COUNT(*) FROM HC_ITEM_2 "
 				     +"WHERE name LIKE '%'||?||'%'";
 		   ps=conn.prepareStatement(sql);
 		   ps.setString(1, ss);
 		   ResultSet rs=ps.executeQuery();
 		   rs.next();
 		   total=rs.getInt(1);
 		   if(ss.equals(""))
 		   {
 			   total=0;
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
 	   return total;
    }
    // 검색후 아이템 페이지 수
    public int itemsearchTotalPage(String ss)
    {
    	int total=0;
 	   	try
 	    {
 		   conn=dbConn.createConnection();
 		   String sql="SELECT CEIL(COUNT(*)/20.0) FROM HC_ITEM_2 "
 				     +"WHERE name LIKE '%'||?||'%'";
 		   ps=conn.prepareStatement(sql);
 		   ps.setString(1, ss);
 		   ResultSet rs=ps.executeQuery();
 		   rs.next();
 		   total=rs.getInt(1);
 		   rs.close();
 	   }catch(Exception ex)
 	   {
 		   ex.printStackTrace();
 	   }
 	   finally
 	   {
 		   dbConn.closeConnection(ps, conn);
 	   }
 	   return total;
    }

 // 아이템 리뷰 리스트 //
   	public List<ItemReviewVO> itemReviewList(int page){
   		List<ItemReviewVO> list = new ArrayList<>();
   		try {
   			conn = dbConn.createConnection();
   			String sql = "select rno, subject, writer, TO_CHAR(created, 'YYYY-MM-DD'), hit, num "
   					+"from (select rno, subject, writer, created, hit, rownum as num "
   					+"from (select /*+index_desc(hc_item_review_2 HC_ITEM_REVIEW_RNO_PK)*/ rno, subject, writer,created, hit "
   					+"from hc_item_review_2)) "
   					+"where num between ? and ?";
   			ps = conn.prepareStatement(sql);
 			int rowSize = 10;
 			int start = (rowSize*page) - (rowSize-1);
 			int end = rowSize*page;
 			ps.setInt(1, start);
 			ps.setInt(2, end);
 			ResultSet rs = ps.executeQuery();
 			while(rs.next()) {
 				ItemReviewVO vo = new ItemReviewVO();
 				vo.setRno(rs.getInt(1));
 				vo.setSubject(rs.getString(2));
 				vo.setWriter(rs.getString(3));

 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 		}finally {
 			dbConn.closeConnection(ps, conn);
 		}
   		return list;
   	}


   	// 아이템 리뷰 인서트//
   	public void itemReviewInsert(ItemReviewVO vo) {
   		try {
   			conn = dbConn.createConnection();
   			String sql = "insert into hc_item_review_2(rno, writer, subject, content, pwd) "
   					+"values(hc_item_review_seq.nextval, ?,?,?,?)";
   			ps = conn.prepareStatement(sql);

   			ps.setString(1, vo.getWriter());
 			ps.setString(2, vo.getSubject());
 			ps.setString(3, vo.getContent());
 			ps.setString(4, vo.getPwd());

 			ps.executeUpdate();

 		} catch (Exception e) {
 			e.printStackTrace();
 		}finally {
 			dbConn.closeConnection(ps, conn);
 		}
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
  		   String sql ="insert into HC_ITEM_QA_2(qano,mid,title,content, password) "
  		   		+ "values(hc_item_qa_seq.nextval, ?,?,?,?) ";

  		   ps=conn.prepareStatement(sql);

  		   ps.setString(1, vo.getMid());
  		   ps.setString(2, vo.getTitle());
  		   ps.setString(3, vo.getContent());
  		   ps.setString(4, vo.getPassword());

  		   ps.executeUpdate();
  	   }catch(Exception ex)
  	   {
  		   ex.printStackTrace();
  	   }
  	   finally {
       	dbConn.closeConnection(ps, conn);
        }
     }

}
