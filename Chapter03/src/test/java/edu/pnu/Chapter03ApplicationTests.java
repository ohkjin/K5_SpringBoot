package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
class Chapter03ApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		System.out.println("test");
	}
	
	@Test
	public void mytest01() {
		System.out.println("test1");
	}
	
//	@Test
//	public void testHello() throws Exception {
//		mockMvc.perform(get("/hello").param("name","Dolly"))
//		.andExpect(status().isOk)
//		System.out.println("test1");
//	}
	

}
