package com.app.alquiler.service

import com.app.alquiler.model.Cliente
import com.app.alquiler.model.Pedido
import com.app.alquiler.model.Traje
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
class TrajeServiceTest {

    @InjectMocks
    lateinit var trajeService: TrajeService

    @Mock
    lateinit var trajeRepository: TrajeRepository

    val returnObject: Traje = Traje().apply {

        id= 1
        descripcion="Pastor"
    }
    val newObject: Traje = Traje().apply {

        id= 1
        descripcion="Pastor"

    }

    @Test
    fun saveIsCorrect(){
        Mockito.`when`(trajeRepository.save(Mockito.any(Traje::class.java))).thenReturn(returnObject)
        val response = trajeService.save(newObject)

        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.descripcion, newObject.descripcion)
    }

    val jsonString = File("./src/test/resources/Traje/crearTraje.json").readText(Charsets.UTF_8)
    val trajeMock = Gson().fromJson(jsonString, Traje::class.java)

    @Test
    fun saveTraje(){
        //Para actualizar
        Mockito.`when`(trajeRepository.save(Mockito.any(Traje::class.java))).thenReturn(trajeMock)
        val response = trajeService.save(trajeMock)

        Assertions.assertEquals(response.id, trajeMock.id)
        Assertions.assertEquals(response.descripcion, trajeMock.descripcion)
    }

    @Test
    fun saveTrajeFailedDescripcion() {
        Assertions.assertThrows(Exception::class.java) {
            trajeMock.apply { descripcion = "    " }
            Mockito.`when`(trajeRepository.save(Mockito.any(Traje::class.java))).thenReturn(trajeMock)
            trajeService.save(trajeMock)
        }
    }

    @Test
    fun updatedIdNotExitsFailed() {

        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(trajeRepository.findById(returnObject.id)).thenReturn(null)
            Mockito.`when`(trajeRepository.save(Mockito.any(Traje::class.java))).thenReturn(returnObject)
            trajeService.update(trajeMock)

        }
    }

    @Test
    fun updateIsFailedDescripcionIsNull() {
        Assertions.assertThrows(Exception::class.java) {
            trajeMock.apply {
                descripcion = " "
            }
            Mockito.`when`(trajeRepository.findById(trajeMock.id)).thenReturn(trajeMock)
            Mockito.`when`(trajeRepository.save(Mockito.any(Traje::class.java))).thenReturn(trajeMock)
            trajeService.update(trajeMock)

        }
    }

}