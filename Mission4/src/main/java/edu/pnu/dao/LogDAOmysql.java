package edu.pnu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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


	@Override
	public int makelog() {
		Statement st = null;
		String sql = " INSERT INTO "
		return 0;
	}
	
}
