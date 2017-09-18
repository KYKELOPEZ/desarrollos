package com.tracing.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author cjlop
 */
@Entity
@Table(name = "SEGUIMIENTOS")
public class Seguimientos implements Serializable {

    @Id
    @Column(name = "CD_SEGUIMIENTO")
    private Long cdSeguimiento;

    @ManyToOne
    @JoinColumn(name = "CD_CLIENTE")
    private Clientes cdCliete;

    @ManyToOne
    @JoinColumn(name = "USUARIO")
    private Usuarios usuario;

    @ManyToOne
    @JoinColumn(name = "CD_RAMO")
    private Ramos cdRamo;

    @ManyToOne
    @JoinColumn(name = "CD_PRODUCTO")
    private Productos cdProducto;

    @Temporal(TemporalType.DATE)
    @Column(name = "FC_REGISTRO", insertable = false)
    private Date fcRegistro;

    @Temporal(TemporalType.DATE)
    @Column(name = "FC_COTIZACION")
    private Date fcCotizacion;

    @Column(name = "RESULTADO")
    private String resultado;

    @ManyToOne
    @JoinColumn(name = "CD_ASEGURADORA")
    private Aseguradoras cdAseguradora;

    @ManyToOne
    @JoinColumn(name = "CD_FUENTE")
    private Fuente cdFuente;

    @Temporal(TemporalType.DATE)
    @Column(name = "FC_RENOVACION")
    private Date fcRenovacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FC_PROSPECTO")
    private Date fcProspecto;

    public Long getCdSeguimiento() {
        return cdSeguimiento;
    }

    public void setCdSeguimiento(Long cdSeguimiento) {
        this.cdSeguimiento = cdSeguimiento;
    }

    public Clientes getCdCliete() {
        return cdCliete;
    }

    public void setCdCliete(Clientes cdCliete) {
        this.cdCliete = cdCliete;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Ramos getCdRamo() {
        return cdRamo;
    }

    public void setCdRamo(Ramos cdRamo) {
        this.cdRamo = cdRamo;
    }

    public Productos getCdProducto() {
        return cdProducto;
    }

    public void setCdProducto(Productos cdProducto) {
        this.cdProducto = cdProducto;
    }

    public Date getFcRegistro() {
        return fcRegistro;
    }

    public void setFcRegistro(Date fcRegistro) {
        this.fcRegistro = fcRegistro;
    }

    public Date getFcCotizacion() {
        return fcCotizacion;
    }

    public void setFcCotizacion(Date fcCotizacion) {
        this.fcCotizacion = fcCotizacion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Aseguradoras getCdAseguradora() {
        return cdAseguradora;
    }

    public void setCdAseguradora(Aseguradoras cdAseguradora) {
        this.cdAseguradora = cdAseguradora;
    }

    public Fuente getCdFuente() {
        return cdFuente;
    }

    public void setCdFuente(Fuente cdFuente) {
        this.cdFuente = cdFuente;
    }

    public Date getFcRenovacion() {
        return fcRenovacion;
    }

    public void setFcRenovacion(Date fcRenovacion) {
        this.fcRenovacion = fcRenovacion;
    }

    public Date getFcProspecto() {
        return fcProspecto;
    }

    public void setFcProspecto(Date fcProspecto) {
        this.fcProspecto = fcProspecto;
    }
}
