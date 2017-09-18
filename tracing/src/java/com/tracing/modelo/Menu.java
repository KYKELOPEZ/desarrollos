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
@Table(name = "MENU")
public class Menu implements Serializable{
    
    @Id
    @Column(name = "CD_MENU")
    private Long cdMenu;
    
    @ManyToOne
    @JoinColumn(name = "MEN_CD_MENU")
    private Menu menCdMenu;
    
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Column(name = "ORDEN")
    private int orden;
    
    @Column(name = "NIVEL")
    private int nivel;
    
    @Column(name = "ESTADO", insertable = false)
    private Boolean estado;
    
    @Column(name = "URL")
    private String url;
    
    @Column(name = "ICONO")
    private String icono;

    public Long getCdMenu() {
        return cdMenu;
    }

    public void setCdMenu(Long cdMenu) {
        this.cdMenu = cdMenu;
    }

    public Menu getMenCdMenu() {
        return menCdMenu;
    }

    public void setMenCdMenu(Menu menCdMenu) {
        this.menCdMenu = menCdMenu;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }
    
    
    
}
