package edu.pnu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberVO;

@Repository
public class MemberDAOmysql implements MemberDAO {
//	@Autowired 이미 생성자가 있어서 필요없다
	private Connection con;
	@Autowired
	private LogDAO log;
	
	public MemberDAOmysql(DataSource ds) throws SQLException {
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
	
	public int removeMember(Integer id) {
		Statement st = null;
		String sql = "DELETE FROM member WHERE id="+id;
		try {
			st = con.createStatement();
			return st.executeUpdate(sql);
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
	
	public int addMember(MemberVO vo) {
		int success = 0;
		if(vo.getName()==null||vo.getPass()==null) return 0;
		Statement st = null;
		String id1 = "";
		String id2 = "";
		String sql = " INSERT INTO member("+id1+"name,pass)";
			   sql+= " VALUES (" +id2+"'"+vo.getPass()+"','"+vo.getName()+"')";
		if(vo.getId()!=null) {
			id1 = "id,";
			id2 = String.valueOf(vo.getId())+",";
		}
		try {
			st = con.createStatement();
			success = st.executeUpdate(sql);
			log.makelog("addMember",sql,success);
			return success;
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


