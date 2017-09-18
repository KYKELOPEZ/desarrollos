/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracing.bean;

import com.tracing.dao.RamosDAO;
import com.tracing.modelo.Ramos;
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
public class BeanRamos implements Bean, Serializable {

    private Ramos ramos;
    private Ramos seleccionRamos;
    @EJB(beanName = "RamosDAOImpl")
    private RamosDAO ramosDAO;
    private List<Ramos> listaRamos;
    private List<Ramos> listaSeleccionRamos;

    @PostConstruct
    @Override
    public void init() {
        cancel();
        findAll();
    }

    @Override
    public void create() {
        if (validate()) {
            try {
                ramos.setCdRamo(ramosDAO.getMaxIdDos("cdRamo"));
                ramosDAO.create(ramos);
                findAll();
                cancel();
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('wvRamos').hide();");
                Mensajes.mensajeGrabarCorrecto();
            } catch (Exception e) {
                Mensajes.mensajeGrabarNoCorrecto();
            }
        }
    }

    public void seleccionar(Ramos rm) {
        try {
            seleccionRamos = rm;
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('wvActualizar').show();");
        } catch (Exception e) {
            Mensajes.mensajeError(e.getMessage());
        }
    }

    public void desactivar(Ramos rm) {
        try {
            rm.setEstado(false);
            ramosDAO.update(rm);
            findAll();
            Mensajes.mensajeInformacion("El ramo " + rm.getRamo() + " fue inactivado");
        } catch (Exception e) {
            Mensajes.mensajeError(e.getMessage());
        }
    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {
        try {
            ramosDAO.update(seleccionRamos);
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('wvActualizar').hide();");
            Mensajes.mensajeInformacion("Registro actualizado correctamente");
        } catch (Exception e) {
            Mensajes.mensajeError(e.getMessage());
        }
    }

    @Override
    public void findAll() {
        listaRamos = ramosDAO.findByFk("Where t.estado = 1 order by t.ramo");
    }

    @Override
    public void find() {

    }

    @Override
    public void cancel() {
        ramos = new Ramos();
        seleccionRamos = new Ramos();
    }

    @Override
    public Boolean validate() {
        Boolean valor = true;
        return valor;
    }

    public void eliminarSeleccion() {
        for (Ramos rm : listaSeleccionRamos) {
            rm.setEstado(false);
            ramosDAO.update(rm);
        }
        findAll();
        Mensajes.mensajeInformacion("Se ha inactivado los ramos seleccionados.");
    }

    public Ramos getRamos() {
        return ramos;
    }

    public void setRamos(Ramos ramos) {
        this.ramos = ramos;
    }

    public List<Ramos> getListaRamos() {
        return listaRamos;
    }

    public void setListaRamos(List<Ramos> listaRamos) {
        this.listaRamos = listaRamos;
    }

    public Ramos getSeleccionRamos() {
        return seleccionRamos;
    }

    public void setSeleccionRamos(Ramos seleccionRamos) {
        this.seleccionRamos = seleccionRamos;
    }

    public List<Ramos> getListaSeleccionRamos() {
        return listaSeleccionRamos;
    }

    public void setListaSeleccionRamos(List<Ramos> listaSeleccionRamos) {
        this.listaSeleccionRamos = listaSeleccionRamos;
    }

}
