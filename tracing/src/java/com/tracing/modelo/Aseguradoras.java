/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracing.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author cjlop
 */
@Entity
@Table(name = "ASEGURADORAS")
public class Aseguradoras implements Serializable {

    @Id
    @Column(name = "CD_ASEGURADORA")
    private Long cdAseguradora;
    
    @Column(name = "ASEGURADORA")
    private String aseguradora;
    
    @Column(name = "ALIAS")
    private String alias;
    
    @Column(name = "ESTADO", insertable = false)
    private Boolean estado;

    public Long getCdAseguradora() {
        return cdAseguradora;
    }

    public void setCdAseguradora(Long cdAseguradora) {
        this.cdAseguradora = cdAseguradora;
    }

    public String getAseguradora() {
        return aseguradora;
    }

    public void setAseguradora(String aseguradora) {
        this.aseguradora = aseguradora;
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
