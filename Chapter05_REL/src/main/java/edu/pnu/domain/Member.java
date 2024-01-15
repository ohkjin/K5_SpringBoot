package edu.pnu.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
//@ToString(exclude="boardList")
@JsonIncludeProperties("boardList")
@Builder @NoArgsConstructor @AllArgsConstructor
@Entity
public class Member {
	@Id
	@Column(name="MEMBER_ID")
	private String id;
	private String pass;
	private String name;
	private String role;
	
	//mappedBy: DB 수정권한이 없음을 주장
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Board> boardList = new ArrayList<Board>();
}
