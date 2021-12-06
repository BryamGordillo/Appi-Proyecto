package com.app.alquiler.respository


import com.app.alquiler.model.Traje
import org.springframework.data.jpa.repository.JpaRepository

interface TrajeRepository:JpaRepository< Traje,Long> {
    fun findById(id: Long?): Traje?
}