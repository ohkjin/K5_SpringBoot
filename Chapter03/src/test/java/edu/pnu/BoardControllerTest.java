package edu.pnu;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@TestMethodOrder(OrderAnnotation.class)
public class BoardControllerTest {
	@Autowired
	private MockMvc mock;
	
	@Test
	@Order(2) //Test 순서
	public void testHello() throws Exception {
		//http://localhost:8080/hello?name=Dolly: 이와같은것을 아래처럼 객체를 만들어서 확인
		mock.perform(get("/hello").param("name", "Dolly"))
			.andExpect(status().isOk())
			.andExpect(content().string("Hello : Dolly"))
			.andDo(print());
	}
	
	@Test
	@Order(1)
	public void testHello2() throws Exception {
		//http://localhost:8080/hello?name=Test: 이와같은것을 아래처럼 객체를 만들어서 확인
		mock.perform(get("/hello").param("name","Test"))
			.andExpect(status().isOk())
			.andExpect(content().string("Hello : Test"))
			.andDo(print());
	}
}
