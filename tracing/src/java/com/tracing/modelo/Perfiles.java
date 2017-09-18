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
@Table(name = "PERFILES")
public class Perfiles implements Serializable{
    
    @Id
    @Column(name = "CD_PERFIL")
    private Long cdPerfil;
    
    @Column(name = "NM_PERFIL")
    private String nmPerfil;
    
    @Column(name = "ESTADO", insertable = false)
    private Boolean estado;

    public Long getCdPerfil() {
        return cdPerfil;
    }

    public void setCdPerfil(Long cdPerfil) {
        this.cdPerfil = cdPerfil;
    }

    public String getNmPerfil() {
        return nmPerfil;
    }

    public void setNmPerfil(String nmPerfil) {
        this.nmPerfil = nmPerfil;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
    
}
