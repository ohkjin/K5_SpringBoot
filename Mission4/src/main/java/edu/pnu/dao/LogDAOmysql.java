package edu.pnu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

@Repository
public class LogDAOmysql implements LogDAO {
	
	private Connection con = null;
	
	public LogDAOmysql(DataSource ds) throws SQLException{
		con = ds.getConnection();
	}

	@Override
	public int makelog() {
		Statement st = null;
		String sql = " INSERT INTO "
		return 0;
	}
	
}
