package edu.pnu.domain;

import java.util.Date;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString(exclude="member")
@Builder @NoArgsConstructor @AllArgsConstructor
@JsonIncludeProperties("member")
@Entity
public class Board {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	private String title;
	private String content;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createDate;
	private Long cnt;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="MEMBER_ID", nullable = false)
	private Member member;
	
	// CascadeType.ALL
	public void setMember(Member member) {
		this.member = member;
		member.getBoardList().add(this);
	}
}
