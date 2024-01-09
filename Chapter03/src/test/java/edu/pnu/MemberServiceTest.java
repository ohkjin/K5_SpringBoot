package edu.pnu;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class MemberServiceTest {
	
	@Autowired
	private MemberService ms;
	
	@Test
	@Order(0)
	@DisplayName("데이터 삭제")
	public void test00() {
		for(int i = 100;i<=105;i++) {
			ms.removeMember(i);
		}
	}
	
	@Test
	@Order(1)
	@DisplayName("데이터 입력")
	public void test01() {
		for(int i = 100;i<=105;i++) {
			ms.addMember(MemberVO.builder()
					.id(i)
					.name("name"+i)
					.pass("qwer"+i)
					.regidate(new Date()).build());
		}
	}
	@Test
	@Order(2)
	@DisplayName("입력 데이터 변경")
	public void test02() {
		System.out.println("=".repeat(60));
		System.out.println(ms.getMemberbyID(100));
		ms.updateMember(MemberVO.builder()
				.id(100)
				.name("test1")
				.pass("test1").build());
		System.out.println(ms.getMemberbyID(100));
		System.out.println("=".repeat(60));
	}
	
	@Test
	@Order(3)
	@DisplayName("입력 데이터 가져오기")
	public void test03() {
		List<MemberVO> list = ms.getMembers();
		for (MemberVO m: list) {
			if(m.getId()==100) System.out.println("-".repeat(60));
			System.out.println(m);
		}
	}
	
	

}
