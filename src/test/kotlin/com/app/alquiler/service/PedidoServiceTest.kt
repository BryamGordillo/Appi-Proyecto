package com.app.alquiler.service

import com.app.alquiler.model.Cliente
import com.app.alquiler.model.Pedido
import com.app.alquiler.model.Traje
import com.app.alquiler.respository.ClienteRepository
import com.app.alquiler.respository.PedidoRepository
import com.app.alquiler.respository.TrajeRepository
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class PedidoServiceTest {

    @InjectMocks
    lateinit var pedidoService: PedidoService

    @Mock
    lateinit var pedidoRepository: PedidoRepository

    @Mock
    lateinit var trajeRepository: TrajeRepository

    @Mock
    lateinit var clienteRepository: ClienteRepository

    val returnObject: Pedido = Pedido().apply {

        id= 1
        cantidad=1

    }
    val newObject: Pedido = Pedido().apply {

        id= 1
        cantidad=1

    }

    @Test
    fun saveIsCorrect(){
        Mockito.`when`(pedidoRepository.save(Mockito.any(Pedido::class.java))).thenReturn(returnObject)
        val response = pedidoService.save(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.cantidad, newObject.cantidad)

    }
    val jsonString = File("./src/test/resources/Pedido/crearPedido.json").readText(Charsets.UTF_8)
    val pedidoMock = Gson().fromJson(jsonString, Pedido::class.java)

    val jsonString1 = File("./src/test/resources/Traje/crearTraje.json").readText(Charsets.UTF_8)
    val trajeMock = Gson().fromJson(jsonString1, Traje::class.java)

    val jsonString2 = File("./src/test/resources/Cliente/crearCliente.json").readText(Charsets.UTF_8)
    val clienteMock = Gson().fromJson(jsonString2, Cliente::class.java)

    @Test
    fun savePedido(){
        //Para actualizar
        Mockito.`when`(pedidoRepository.save(Mockito.any(Pedido::class.java))).thenReturn(returnObject)
        val response = pedidoService.save(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.cantidad, newObject.cantidad)

    }

    @Test
    fun savePedidoFailedPedido() {
        Assertions.assertThrows(Exception::class.java) {
            pedidoMock.apply { cantidad = 0 }
            Mockito.`when`(pedidoRepository.save(Mockito.any(Pedido::class.java))).thenReturn(pedidoMock)
            pedidoService.save(pedidoMock)
        }
    }

}