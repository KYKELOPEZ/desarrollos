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
@Table(name = "RAMOS")
public class Ramos implements Serializable{
    
    
    @Id
    @Column(name = "CD_RAMO")
    private Long cdRamo;
    
    @Column(name = "RAMO")
    private String ramo;
    
    @Column(name = "ALIAS")
    private String alias;
    
    @Column(name = "ESTADO", insertable = false)
    private Boolean estado;

    public Long getCdRamo() {
        return cdRamo;
    }

    public void setCdRamo(Long cdRamo) {
        this.cdRamo = cdRamo;
    }

    public String getRamo() {
        return ramo;
    }

    public void setRamo(String ramo) {
        this.ramo = ramo;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
}
