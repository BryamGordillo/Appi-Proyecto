package com.app.alquiler.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest {

    @Autowired
    lateinit var  userService: UserService

    @Test
    fun calcMultiplicationIfIsPair(){
        val response = userService.calcMultiplication(2,2)
        Assertions.assertEquals(4,response)
    }

    @Test
    fun calcMultiplicationIfIsNotPair(){
        val response = userService.calcMultiplication(2,4)
        Assertions.assertEquals(8,response)
    }

    @Test
    fun restNineIfIsLessTen(){
        val response = userService.reNine(9)
        Assertions.assertEquals(9, response)
    }
    @Test
    fun restNineIfIsHigherTen(){
        val response = userService.reNine(10)
        Assertions.assertEquals(1, response)
    }

    @Test
    fun subtactFromNextTen(){
        val response = userService.subtactFromNextTen(9)
        Assertions.assertEquals(1,response)
    }
    @Test
    fun validaCedula(){
        val response = userService.validaCedula("0150089985")
        Assertions.assertEquals(true , response)
    }
}