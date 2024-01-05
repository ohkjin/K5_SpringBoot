package edu.pnu.domain;

import java.util.Date;

public class MemberVObuilder{
	private Integer id;
	private String pass;
	private String name;
	private Date regidate;

	
	public MemberVObuilder() {}	
	public MemberVObuilder(Integer id, String name, String pass, Date regidate) {
		this.id=id;
		this.name=name;
		this.pass=pass;
		this.regidate = regidate;
	}
	public static MemberVObuilderClass builder() {
		return new MemberVObuilderClass();
	}
	
	static class MemberVObuilderClass{
		private Integer id;
		private String pass;
		private String name;
		private Date regidate;
	
		public MemberVObuilderClass id(Integer id) {
			this.id = id;
			return this;
		}
		public MemberVObuilderClass pass(String pass) {
			this.pass = pass;
			return this;
		}
		public MemberVObuilderClass name(String name) {
			this.name = name;
			return this;
		}
		public MemberVObuilderClass regidate(Date regidate) {
			this.regidate = regidate;
			return this;
		}
		
		public MemberVObuilder build() {
			return new MemberVObuilder(id,name,pass,regidate);
		}
		
		
	}
}
