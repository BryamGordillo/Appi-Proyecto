package com.app.alquiler.service

import com.app.alquiler.model.Traje
import com.app.alquiler.respository.TrajeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class TrajeService {
    @Autowired
    lateinit var trajeRepository: TrajeRepository


    fun list(): List<Traje> {
        return trajeRepository.findAll()
    }

    fun save (traje:Traje): Traje {
        try {
            if (traje.descripcion.equals(""))
            {
                throw Exception("campo incompleto")
            }
            else
            {
                return trajeRepository.save(traje)
            }
        }
        catch (ex: Exception){
            throw ResponseStatusException( HttpStatus.NOT_FOUND,ex.message, ex)

        }

    }

    fun update(traje: Traje): Traje {
        return trajeRepository.save(traje)
    }

    fun updateDescription (traje: Traje): Traje {

            val response = trajeRepository.findById(traje.id)
                ?: throw Exception()
            response.apply {
                this.descripcion = traje.descripcion
            }
            return trajeRepository.save(response)

    }

    fun delete (id:Long): Boolean{
        trajeRepository.deleteById(id)
        return true
    }
}