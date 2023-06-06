/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ut7pro.ut7pro.models;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "Orders")
@EntityListeners(AuditingEntityListener.class)
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "usuario_id")
    private String usuario_id; 
    @Column(name = "producto_id")
    private String producto_id;
    @Column(name = "fecha_creacion")
    private String fecha_creacion;

    public Orders() {

    }

    public Orders(long id, String usuario_id, String producto_id, String fecha_creacion) {
        this.id = id;
        this.usuario_id = usuario_id;
        this.producto_id = producto_id;
        this.fecha_creacion = fecha_creacion;
    }

    // Getters y setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getusuario_id() {
        return usuario_id;
    }

    public void setusuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getproducto_id() {
        return producto_id;
    }

    public void setproducto_id(String producto_id) {
        this.producto_id = producto_id;
    }

    public String getfecha_creacion() {
        return fecha_creacion;
    }

    public void setfecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
    
}
