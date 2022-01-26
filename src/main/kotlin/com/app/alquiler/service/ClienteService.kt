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
            cliente.cedula?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("Campo CEDULA Vacio")

            cliente.nombre?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("Campo NOMBRE Vacio")

            cliente.celular?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("Campo CELULAR Vacio")

            return clienteRepository.save(cliente)
        }
        catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun update(cliente: Cliente): Cliente {
        try {
            val response = clienteRepository.findById(cliente.id)
                ?: throw Exception()
            cliente.cedula?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("Campo CEDULA Vacio")

            cliente.nombre?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("Campo NOMBRE Vacio")

            cliente.celular?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("Campo CELULAR Vacio")

            return clienteRepository.save(cliente)

        }
        catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun updateDescription (cliente: Cliente): Cliente {
        try {
            cliente.cedula?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("Campo CEDULA Vacio")

            cliente.nombre?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("Campo NOMBRE Vacio")

            cliente.celular?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("Campo CELULAR Vacio")

            val response = clienteRepository.findById(cliente.id)
                ?: throw Exception()
            response.apply {
                this.cedula = cliente.cedula
                this.nombre = cliente.nombre
                this.celular = cliente.celular

                return clienteRepository.save(response)
            }
        }
        catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun delete (id :Long?): Boolean{
        try{
            clienteRepository.findById(id)
                ?: throw Exception ("id no existe")
            clienteRepository.deleteById(id!!)
            return true

        }catch (ex:Exception){
            throw Exception()
        }

    }
}