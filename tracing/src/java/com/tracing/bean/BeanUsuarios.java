/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracing.bean;

import com.tracing.dao.PerfilesDAO;
import com.tracing.dao.UsuariosDAO;
import com.tracing.modelo.Perfiles;

import com.tracing.modelo.Usuarios;
import com.tracing.util.Mensajes;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author cjlop
 */
@ManagedBean
@ViewScoped
public class BeanUsuarios implements Serializable {

    private Usuarios usuarios;
    private Usuarios seleccionUsuarios;
    @EJB(beanName = "UsuariosDAOImpl")
    private UsuariosDAO usuariosDAO;
    private List<Usuarios> listaUsuarios;

    private Perfiles perfiles;
    @EJB(beanName = "PerfilesDAOImpl")
    private PerfilesDAO perfilesDAO;
    private List<Perfiles> listaPerfil;

    @PostConstruct
    public void init() {
        usuarios = new Usuarios();
        perfiles = new Perfiles();
        seleccionUsuarios = new Usuarios();
        listaUsuarios = usuariosDAO.findByFk("WHERE t.estado = 1 ORDER BY t.usuario");
        listaPerfil = perfilesDAO.findByFk("where t.estado = 1");
    }

    public void grabar() {
        try {
            usuarios.setClave(usuarios.getUsuario());
            usuariosDAO.create(usuarios);
            init();
        } catch (Exception e) {
            Mensajes.mensajeGrabarNoCorrecto();
            Mensajes.mensajeError(e.getMessage());
        }
    }

    public void actualizar() {
        try {
            seleccionUsuarios.setCdPerfil(perfilesDAO.findById(perfiles.getCdPerfil()));
            usuariosDAO.update(seleccionUsuarios);
            Mensajes.mensajeInformacion("Se actualizo la informacion correctamente.");
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('wvUsuarios').hide();");
            init();
        } catch (Exception e) {
        }
    }

    public void desactivar(Usuarios usro) {
        try {
            usro.setEstado(false);
            usuariosDAO.update(usro);
            Mensajes.mensajeInformacion("Usuario Inactivo");
            init();
        } catch (Exception e) {

        }
    }

    public void seleccion(Usuarios usro) {
        perfiles = usro.getCdPerfil();
        seleccionUsuarios = usro;
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('wvActualizar').show();");
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public List<Usuarios> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Usuarios getSeleccionUsuarios() {
        return seleccionUsuarios;
    }

    public void setSeleccionUsuarios(Usuarios seleccionUsuarios) {
        this.seleccionUsuarios = seleccionUsuarios;
    }

    public Perfiles getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(Perfiles perfiles) {
        this.perfiles = perfiles;
    }

    public List<Perfiles> getListaPerfil() {
        return listaPerfil;
    }

    public void setListaPerfil(List<Perfiles> listaPerfil) {
        this.listaPerfil = listaPerfil;
    }

}
