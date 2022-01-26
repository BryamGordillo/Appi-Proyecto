package com.app.alquiler.service

import com.app.alquiler.model.Cliente
import com.app.alquiler.respository.ClienteRepository
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class ClienteServiceTest {

    @InjectMocks
    lateinit var clienteService: ClienteService

    @Mock
    lateinit var clienteRepository: ClienteRepository

    val returnObject: Cliente = Cliente().apply {

        id= 1
        cedula="0150089985"
        nombre="Mayra"
        celular="0984328172"
    }
    val newObject: Cliente = Cliente().apply {

        id= 1
        cedula="0150089985"
        nombre="Mayra"
        celular="0984328172"
    }

    @Test
    fun saveIsCorrect(){
        Mockito.`when`(clienteRepository.save(Mockito.any(Cliente::class.java))).thenReturn(returnObject)
        val response = clienteService.save(newObject)

        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.cedula, newObject.cedula)
        Assertions.assertEquals(response.nombre, newObject.nombre)
        Assertions.assertEquals(response.celular, newObject.celular)
    }

    val jsonString = File("./src/test/resources/Cliente/crearCliente.json").readText(Charsets.UTF_8)
    val clienteMock = Gson().fromJson(jsonString, Cliente::class.java)

    @Test
    fun saveCliente(){
        //Para actualizar
        Mockito.`when`(clienteRepository.save(Mockito.any(Cliente::class.java))).thenReturn(clienteMock)
        val response = clienteService.save(clienteMock)
        Assertions.assertEquals(response.id, clienteMock.id)
        Assertions.assertEquals(response.cedula, clienteMock.cedula)
        Assertions.assertEquals(response.nombre, clienteMock.nombre)
        Assertions.assertEquals(response.celular, clienteMock.celular)
    }

    @Test
    fun saveClienteFailedCedula() {
        Assertions.assertThrows(Exception::class.java) {
            clienteMock.apply { cedula = "    " }
            Mockito.`when`(clienteRepository.save(Mockito.any(Cliente::class.java))).thenReturn(clienteMock)
            clienteService.save(clienteMock)
        }
    }

    @Test
    fun saveClienteFailedNombre() {
        Assertions.assertThrows(Exception::class.java) {
            clienteMock.apply { nombre = "    " }
            Mockito.`when`(clienteRepository.save(Mockito.any(Cliente::class.java))).thenReturn(clienteMock)
            clienteService.save(clienteMock)
        }
    }

    @Test
    fun saveClienteFailedCelular() {
        Assertions.assertThrows(Exception::class.java) {
            clienteMock.apply { celular = "    " }
            Mockito.`when`(clienteRepository.save(Mockito.any(Cliente::class.java))).thenReturn(clienteMock)
            clienteService.save(clienteMock)
        }
    }
}