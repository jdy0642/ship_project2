package com.ship.web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShipSpringbootVuetifyApplicationTests {

	@Test
	void contextLoads() {
		int a = (int) Math.random()*4+1;
		System.out.println(a);
	}

}
