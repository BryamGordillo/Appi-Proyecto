package com.app.alquiler.service

import com.app.alquiler.model.Traje
import com.app.alquiler.model.User
import com.app.alquiler.respository.TrajeRepository
import com.app.alquiler.respository.`User Repository`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserService {
    @Autowired
    lateinit var userRepository: `User Repository`


    fun list(): List<User> {
        return userRepository.findAll()
    }

    fun getUser (username: String?): User {
        try {
            val response = userRepository.findByUsername(username)
                ?:throw Exception("no existe Usuario")
            return response
        }
        catch (ex: Exception){
            throw ResponseStatusException( HttpStatus.NOT_FOUND,ex.message, ex)
        }
    }
    fun update(user: User): User {
        return userRepository.save(user)
    }
}