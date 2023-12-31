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

//@Repository
public class MemberDAOH2Impl implements MemberDAO {

	private Connection con;
	//@AutoWired : 생성자가 유일할때는 생략가능
	public MemberDAOH2Impl(DataSource ds) throws SQLException {
		con = ds.getConnection();
	}
	
	public int updateMember(MemberVO vo) {
//------PS의 경우 동적 쿼리를 사용하기 힘들다-----//
//		PreparedStatement ps = null;
//		String sql = "UPDATE member"
//				+ " SET name=?,pass=?"
//				+ " WHERE id=?";
//			ps = con.prepareStatement(sql);
//			ps.setString(1, vo.getName());
		
		if (vo.getPass() == null && vo.getName() == null)
			return 0;
		
		Statement st = null;
		String sql = "UPDATE member SET ";
		if (vo.getName() != null)	sql += (" name='" + vo.getName() + "' ");
		if (vo.getPass() != null)	sql += (",pass='" + vo.getPass() + "' ");
		sql += ("WHERE id=" + vo.getId());
		try {
			st = con.createStatement();
			return st.executeUpdate(sql);
		}catch(Exception e) {
			e.getStackTrace();
		}finally {
			try {
				if(st!=null) st.close();
			}catch(SQLException e) {
				e.getStackTrace();
			}
		}
		return 0;
	}
	
	public int removeMember(Integer id) {
		Statement st = null;
		String sql = "DELETE FROM member WHERE id="+id;
		try {
			st = con.createStatement();
			return st.executeUpdate(sql);
		}catch(Exception e) {
			e.getStackTrace();
		}finally {
			try {
				if(st!=null) st.close();
			}catch(SQLException e) {
				e.getStackTrace();
			}
		}
		return 0;
	}
	
	public int addMember(MemberVO vo) {
		if(vo.getName()==null||vo.getPass()==null) return 0;
		Statement st = null;
		String sql = " INSERT INTO member(id,name,pass)";
			   sql+= " VALUES (" +vo.getId()+",'"+vo.getPass()+"','"+vo.getName()+"')";
		try {
			st = con.createStatement();
			return st.executeUpdate(sql);
		}catch(Exception e) {
			e.getStackTrace();
		}finally {
			try {
				if(st!=null) st.close();
			}catch(SQLException e) {
				e.getStackTrace();
			}
		}
		return 0;
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
	
	public MemberVO getMemberbyID(Integer id) {
		if(id==null) return null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberVO vo = null;
		String sql = " SELECT * FROM member WHERE id=? ";
		try {
			ps= con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				vo = MemberVO.builder()
						.id(id)
						.name(rs.getString("name"))
						.pass(rs.getString("name"))
						.regidate(rs.getDate("regidate")).build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null) ps.close();
				if(rs!=null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return vo;		
	}

}


