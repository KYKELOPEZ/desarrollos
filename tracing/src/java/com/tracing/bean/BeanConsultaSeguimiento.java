package com.tracing.bean;

import com.tracing.dao.SegEstadosDAO;
import com.tracing.dao.VSeguimientoDAO;
import com.tracing.modelo.SegEstados;
import com.tracing.modelo.VSeguimiento;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class BeanConsultaSeguimiento implements Bean, Serializable {

    private List<VSeguimiento> vistaSeguimiento;
    @EJB(beanName = "VSeguimientoDAOImpl")
    private VSeguimientoDAO vSeguimientoDAO;

    private List<SegEstados> segEstados;
    @EJB(beanName = "SegEstadosDAOImpl")
    private SegEstadosDAO segEstadosDAO;

    @Override
    public void init() {
        cancel();
        findAll();
    }

    @Override
    public void create() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }

    @Override
    public void findAll() {
        vistaSeguimiento = vSeguimientoDAO.findAll();
    }

    @Override
    public void find() {

    }

    @Override
    public void cancel() {
        
    }

    @Override
    public Boolean validate() {
        Boolean valor = true;
        return valor;
    }

}
