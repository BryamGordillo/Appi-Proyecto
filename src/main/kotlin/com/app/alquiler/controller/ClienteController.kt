package com.app.alquiler.controller

import com.app.alquiler.model.Cliente
import com.app.alquiler.service.ClienteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
@RestController
@RequestMapping("/cliente")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT])
class ClienteController {
    @Autowired
    lateinit var clienteService: ClienteService

    @GetMapping
    fun list(): List<Cliente>{
        return clienteService.list()
    }

    @PostMapping
    fun save ( @RequestBody cliente: Cliente): Cliente {
        return clienteService.save(cliente)
    }

    @PutMapping
    fun update(@RequestBody cliente: Cliente): Cliente {
        return clienteService.update(cliente)
    }

    @PatchMapping
    fun updateDescription (@RequestBody cliente: Cliente): Cliente {
        return clienteService.updateDescription(cliente)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean{
        return clienteService.delete(id)
    }



}
