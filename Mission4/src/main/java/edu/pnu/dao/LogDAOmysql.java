package edu.pnu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import edu.pnu.domain.LogVO;

@Repository
public class LogDAOmysql implements LogDAO {
	
	private Connection con = null;
	
	public LogDAOmysql(DataSource ds) throws SQLException{
		con = ds.getConnection();
	}

	@Override
	public List<LogVO> getLogs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogVO getLog() {
		// TODO Auto-generated method stub
		return null;
	}
//	private Integer id;
//	private String method;
//	private String sqlstring;
//	private Date regidate;
//	private short success;

	@Override
	public int makelog(String method,String sql,int success) {
//		Statement st = null;
//		sql = sql.replaceAll("'", "\'");
		String query = " INSERT INTO dblog (method, sqlstring, success) values('"+method+"','"+sql+"',"+success+")";
		try {
			st = con.createStatement();
			return st.executeUpdate(query);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(st!=null) st.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
}
