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
@Table(name = "TELEFONOS")
public class Telefonos implements Serializable{
    
    @Id
    @Column(name = "CD_TELEFONO")
    private Long cdTelefono;
    
    @ManyToOne
    @JoinColumn(name = "CD_CLIENTE")
    private Clientes cdCliente;
    
    @Column(name = "NUM_TELEFONO")
    private String numTelefono;
    
    @Column(name = "ESTADO", insertable = false)
    private Boolean estado;
    
    @Column(name = "TP_TELEFONO")
    private String tpTelefono;

    public Long getCdTelefono() {
        return cdTelefono;
    }

    public void setCdTelefono(Long cdTelefono) {
        this.cdTelefono = cdTelefono;
    }

    public Clientes getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(Clientes cdCliente) {
        this.cdCliente = cdCliente;
    }

    public String getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getTpTelefono() {
        return tpTelefono;
    }

    public void setTpTelefono(String tpTelefono) {
        this.tpTelefono = tpTelefono;
    }
    
    
}
