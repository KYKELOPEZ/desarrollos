/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracing.bean;

import com.tracing.dao.CiudadesDAO;
import com.tracing.dao.CompaniasDAO;
import com.tracing.modelo.Ciudades;
import com.tracing.modelo.Companias;
import com.tracing.util.Mensajes;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author cjlop
 */
@ManagedBean
@ViewScoped
public class BeanCompania implements Bean, Serializable {

    private Companias companias;
    private Companias companiasSeleccion;
    @EJB(beanName = "CompaniasDAOImpl")
    private CompaniasDAO companiasDAO;
    private List<Companias> listaCompanias;

    private Ciudades ciudades;
    @EJB(beanName = "CiudadesDAOImpl")
    private CiudadesDAO ciudadesDAO;
    private List<Ciudades> listaCiudades;

    @PostConstruct
    @Override
    public void init() {
        cancel();
        findAll();
    }

    @Override
    public void create() {
        try {
            if (validate()) {
                companias.setCdCompania(companiasDAO.getMaxIdDos("cdCompania"));
                companias.setCdCiudad(ciudades);
                companiasDAO.create(companias);
                findAll();
                RequestContext.getCurrentInstance().execute("PF('wvRegistrar').hide();");
                Mensajes.mensajeGrabarCorrecto();

            }
        } catch (Exception e) {
            Mensajes.mensajeGrabarNoCorrecto();
        }
    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {
        try {
            companiasSeleccion.setCdCiudad(ciudadesDAO.findById(ciudades.getCdCiudad()));
            companiasDAO.update(companiasSeleccion);
            Mensajes.mensajeInformacion("Registro actualizado correctamente.");
            RequestContext.getCurrentInstance().execute("PF('wvActualizar').hide();");
        } catch (Exception e) {

        }
    }

    @Override
    public void findAll() {
        listaCiudades = ciudadesDAO.findAll();
        listaCompanias = companiasDAO.findByFk("WHERE t.estado = 1 order by t.cdCiudad.nombre, t.cdCompania");
    }

    @Override
    public void find() {

    }

    @Override
    public void cancel() {
        companias = new Companias();
        ciudades = new Ciudades();
    }

    @Override
    public Boolean validate() {
        Boolean valor = true;
        return valor;
    }

    public void seleccion(Companias cmp) {
        try {
            ciudades = cmp.getCdCiudad();
            companiasSeleccion = cmp;
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('wvActualidar').show();");
        } catch (Exception e) {
        }

    }
    
    public void eliminar(Companias cmp){
        try {
            cmp.setEstado(false);
            companiasDAO.update(cmp);
            Mensajes.mensajeError("El registro se inactivo");
            findAll();
        } catch (Exception e) {
        }
    }

    public Companias getCompanias() {
        return companias;
    }

    public void setCompanias(Companias companias) {
        this.companias = companias;
    }

    public Ciudades getCiudades() {
        return ciudades;
    }

    public void setCiudades(Ciudades ciudades) {
        this.ciudades = ciudades;
    }

    public List<Ciudades> getListaCiudades() {
        return listaCiudades;
    }

    public void setListaCiudades(List<Ciudades> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

    public List<Companias> getListaCompanias() {
        return listaCompanias;
    }

    public void setListaCompanias(List<Companias> listaCompanias) {
        this.listaCompanias = listaCompanias;
    }

    public Companias getCompaniasSeleccion() {
        return companiasSeleccion;
    }

    public void setCompaniasSeleccion(Companias companiasSeleccion) {
        this.companiasSeleccion = companiasSeleccion;
    }

}
