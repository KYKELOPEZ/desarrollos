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
@Table(name = "COMPANIAS")
public class Companias implements Serializable{
    
    @Id
    @Column(name = "CD_COMPANIA")
    private Long cdCompania;
    
    @ManyToOne
    @JoinColumn(name = "CD_CIUDAD")
    private Ciudades cdCiudad;
    
    @Column(name = "NM_COMPANIA")
    private String nmCompania;
    
    @Column(name = "ESTADO" , insertable = false)
    private Boolean estado;

    public Long getCdCompania() {
        return cdCompania;
    }

    public void setCdCompania(Long cdCompania) {
        this.cdCompania = cdCompania;
    }

    public Ciudades getCdCiudad() {
        return cdCiudad;
    }

    public void setCdCiudad(Ciudades cdCiudad) {
        this.cdCiudad = cdCiudad;
    }

    public String getNmCompania() {
        return nmCompania;
    }

    public void setNmCompania(String nmCompania) {
        this.nmCompania = nmCompania;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
    
}
