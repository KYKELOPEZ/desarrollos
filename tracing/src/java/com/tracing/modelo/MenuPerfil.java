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
@Table(name = "MENU_PERFIL")
public class MenuPerfil implements Serializable{
    
    @Id
    @Column(name = "CD_MENU_PERFIL")
    private Long cdMenuPerfil;
    
    @ManyToOne
    @JoinColumn(name = "CD_MENU")
    private Menu cdMenu;
    
    @ManyToOne
    @JoinColumn(name = "CD_PERFIL")
    private Perfiles cdPerfil;

    public Long getCdMenuPerfil() {
        return cdMenuPerfil;
    }

    public void setCdMenuPerfil(Long cdMenuPerfil) {
        this.cdMenuPerfil = cdMenuPerfil;
    }

    public Menu getCdMenu() {
        return cdMenu;
    }

    public void setCdMenu(Menu cdMenu) {
        this.cdMenu = cdMenu;
    }

    public Perfiles getCdPerfil() {
        return cdPerfil;
    }

    public void setCdPerfil(Perfiles cdPerfil) {
        this.cdPerfil = cdPerfil;
    }
    
    
}
