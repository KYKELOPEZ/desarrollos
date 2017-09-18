/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracing.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author cjlop
 */
@Entity
@Table(name = "PRODUCTOS")
public class Productos implements Serializable{
    
    @Id
    @Column(name = "CD_PRODUCTO")
    private Long cdProducto;
    
    @ManyToOne
    @JoinColumn(name = "CD_RAMO")
    private Ramos cdRamo;
    
    @Column(name = "PRODUCTO")
    private String producto;
    
    @Column(name = "ESTADO")
    private Boolean estado;

    public Long getCdProducto() {
        return cdProducto;
    }

    public void setCdProducto(Long cdProducto) {
        this.cdProducto = cdProducto;
    }

    public Ramos getCdRamo() {
        return cdRamo;
    }

    public void setCdRamo(Ramos cdRamo) {
        this.cdRamo = cdRamo;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
    
}
