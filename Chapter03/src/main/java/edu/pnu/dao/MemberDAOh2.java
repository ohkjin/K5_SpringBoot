package edu.pnu.dao;

import java.awt.Taskbar.State;
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
public class MemberDAOh2 implements MemberDAO{
	private Connection con;
	
	// DataSource: 다른곳의 사용처가 없으니 field 선언이 필요하지 않다 
	public MemberDAOh2(DataSource ds) throws SQLException {
		System.out.println("Dao");
		con = ds.getConnection();
		System.out.println("con");
	}
	
	public List<MemberVO> getMembers(){
		Statement st = null;
		ResultSet rs = null;
		List<MemberVO> list = new ArrayList<>();
		String query = " SELECT * FROM member ORDER BY id ASC ";
		try {
			// Creates Statement object
			st = con.createStatement();
			// Returns a single ResultSet Object=> 커서 생성
			rs = st.executeQuery(query);
			// 커서 프로세싱
			while(rs.next()) {
				list.add(MemberVO.builder()
						.id(rs.getInt("id"))
						.name(rs.getString("name"))
						.pass(rs.getString("pass"))
						.regidate(rs.getDate("regidate")).build());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(st!=null) st.close();
				if(rs!=null) rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public MemberVO getMemberbyID(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberVO vo = null;
		String query=" SELECT * FROM member WHERE id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				vo = MemberVO.builder()
						.id(rs.getInt("id"))
						.name(rs.getString("name"))
						.pass(rs.getString("pass"))
						.regidate(rs.getDate("regidate")).build();	
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null) ps.close();
				if(rs!=null) rs.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
				
		return vo;
	}
	
	@Override
	public int addMember(MemberVO vo) {
		if(vo.getName()==null||vo.getPass()==null) return 0;
		PreparedStatement ps = null;
		String query = null;
		if(vo.getId()!=null) query ="INSERT INTO member(name, pass,id) VALUES(?,?,?);";
		else query ="INSERT INTO member(name, pass) VALUES(?,?);";
		try {
			ps = con.prepareStatement(query);
			
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getPass());
			if(vo.getId()!=null) ps.setInt(3, vo.getId());
			return ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null) ps.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	@Override
	public int updateMember(MemberVO vo) {
		if(vo.getName()==null&&vo.getPass()==null) return 0;
		Statement st = null;
		String query2 = "";
//		String query = "UPDATE member SET ";
		String query = "UPDATE member SET ";
		if(vo.getName()!=null) query2 += " name='"+vo.getName()+"' ";
		if(query2!="") 	   	   query2 += " , ";
		if(vo.getPass()!=null) query2 += " pass='"+vo.getPass()+"' ";
		query += query2 + " WHERE id="+ vo.getId()+";";
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

	@Override
	public int removeMember(Integer id) {
		Statement st = null;
		String query = " DELETE FROM member WHERE id="+id;
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
