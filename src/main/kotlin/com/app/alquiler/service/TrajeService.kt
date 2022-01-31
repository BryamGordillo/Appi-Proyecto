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
            traje.descripcion?.takeIf {it.trim().isNotEmpty()}
                ?:throw java.lang.Exception("Campo DESCRIPCION Vacio")

            return trajeRepository.save(traje)
        }
        catch (ex: Exception){
            throw ResponseStatusException( HttpStatus.NOT_FOUND,ex.message, ex)
        }
    }

    fun update(traje: Traje): Traje {
        try {
            val response = trajeRepository.findById(traje.id)
                ?: throw Exception()
            traje.descripcion?.takeIf {it.trim().isNotEmpty()}
                ?:throw java.lang.Exception("Campo DESCRIPCION Vacio")

            return trajeRepository.save(traje)
        }
        catch (ex: Exception){
            throw ResponseStatusException( HttpStatus.NOT_FOUND,ex.message, ex)
        }
    }

    fun updateDescription (traje: Traje): Traje {
        try {
            traje.descripcion?.takeIf {it.trim().isNotEmpty()}
                ?:throw java.lang.Exception("Campo DESCRIPCION Vacio")

            val response = trajeRepository.findById(traje.id)
                ?: throw Exception()
            response.apply {
                this.descripcion = traje.descripcion
            }
            return trajeRepository.save(response)
        }
        catch (ex: Exception){
            throw ResponseStatusException( HttpStatus.NOT_FOUND,ex.message, ex)
        }
    }
    fun validarTraje(categorias: String):Boolean{
        for (i in listaCategorias){
            if (categorias == i){
                return true
            }
        }
        return false

    }

    fun delete (id :Long?): Boolean{
        try{
            trajeRepository.findById(id)
                ?: throw Exception ("id no existe")
            trajeRepository.deleteById(id!!)
            return true

        }catch (ex:Exception){
            throw Exception()
        }

    }

}