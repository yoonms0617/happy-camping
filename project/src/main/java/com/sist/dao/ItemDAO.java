package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sist.util.DBConn;
import com.sist.vo.ItemVO;

public class ItemDAO {

    private Connection conn;

    private PreparedStatement ps;

    private final DBConn dbConn;
    
    private List<List<Integer>> typeList = Arrays.asList(
			Arrays.asList(202,203,204,207,261,215,209,276),
			Arrays.asList(211,212,217,224),
			Arrays.asList(218,219,220),
			Arrays.asList(99,68,71,69,284,283,70,285,72),
			Arrays.asList(66,63,190,67,65,64,299),
			Arrays.asList(138,83,84,85,86,303,186),
			Arrays.asList(73,74,301,82,75,78,80,300,191,315,81),
			Arrays.asList(87,298,88,89,296,297,90,91),
			Arrays.asList(189,178,179,180,269,264,181),
			Arrays.asList(295,286,292,293,290,291,94,294,137,287,289),
			Arrays.asList(246,93,248,187,192,247,350,95,302,96),
			Arrays.asList(270,271,272,274,288,273,275,318));

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

    private String getMainImage(String images) {
        String[] image = images.split(",");
        return image[0];
    }
    
////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////
	//캠핑용품 상위카테고리//
		public List<ItemVO> itemCategoryList(int page, int type) {
			ArrayList<ItemVO> list = new ArrayList<>();
			int[] table = { 201, 210, 282, 28, 25, 44, 26, 42, 92, 45, 47, 169 };
			try {
				conn = dbConn.createConnection();
				String sql = "select ino, image, name, price, num " + "from (select ino, image, name, price, rownum as num "
						+ "from (select ino, image, name, price "
						+ "from hc_item_2 where icno in(select icno from hc_item_category_2 where pcode=" + table[type]
						+ "))) " + "where num between ? and ?";
				//select ino, name, icno from hc_item_2 where icno>=2 and icno <=9;
				//System.out.println("listsql:"+sql);
				ps = conn.prepareStatement(sql);
				// ?에 값 채우기
				int rowSize = 20;
				int start = (rowSize * page) - (rowSize - 1);
				int end = rowSize * page;
				ps.setInt(1, start);
				ps.setInt(2, end);
				ResultSet rs = ps.executeQuery();
	
				while (rs.next()) {
					ItemVO vo = new ItemVO();
					vo.setIno(rs.getInt(1));
					String image = rs.getString(2);
					image = image.substring(0, image.indexOf(","));
					vo.setImage(image);
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
	
		public int itemTotlaPage(int type) {
			int total = 0;
			int[] table = { 201, 210, 282, 28, 25, 44, 26, 42, 92, 45, 47, 169 };
			try {
				conn = dbConn.createConnection();
				String sql = "select ceil(count(*)/20.0) from hc_item_2 "
						+ "where icno in(select icno from hc_item_category_2 where pcode=" + table[type] + ")";
				System.out.println("sql:" + sql);
				ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				rs.next();
				total = rs.getInt(1);
				//System.out.println("totalpage:"+total);
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				dbConn.closeConnection(ps, conn);
			}
			return total;
		}

////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////
	//캠핑용품 하위카테고리//
	public List<ItemVO> itemCategoryList2(int page, int type, int type1) {
		ArrayList<ItemVO> list = new ArrayList<>();
	
		try {
			conn = dbConn.createConnection();
			String sql = "select ino, image, name, price, num " + "from (select ino, image, name, price, rownum as num "
					+ "from (select ino, image, name, price "
					+ "from hc_item_2 where icno in(select icno from hc_item_category_2 where code="
					+ this.typeList.get(type).get(type1) + "))) " + "where num between ? and ?";
			// System.out.println("sql1:"+sql);
			ps = conn.prepareStatement(sql);
	
			// ? 에 값채우기
			int rowSize = 20;
			int start = (rowSize * page) - (rowSize - 1);
			int end = rowSize * page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs = ps.executeQuery();
	
			while (rs.next()) {
				ItemVO vo = new ItemVO();
				vo.setIno(rs.getInt(1));
				String image = rs.getString(2);
				image = image.substring(0, image.indexOf(","));
				vo.setImage(image);
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
			System.out.println("sql page2:" + sql);
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
			// System.out.println("totalpage:"+total);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConn.closeConnection(ps, conn);
		}
		return total;
	}

}
