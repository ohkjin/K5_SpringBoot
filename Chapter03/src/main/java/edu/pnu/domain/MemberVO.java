package edu.pnu.domain;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class MemberVO {
	private Integer id;
	private String name;
	private String pass;
	private Date regidate;
}
