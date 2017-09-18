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
@Table(name = "CLIENTES")
public class Clientes implements Serializable{
    
    
    @Id
    @Column(name = "CD_CLIENTE")
    private Long cdCliente;
    
    @Column(name = "NOMBRES")
    private String nombres;
       
    @Column(name = "APELLIDOS")
    private String apellidos;
    
    @Column(name = "TP_CLIENTE")
    private String tpCliente;
    
    @Column(name = "TP_IDENTIFICACION")
    private String tpIdentificacion;
    
    @Column(name = "IDENTIFICACION")
    private String identificacion;
    
    @Column(name = "USUARIO")
    private String usaurio;

    public Long getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(Long cdCliente) {
        this.cdCliente = cdCliente;
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

    public String getTpCliente() {
        return tpCliente;
    }

    public void setTpCliente(String tpCliente) {
        this.tpCliente = tpCliente;
    }

    public String getTpIdentificacion() {
        return tpIdentificacion;
    }

    public void setTpIdentificacion(String tpIdentificacion) {
        this.tpIdentificacion = tpIdentificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getUsaurio() {
        return usaurio;
    }

    public void setUsaurio(String usaurio) {
        this.usaurio = usaurio;
    }

  
    
}
