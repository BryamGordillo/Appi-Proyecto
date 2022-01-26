package com.app.alquiler

import com.app.alquiler.service.UserService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AlquilerApplicationTests {

	@Autowired
	lateinit var userService: UserService

	@Test
	fun contextLoads() {
	}

}
