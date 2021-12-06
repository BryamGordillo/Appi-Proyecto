package com.app.alquiler.service

import com.app.alquiler.model.Cliente
import com.app.alquiler.respository.ClienteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ClienteService {
    @Autowired
    lateinit var clienteRepository: ClienteRepository


    fun list(): List<Cliente> {
        return clienteRepository.findAll()
    }

    fun save (cliente: Cliente): Cliente {
        if (cliente.cedula.equals("")) {
            (cliente.nombre.equals(""))
            (cliente.celular.equals(""))


            throw Exception()
        }
        else
        {
            return clienteRepository.save(cliente)
        }

    }

    fun update(cliente: Cliente): Cliente {
        return clienteRepository.save(cliente)

    }

    fun updateDescription (cliente: Cliente): Cliente {
        try{
            val response = clienteRepository.findById(cliente.id)
                ?: throw Exception("El cliente${cliente.id} no a sido encontrado")
            response.apply {
                this.cedula = cliente.cedula
                this.nombre = cliente.nombre
                this.celular = cliente.celular
            }
            return clienteRepository.save(response)
        }
        catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message, ex)
        }

    }

    fun delete (id:Long): Boolean{
        clienteRepository.deleteById(id)
        return true
    }
}