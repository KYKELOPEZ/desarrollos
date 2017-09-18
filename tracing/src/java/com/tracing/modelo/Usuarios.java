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
@Table(name = "USUARIOS")
public class Usuarios implements Serializable{
    
    @Id
    @Column(name = "USUARIO")
    private String usuario;
    
    @ManyToOne
    @JoinColumn(name = "CD_PERFIL")
    private Perfiles cdPerfil;
    
    @Column(name = "CLAVE")
    private String clave;
    
    @Column(name = "NOMBRES")
    private String nombres;
    
    @Column(name = "APELLIDOS")
    private String apellidos;
    
    @Column(name = "ESTADO", insertable = false)
    private Boolean estado;
    
    @Column(name = "BLOQUEO")
    private Boolean bloqueo;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Perfiles getCdPerfil() {
        return cdPerfil;
    }

    public void setCdPerfil(Perfiles cdPerfil) {
        this.cdPerfil = cdPerfil;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Boolean getBloqueo() {
        return bloqueo;
    }

    public void setBloqueo(Boolean bloqueo) {
        this.bloqueo = bloqueo;
    }

   
    
}
