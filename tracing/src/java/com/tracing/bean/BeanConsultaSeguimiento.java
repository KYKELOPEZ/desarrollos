package com.tracing.bean;

import com.tracing.dao.SeguimientoEstadosDAO;
import com.tracing.dao.VSeguimientoDAO;
import com.tracing.modelo.SeguimientoEstados;
import com.tracing.modelo.VSeguimiento;
import com.tracing.util.Mensajes;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class BeanConsultaSeguimiento implements Bean, Serializable {

    private List<VSeguimiento> vistaSeguimiento;

    @EJB(beanName = "VSeguimientoDAOImpl")
    private VSeguimientoDAO vSeguimientoDAO;

    private SeguimientoEstados seguimientoEstados;
    private List<SeguimientoEstados> consultaSeguimiento;
    @EJB(beanName = "SeguimientoEstadosDAOImpl")
    private SeguimientoEstadosDAO seguimientoEstadosDAO;

    @PostConstruct
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
        find();
    }

    @Override
    public void find() {
        vistaSeguimiento = vSeguimientoDAO.findAllOrderBy("fcRegistro DESC");
    }

    @Override
    public void cancel() {

    }

    @Override
    public Boolean validate() {
        Boolean valor = true;
        return valor;
    }

    public void consultar(VSeguimiento vs) {
        try {
            consultaSeguimiento = seguimientoEstadosDAO.findByFk("WHERE t.cdSeguimiento.cdSeguimiento = " + vs.getCdSeguimiento() + " ORDER BY t.fcEstado desc");
            RequestContext.getCurrentInstance().execute("PF('consultar').show();");
        } catch (Exception e) {
            Mensajes.mensajeError(e.getLocalizedMessage());
        }
    }

    public VSeguimientoDAO getvSeguimientoDAO() {
        return vSeguimientoDAO;
    }

    public void setvSeguimientoDAO(VSeguimientoDAO vSeguimientoDAO) {
        this.vSeguimientoDAO = vSeguimientoDAO;
    }

    public SeguimientoEstados getSeguimientoEstados() {
        return seguimientoEstados;
    }

    public void setSeguimientoEstados(SeguimientoEstados seguimientoEstados) {
        this.seguimientoEstados = seguimientoEstados;
    }

    public List<VSeguimiento> getVistaSeguimiento() {
        return vistaSeguimiento;
    }

    public void setVistaSeguimiento(List<VSeguimiento> vistaSeguimiento) {
        this.vistaSeguimiento = vistaSeguimiento;
    }

    public List<SeguimientoEstados> getConsultaSeguimiento() {
        return consultaSeguimiento;
    }

    public void setConsultaSeguimiento(List<SeguimientoEstados> consultaSeguimiento) {
        this.consultaSeguimiento = consultaSeguimiento;
    }

}
