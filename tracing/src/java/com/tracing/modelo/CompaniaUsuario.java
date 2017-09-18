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
@Table(name = "COMPANIA_USUARIO")
public class CompaniaUsuario implements Serializable{
    
    @Id
    @Column(name = "CD_CMP_USRO")
    private Long cdCmpUsro;
    
    @ManyToOne
    @JoinColumn(name = "CD_COMPANIA")
    private Companias cdCompania;
    
    @ManyToOne
    @JoinColumn(name = "USUARIO")
    private Usuarios usuario;
    
    @Column(name = "ESTADO", insertable = false)
    private Boolean estado;

    public Long getCdCmpUsro() {
        return cdCmpUsro;
    }

    public void setCdCmpUsro(Long cdCmpUsro) {
        this.cdCmpUsro = cdCmpUsro;
    }

    public Companias getCdCompania() {
        return cdCompania;
    }

    public void setCdCompania(Companias cdCompania) {
        this.cdCompania = cdCompania;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
    
}
