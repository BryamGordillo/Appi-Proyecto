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
        try {
            if (cliente.cedula.equals("") or  cliente.nombre.equals("") or cliente.celular.equals("")){


                throw Exception("campo vacio")
            }
            else
            {
                return clienteRepository.save(cliente)
            }
        }
        catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message, ex)
        }


    }

    fun update(cliente: Cliente): Cliente {
        return clienteRepository.save(cliente)

    }

    fun updateDescription (cliente: Cliente): Cliente {
            val response = clienteRepository.findById(cliente.id)
                ?: throw Exception()
            response.apply {
                this.cedula = cliente.cedula
                this.nombre = cliente.nombre
                this.celular = cliente.celular

            return clienteRepository.save(response)
        }

    }

    fun delete (id:Long): Boolean{
        clienteRepository.deleteById(id)
        return true
    }
}