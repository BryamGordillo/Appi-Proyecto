package com.app.alquiler.respository

import com.app.alquiler.model.Cliente
import org.springframework.data.jpa.repository.JpaRepository

interface ClienteRepository:JpaRepository<Cliente,Long> {
    fun findById(id: Long?): Cliente?
}