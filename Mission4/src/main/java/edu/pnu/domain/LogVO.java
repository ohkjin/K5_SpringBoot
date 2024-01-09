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
public class LogVO {
	private Integer id;
	private String method;
	private String sqlstring;
	private Date regidate;
	private int success;
}
