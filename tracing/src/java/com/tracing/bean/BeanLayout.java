/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracing.bean;

import com.tracing.dao.MenuDAO;
import com.tracing.dao.MenuPerfilDAO;
import com.tracing.modelo.Menu;
import com.tracing.modelo.MenuPerfil;
import com.tracing.modelo.Usuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author cjlop
 */
@ManagedBean
@SessionScoped
public class BeanLayout implements Serializable {

    private MenuModel modelo;

    private Usuarios usuarios;

    private MenuPerfil menuPerfil;
    @EJB(beanName = "MenuPerfilDAOImpl")
    private MenuPerfilDAO menuPerfilDAO;
    private List<MenuPerfil> listaMenuPerfil;

    private Menu menu;
    @EJB(beanName = "MenuDAOImpl")
    private MenuDAO menuDAO;
    private List<Menu> listaMenu;
    private List<Menu> listamPerfil;

    @PostConstruct
    public void init() {
        modelo = new DefaultMenuModel();
        usuarios = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        findAll();
        listamPerfil = new ArrayList<Menu>();
        acceso();
    }

    public void logout() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath());
        } catch (Exception e) {

        }
    }

    public void acceso() {
        try {
            for (MenuPerfil mp : listaMenuPerfil) {
                listamPerfil.add(mp.getCdMenu());
            }
            for (Menu m : listaMenu) {
                for (Menu mp : listamPerfil) {
                    if (mp.getCdMenu() == m.getCdMenu()) {
                        if (m.getNivel() == 1) {
                            DefaultSubMenu firstSubmenu = new DefaultSubMenu(m.getDescripcion());
                            firstSubmenu.setIcon(m.getIcono());
                            for (Menu i : listamPerfil) {
                                Menu submenu = i.getMenCdMenu();
                                if (submenu != null) {
                                    if (submenu.getCdMenu() == m.getCdMenu()) {
                                        DefaultMenuItem item = new DefaultMenuItem(i.getDescripcion());
                                        item.setUrl(i.getUrl());
                                        item.setIcon(i.getIcono());
                                        firstSubmenu.addElement(item);
                                    }
                                }
                            }
                            modelo.addElement(firstSubmenu);
                        } else if (m.getNivel() == 0) {
                            DefaultMenuItem item = new DefaultMenuItem(m.getDescripcion());
                            item.setIcon(m.getIcono());
                            item.setUrl(m.getUrl());
                            modelo.addElement(item);
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public void findAll() {
        try {
            listaMenu = menuDAO.findByFk("where t.estado = 1 order by t.orden");
            listaMenuPerfil = menuPerfilDAO.findByFk("WHERE t.cdPerfil.cdPerfil = " + usuarios.getCdPerfil().getCdPerfil() + " order by t.cdMenu.orden");
        } catch (Exception e) {

        }

    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public MenuModel getModelo() {
        return modelo;
    }

    public void setModelo(MenuModel modelo) {
        this.modelo = modelo;
    }

}
