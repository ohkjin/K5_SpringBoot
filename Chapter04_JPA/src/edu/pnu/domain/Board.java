package edu.pnu.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor	// in need to use Builder
@AllArgsConstructor //
@Entity
@Table(name="Board") //if none, name will be Board(not used often)
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	@Column(length = 30)
	private String title;
	private String writer;
	private String content;
	@Temporal(TemporalType.DATE) //TemporalType.TIME(시간) TemporalType.TIMESTAMP(날짜와 시간)
	private Date createDate; //camel notation to a_b
	private Long cnt;
}
