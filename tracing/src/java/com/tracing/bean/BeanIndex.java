/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracing.bean;

import com.tracing.dao.UsuariosDAO;
import com.tracing.modelo.Usuarios;
import com.tracing.util.Mensajes;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author cjlop
 */
@ManagedBean
@ViewScoped
public class BeanIndex implements Serializable {

    private Usuarios usuarios;
    @EJB(beanName = "UsuariosDAOImpl")
    private UsuariosDAO usuariosDAO;

    private String usuario;
    private String clave;

    @PostConstruct
    public void init() {
        usuarios = new Usuarios();
    }

    public void login() {
        String pantalla = "";
        
        if (usuariosDAO.findByFk("where t.usuario = '" + usuario.toUpperCase() + "' and t.clave = '" + clave.toUpperCase() + "'").size() > 0) {
            try{
            usuarios = usuariosDAO.findByFk("where t.usuario = '" + usuario.toUpperCase() + "' and t.clave = '" + clave.toUpperCase() + "'").get(0);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuarios);
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/layouts/inicio.xhtml");
            }catch(Exception e){
                
            }
        } else {
            Mensajes.mensajeError("Usuario y/o Contraseña incorrectos");
            
        }

    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

}
