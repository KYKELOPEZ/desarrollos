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
@Table(name = "PROVINCIAS")
public class Provincias implements Serializable{
    
    @Id
    @Column(name = "CD_PROVINCIA")
    private Long cdProvincia;
    
    @Column(name = "PROVINCIA")
    private String provincia;
    
    @Column(name = "ESTADO", insertable = false)
    private Boolean estado;

    public Long getCdProvincia() {
        return cdProvincia;
    }

    public void setCdProvincia(Long cdProvincia) {
        this.cdProvincia = cdProvincia;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }


    
}
