package com.app.alquiler.service

import com.app.alquiler.model.Pedido
import com.app.alquiler.respository.ClienteRepository
import com.app.alquiler.respository.PedidoRepository
import com.app.alquiler.respository.TrajeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PedidoService {

    @Autowired
    lateinit var pedidoRepository: PedidoRepository

    @Autowired
    lateinit var clienteRepository: ClienteRepository

    @Autowired
    lateinit var trajeRepository: TrajeRepository

    fun list(): MutableList<Pedido> {
        return pedidoRepository.findAll()
    }

    fun save (pedido: Pedido): Pedido {
        try {
            clienteRepository.findById(pedido.clienteId)
                ?: throw Exception("El cliente${pedido.clienteId} no a sido encontrado")

            trajeRepository.findById(pedido.trajeId)
                ?: throw Exception("El traje${pedido.trajeId} no a sido encontrado")


            if(pedido.cantidad!! >0){
                return pedidoRepository.save(pedido)
            }
          else {
                throw Exception("cantidad mayor a 0")
            }
        }
        catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message, ex)
        }
    }

    fun update(pedido: Pedido): Pedido {
        try {
             val reponse = clienteRepository.findById(pedido.clienteId)
                ?: throw Exception("El cliente${pedido.clienteId} no a sido encontrado")

             val response = trajeRepository.findById(pedido.trajeId)
                ?: throw Exception("El traje${pedido.trajeId} no a sido encontrado")

            if(pedido.cantidad!! >0){
                return pedidoRepository.save(pedido)
            }
            else {
                throw Exception("cantidad mayor a 0")
            }
        }
        catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message, ex)
        }
    }

    fun updateDescription (pedido: Pedido): Pedido {
        try {
            if (pedido.cantidad!! >0){
                val response = pedidoRepository.findById(pedido.id)
                    ?: throw Exception()
                response.apply {
                    this.cantidad = pedido.cantidad
                    this.clienteId = pedido.clienteId
                    this.trajeId = pedido.trajeId
                }
                return pedidoRepository.save(response)
            }
                else{
                throw Exception("cantidad mayor a 0")
            }
        }
        catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message, ex)
        }
    }

    fun delete (id :Long?): Boolean{
        try{
            pedidoRepository.findById(id)
                ?: throw Exception ("id no existe")
            pedidoRepository.deleteById(id!!)
            return true

        }catch (ex:Exception){
            throw Exception()
        }

    }
}