package com.app.alquiler.controller

import com.app.alquiler.model.Traje
import com.app.alquiler.service.TrajeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pedido")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT])

class PedidoController {

    @Autowired
    lateinit var trajeService: TrajeService

    @GetMapping
    fun list(): List<Traje> {
        return trajeService.list()
    }

    @PostMapping
    fun save ( @RequestBody traje: Traje): Traje {
        return trajeService.save(traje)
    }

    @PutMapping
    fun update(@RequestBody traje: Traje): Traje {
        return trajeService.update(traje)
    }

    @PatchMapping
    fun updateDescription (@RequestBody traje: Traje): Traje {
        return trajeService.updateDescription(traje)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean{
        return trajeService.delete(id)
    }
}