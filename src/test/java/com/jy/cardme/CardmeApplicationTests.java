package com.jy.cardme;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.config.location=" +
		"/config/application.yml"
)
class CardmeApplicationTests {

	@Test
	void contextLoads() {
	}

}
