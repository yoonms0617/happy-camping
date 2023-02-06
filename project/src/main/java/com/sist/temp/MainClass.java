package com.sist.temp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
public class MainClass {

	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement ps =null;
		// sql문장 둘중 하나가 틀리면 rollback을 하게 만든다
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			conn=DriverManager.getConnection(url,"hr","happy");
			conn.setAutoCommit(false); // 커밋을 수행하지 않게 한다
			String sql="insert into card values(1,'hong',30000) ";
			ps=conn.prepareStatement(sql);
			ps.executeUpdate(); // -> commit 취소 (setAutoCommit)

			sql="insert into point values(1,'park',300) ";
			ps=conn.prepareStatement(sql);
			ps.executeUpdate(); //commit

			conn.commit(); // 전체 저장
		}catch(Exception ex)
		{
			ex.printStackTrace();
			try {
				conn.rollback(); // 전체 취소
			}catch(Exception ex1) {}
		}
		finally
		{
			try
			{

				if(conn!=null)conn.close();
				if(ps!=null)ps.close();
			}catch (Exception e) {}
		}
	}
}
