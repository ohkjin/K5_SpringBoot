package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.pnu.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

//	int updateMember(Member vo);
//
//	int removeMember(int id);
//
//	int addMember(Member vo);
//
//	Page<Member> getMembers();
//
//	Member getMemberbyID(int id);

}
