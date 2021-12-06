package com.app.alquiler.model

import javax.persistence.*

@Entity
@Table(name = "traje")
class Traje {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var descripcion: String? = null

}