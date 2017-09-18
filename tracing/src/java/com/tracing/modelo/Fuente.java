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
import javax.persistence.Table;

/**
 *
 * @author cjlop
 */
@Entity
@Table
public class Fuente implements Serializable{
    
    @Id
    @Column(name = "CD_FUENTE")
    private Long cdFuente;
    
    @Column(name = "DEC_FUENTE")
    private String decFuente;
    
    @Column(name = "ESTADO", insertable = false)
    private Boolean estado;

    public Long getCdFuente() {
        return cdFuente;
    }

    public void setCdFuente(Long cdFuente) {
        this.cdFuente = cdFuente;
    }

    public String getDecFuente() {
        return decFuente;
    }

    public void setDecFuente(String decFuente) {
        this.decFuente = decFuente;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
    
}
