package edu.pnu.domain;

public class TestBuilder {
	public static void main(String[] args) {
		MemberVObuilder mvo = MemberVObuilder.builder()
				.id(null)
				.name(null)
				.pass(null)
				.regidate(null).build();
	}
	
}
