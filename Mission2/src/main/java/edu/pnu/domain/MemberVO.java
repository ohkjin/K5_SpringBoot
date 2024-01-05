package edu.pnu.domain;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder //Chain coding 허용
//list.add(MemberVO.builder()
//.id(i)
//.name("name"+i)
//.pass("pass"+i)
//.regidate(new Date()).build());
public class MemberVO {
	
	private Integer id;
	private String pass;
	private String name;
	private Date regidate;
}
