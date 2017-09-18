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
@Table(name = "CIUDADES")
public class Ciudades implements Serializable {

    @Id
    @Column(name = "CD_CIUDAD")
    private Long cdCiudad;

    @ManyToOne
    @JoinColumn(name = "CD_PROVINCIA")
    private Provincias cdProvincia;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "ESTADO", insertable = false)
    private Boolean estado;

    public Long getCdCiudad() {
        return cdCiudad;
    }

    public void setCdCiudad(Long cdCiudad) {
        this.cdCiudad = cdCiudad;
    }

    public Provincias getCdProvincia() {
        return cdProvincia;
    }

    public void setCdProvincia(Provincias cdProvincia) {
        this.cdProvincia = cdProvincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
    
}
