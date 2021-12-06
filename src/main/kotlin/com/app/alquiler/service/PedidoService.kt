package com.app.alquiler.service

import com.app.alquiler.model.Pedido
import com.app.alquiler.respository.PedidoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PedidoService {

    @Autowired
    lateinit var pedidoRepository: PedidoRepository


    fun list(): MutableList<Pedido> {
        return pedidoRepository.findAll()
    }

    fun save (pedido: Pedido): Pedido {

        if(pedido.cantidad!! > 0 || pedido.clienteId!! > 0 || pedido.trajeId!! > 0){
            throw Exception()
        }



        else
        {
            return pedidoRepository.save(pedido)
        }
        }

    fun update(pedido: Pedido): Pedido {
        return pedidoRepository.save(pedido)

    }

    fun updateDescription (pedido: Pedido): Pedido {
        try {
            val response = pedidoRepository.findById(pedido.id)
                ?: throw Exception("El cliente${pedido.id} no a sido encontrado")
            response.apply {
                this.cantidad = pedido.cantidad
                this.clienteId = pedido.clienteId
                this.trajeId = pedido.trajeId
            }
            return pedidoRepository.save(response)
        }
        catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message, ex)
        }
    }

    fun delete (id:Long): Boolean{
        pedidoRepository.deleteById(id)
        return true
    }
}