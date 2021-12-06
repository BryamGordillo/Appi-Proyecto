package com.app.alquiler.model

import javax.persistence.*

@Entity
@Table(name = "pedido")
class Pedido {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var cantidad: Long? = null
    @Column(name= "cliente_id")
    var clienteId: Long? = null
    @Column(name = "traje_id")
    var trajeId: Long? = null

}