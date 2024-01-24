package edu.pnu.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Builder @AllArgsConstructor @NoArgsConstructor
@Entity
public class Member {
	@Id
	private String username;
	private String password;
	@Enumerated(EnumType.STRING)
//	@Builder.Default
	private Role role;
	private boolean enabled;
	
}
