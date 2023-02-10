package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sist.util.DBConn;
import com.sist.vo.ItemQAReplyVO;
public class ItemQnaReDAO {
	private Connection conn;

    private PreparedStatement ps;

    private final DBConn dbConn;

    public ItemQnaReDAO() {
        this.dbConn = DBConn.getInstance();
    }

	public void IqReplyInsert(ItemQAReplyVO vo) {
		try {
			conn = dbConn.createConnection();
			String sql = "insert into hc_item_qa_reply_2(qarno, mid, content, qano) "
					+"values(hc_item_qa_reply_seq.nextval, ?,?,?)";

			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getMid());
			ps.setString(2, vo.getContent());
			ps.setInt(3, vo.getQano());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbConn.closeConnection(ps, conn);
		}
	}

	public List<ItemQAReplyVO> IqReplyList(int qarno){
		List<ItemQAReplyVO> list = new ArrayList<>();
		try {
			conn = dbConn.createConnection();
			String sql = "select qarno, qano, mid, content, TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI;SS') "
					+"from hc_item_qa_reply_2 where qano = ? "
					+"order by qarno desc";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qarno);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ItemQAReplyVO vo = new ItemQAReplyVO();
				vo.setQarno(rs.getInt(1));
				vo.setQano(rs.getInt(2));
				vo.setMid(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setDbday(rs.getString(5));
				System.out.println("rs.getString(5)="+rs.getString(5));
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
}
