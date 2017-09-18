package com.tracing.bean;

import com.tracing.dao.CompaniaUsuarioDAO;
import com.tracing.dao.CompaniasDAO;
import com.tracing.dao.UsuariosDAO;
import com.tracing.modelo.CompaniaUsuario;
import com.tracing.modelo.Companias;
import com.tracing.modelo.Usuarios;
import com.tracing.util.Mensajes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class BeanUsuarioCompania implements Bean, Serializable {

    private CompaniaUsuario companiaUsuario;
    @EJB(beanName = "CompaniaUsuarioDAOImpl")
    private CompaniaUsuarioDAO companiaUsuarioDAO;
    private List<CompaniaUsuario> listaCompaniasUsuario;

    private Companias companias;
    @EJB(beanName = "CompaniasDAOImpl")
    private CompaniasDAO companiasDAO;
    private List<Companias> listaCompanias;
    private List<Companias> seleccionaCompanias;

    private Usuarios usuarios;
    @EJB(beanName = "UsuariosDAOImpl")
    private UsuariosDAO usuariosDAO;
    private List<Usuarios> listaUsuarios;

    @PostConstruct
    @Override
    public void init() {
        cancel();
        findAll();
    }

    @Override
    public void create() {
        try {
            for (CompaniaUsuario cu : listaCompaniasUsuario) {
                companiaUsuarioDAO.delete(cu);
            }
            Long codigoCmpUsro = companiaUsuarioDAO.getMaxIdDos("cdCmpUsro");
            usuarios = usuariosDAO.findByFk("where t.usuario = '"+usuarios.getUsuario() +"'").get(0);
            for (Companias cmp : seleccionaCompanias) {
                companiaUsuario.setCdCmpUsro(codigoCmpUsro);
                companiaUsuario.setCdCompania(cmp);
                companiaUsuario.setUsuario(usuarios);
                companiaUsuarioDAO.create(companiaUsuario);
                companiaUsuario = new CompaniaUsuario();
                codigoCmpUsro++;
            }
            Mensajes.mensajeGrabarCorrecto();
        } catch (Exception e) {

        }
    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }

    @Override
    public void findAll() {
        listaCompanias = companiasDAO.findByFk("where t.estado = 1 order by t.nmCompania");
        listaUsuarios = usuariosDAO.findByFk("where t.estado = 1");
    }

    @Override
    public void find() {

    }

    @Override
    public void cancel() {
        usuarios = new Usuarios();
        listaCompaniasUsuario = new ArrayList<CompaniaUsuario>();
        companiaUsuario = new CompaniaUsuario();
    }

    @Override
    public Boolean validate() {
        Boolean valor = true;
        return valor;
    }

    public void seleccionar() {
        listaCompaniasUsuario = companiaUsuarioDAO.findByFk("where t.usuario.usuario = '" + usuarios.getUsuario() + "' and t.estado = 1");
        seleccionaCompanias = new ArrayList<Companias>();
        for (CompaniaUsuario cu : listaCompaniasUsuario) {
            seleccionaCompanias.add(cu.getCdCompania());
        }

    }

    public CompaniaUsuario getCompaniaUsuario() {
        return companiaUsuario;
    }

    public void setCompaniaUsuario(CompaniaUsuario companiaUsuario) {
        this.companiaUsuario = companiaUsuario;
    }

    public Companias getCompanias() {
        return companias;
    }

    public void setCompanias(Companias companias) {
        this.companias = companias;
    }

    public List<Companias> getListaCompanias() {
        return listaCompanias;
    }

    public void setListaCompanias(List<Companias> listaCompanias) {
        this.listaCompanias = listaCompanias;
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

    public List<Companias> getSeleccionaCompanias() {
        return seleccionaCompanias;
    }

    public void setSeleccionaCompanias(List<Companias> seleccionaCompanias) {
        this.seleccionaCompanias = seleccionaCompanias;
    }

    public List<CompaniaUsuario> getListaCompaniasUsuario() {
        return listaCompaniasUsuario;
    }

    public void setListaCompaniasUsuario(List<CompaniaUsuario> listaCompaniasUsuario) {
        this.listaCompaniasUsuario = listaCompaniasUsuario;
    }

}
