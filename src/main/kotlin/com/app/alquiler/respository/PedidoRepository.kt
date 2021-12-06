package com.app.alquiler.respository

import com.app.alquiler.model.Pedido
import org.springframework.data.jpa.repository.JpaRepository

interface PedidoRepository:JpaRepository<Pedido,Long> {
    fun findById(id: Long?): Pedido?
}