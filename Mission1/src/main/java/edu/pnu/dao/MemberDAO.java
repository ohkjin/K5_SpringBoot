package edu.pnu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberVO;

@Repository
public class MemberDAO {

	private Connection con;
	
	public MemberDAO(DataSource ds) throws SQLException {
		con = ds.getConnection();
	}
	
	public List<MemberVO> getMembers(){
		List<MemberVO> list = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM member ORDER BY id ASC ");
			while(rs.next()) {
				list.add(MemberVO.builder()
				.id(rs.getInt("id"))
				.name(rs.getString("name"))
				.pass(rs.getString("pass"))
				.regidate(rs.getDate("regidate")).build());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(st!=null) st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public MemberVO getMemberbyID(int id) {
		if(id==0) return null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM member WHERE id=? ";
		ps.executeQuery(sql);
		return getObject(id);		
	}
	
	public int getIndex(int id, List<MemberVO> list) {
		if(id==0) return 0;
		int idx =0;
		for(MemberVO s:list) {
			if(s.getId()==id)
				return idx;
			idx++;
		}
		return 0;		
	}
	
	public MemberVO getObject(int id, List<MemberVO> list) {
		if(id==0) return null;
		for(MemberVO s:list) {
			if(s.getId()==id)
				return s;
		}
		return null;		
	}
	
	

}


