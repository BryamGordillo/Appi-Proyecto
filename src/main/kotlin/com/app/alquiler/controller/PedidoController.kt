package com.app.alquiler.controller

import com.app.alquiler.model.Pedido
import com.app.alquiler.model.Traje
import com.app.alquiler.service.PedidoService
import com.app.alquiler.service.TrajeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pedido")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT])

class PedidoController {

    @Autowired
    lateinit var pedidoService: PedidoService

    @GetMapping
    fun list(): List<Pedido> {
        return pedidoService.list()
    }

    @PostMapping
    fun save ( @RequestBody pedido: Pedido): Pedido {
        return pedidoService.save(pedido)
    }

    @PutMapping
    fun update(@RequestBody pedido: Pedido): Pedido {
        return pedidoService.update(pedido)
    }

    @PatchMapping
    fun updateDescription (@RequestBody pedido: Pedido): Pedido {
        return pedidoService.updateDescription(pedido)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean{
        return pedidoService.delete(id)
    }
}