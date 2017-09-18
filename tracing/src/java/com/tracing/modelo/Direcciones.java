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
@Table(name = "DIRECCIONES")
public class Direcciones implements Serializable{
    
    @Id
    @Column(name = "CD_DIRECCION")
    private Long cdDireccion;
    
    @ManyToOne
    @JoinColumn(name = "CD_CIUDAD")
    private Ciudades cdCiudad;
    
    @ManyToOne
    @JoinColumn(name = "CD_CLIENTE")
    private Clientes cdCliente;
    
    @Column(name = "DIRECCION")
    private String direccion;
    
    @Column(name = "REFERENCIA")
    private String referencia;
    
    @Column(name = "ESTADO")
    private Boolean estado;

    public Long getCdDireccion() {
        return cdDireccion;
    }

    public void setCdDireccion(Long cdDireccion) {
        this.cdDireccion = cdDireccion;
    }

    public Ciudades getCdCiudad() {
        return cdCiudad;
    }

    public void setCdCiudad(Ciudades cdCiudad) {
        this.cdCiudad = cdCiudad;
    }

    public Clientes getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(Clientes cdCliente) {
        this.cdCliente = cdCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
}
