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
@Table(name = "SEG_ESTADOS")
public class SegEstados implements Serializable{
    
    @Id
    @Column(name = "CD_ESTADO")
    private Long cdEstado;
    
    @Column(name = "NM_ESTADO")
    private String nmEstado;
    
    @Column(name = "ESTADO")
    private Boolean estado;

    public Long getCdEstado() {
        return cdEstado;
    }

    public void setCdEstado(Long cdEstado) {
        this.cdEstado = cdEstado;
    }

    public String getNmEstado() {
        return nmEstado;
    }

    public void setNmEstado(String nmEstado) {
        this.nmEstado = nmEstado;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
    
    
    
}
