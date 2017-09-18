/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracing.modelo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cjlop
 */
@Entity
@Table(name = "V_SEGUIMIENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VSeguimiento.findAll", query = "SELECT v FROM VSeguimiento v"),
    @NamedQuery(name = "VSeguimiento.findByUsuario", query = "SELECT v FROM VSeguimiento v WHERE v.usuario = :usuario"),
    @NamedQuery(name = "VSeguimiento.findByAsesor", query = "SELECT v FROM VSeguimiento v WHERE v.asesor = :asesor"),
    @NamedQuery(name = "VSeguimiento.findByCdCliente", query = "SELECT v FROM VSeguimiento v WHERE v.cdCliente = :cdCliente"),
    @NamedQuery(name = "VSeguimiento.findByIdentificacion", query = "SELECT v FROM VSeguimiento v WHERE v.identificacion = :identificacion"),
    @NamedQuery(name = "VSeguimiento.findByCliNombres", query = "SELECT v FROM VSeguimiento v WHERE v.cliNombres = :cliNombres"),
    @NamedQuery(name = "VSeguimiento.findByCliApellidos", query = "SELECT v FROM VSeguimiento v WHERE v.cliApellidos = :cliApellidos"),
    @NamedQuery(name = "VSeguimiento.findByCdProvincia", query = "SELECT v FROM VSeguimiento v WHERE v.cdProvincia = :cdProvincia"),
    @NamedQuery(name = "VSeguimiento.findByProvincia", query = "SELECT v FROM VSeguimiento v WHERE v.provincia = :provincia"),
    @NamedQuery(name = "VSeguimiento.findByCdCiudad", query = "SELECT v FROM VSeguimiento v WHERE v.cdCiudad = :cdCiudad"),
    @NamedQuery(name = "VSeguimiento.findByCiudad", query = "SELECT v FROM VSeguimiento v WHERE v.ciudad = :ciudad"),
    @NamedQuery(name = "VSeguimiento.findByCdDireccion", query = "SELECT v FROM VSeguimiento v WHERE v.cdDireccion = :cdDireccion"),
    @NamedQuery(name = "VSeguimiento.findByDireccion", query = "SELECT v FROM VSeguimiento v WHERE v.direccion = :direccion"),
    @NamedQuery(name = "VSeguimiento.findByTelefono", query = "SELECT v FROM VSeguimiento v WHERE v.telefono = :telefono"),
    @NamedQuery(name = "VSeguimiento.findByCorreo", query = "SELECT v FROM VSeguimiento v WHERE v.correo = :correo"),
    @NamedQuery(name = "VSeguimiento.findByCdSeguimiento", query = "SELECT v FROM VSeguimiento v WHERE v.cdSeguimiento = :cdSeguimiento"),
    @NamedQuery(name = "VSeguimiento.findByFcRegistro", query = "SELECT v FROM VSeguimiento v WHERE v.fcRegistro = :fcRegistro"),
    @NamedQuery(name = "VSeguimiento.findByFcCotizacion", query = "SELECT v FROM VSeguimiento v WHERE v.fcCotizacion = :fcCotizacion"),
    @NamedQuery(name = "VSeguimiento.findByDiasCotizacion", query = "SELECT v FROM VSeguimiento v WHERE v.diasCotizacion = :diasCotizacion"),
    @NamedQuery(name = "VSeguimiento.findByFcEstado", query = "SELECT v FROM VSeguimiento v WHERE v.fcEstado = :fcEstado"),
    @NamedQuery(name = "VSeguimiento.findByDiasEstado", query = "SELECT v FROM VSeguimiento v WHERE v.diasEstado = :diasEstado"),
    @NamedQuery(name = "VSeguimiento.findByCdEstado", query = "SELECT v FROM VSeguimiento v WHERE v.cdEstado = :cdEstado"),
    @NamedQuery(name = "VSeguimiento.findByNmEstado", query = "SELECT v FROM VSeguimiento v WHERE v.nmEstado = :nmEstado"),
    @NamedQuery(name = "VSeguimiento.findByCdRamo", query = "SELECT v FROM VSeguimiento v WHERE v.cdRamo = :cdRamo"),
    @NamedQuery(name = "VSeguimiento.findByRamo", query = "SELECT v FROM VSeguimiento v WHERE v.ramo = :ramo"),
    @NamedQuery(name = "VSeguimiento.findByCdProducto", query = "SELECT v FROM VSeguimiento v WHERE v.cdProducto = :cdProducto"),
    @NamedQuery(name = "VSeguimiento.findByProducto", query = "SELECT v FROM VSeguimiento v WHERE v.producto = :producto"),
    @NamedQuery(name = "VSeguimiento.findByFuente", query = "SELECT v FROM VSeguimiento v WHERE v.fuente = :fuente"),
    @NamedQuery(name = "VSeguimiento.findByObservacion", query = "SELECT v FROM VSeguimiento v WHERE v.observacion = :observacion")})
public class VSeguimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USUARIO")
    private String usuario;
    @Size(max = 101)
    @Column(name = "ASESOR")
    private String asesor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CD_CLIENTE")
    private BigInteger cdCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "IDENTIFICACION")
    private String identificacion;
    @Size(max = 201)
    @Column(name = "CLI_NOMBRES")
    private String cliNombres;
    @Size(max = 201)
    @Column(name = "CLI_APELLIDOS")
    private String cliApellidos;
    @Size(max = 3)
    @Column(name = "CD_PROVINCIA")
    private String cdProvincia;
    @Size(max = 100)
    @Column(name = "PROVINCIA")
    private String provincia;
    @Size(max = 5)
    @Column(name = "CD_CIUDAD")
    private String cdCiudad;
    @Size(max = 100)
    @Column(name = "CIUDAD")
    private String ciudad;
    @Column(name = "CD_DIRECCION")
    private BigInteger cdDireccion;
    @Size(max = 500)
    @Column(name = "DIRECCION")
    private String direccion;
    @Size(max = 4000)
    @Column(name = "TELEFONO")
    private String telefono;
    @Size(max = 4000)
    @Column(name = "CORREO")
    private String correo;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CD_SEGUIMIENTO")
    private Long cdSeguimiento;
    @Column(name = "FC_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fcRegistro;
    @Column(name = "FC_COTIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fcCotizacion;
    @Column(name = "DIAS_COTIZACION")
    private BigInteger diasCotizacion;
    @Column(name = "FC_ESTADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fcEstado;
    @Column(name = "DIAS_ESTADO")
    private BigInteger diasEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CD_ESTADO")
    private BigInteger cdEstado;
    @Size(max = 100)
    @Column(name = "NM_ESTADO")
    private String nmEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CD_RAMO")
    private BigInteger cdRamo;
    @Size(max = 100)
    @Column(name = "RAMO")
    private String ramo;
    @Column(name = "CD_PRODUCTO")
    private BigInteger cdProducto;
    @Size(max = 100)
    @Column(name = "PRODUCTO")
    private String producto;
    @Size(max = 100)
    @Column(name = "FUENTE")
    private String fuente;
    @Size(max = 1000)
    @Column(name = "OBSERVACION")
    private String observacion;
    @Column(name = "CD_ASEGURADORA")
    private Long cdAseguradora;
    
    @Size(max = 150)
    @Column(name = "ASEGURADORA")
    private String aseguradora;
    
    @Column(name = "RESULTADO")
    private String resultado;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FC_RENOVACION")
    private Date fcRenovacion;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FC_RECORDATORIO")
    private Date fcRecordatorio;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FC_PROSPECTO")
    private Date fcProspecto;

    public VSeguimiento() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getAsesor() {
        return asesor;
    }

    public void setAsesor(String asesor) {
        this.asesor = asesor;
    }

    public BigInteger getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(BigInteger cdCliente) {
        this.cdCliente = cdCliente;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getCliNombres() {
        return cliNombres;
    }

    public void setCliNombres(String cliNombres) {
        this.cliNombres = cliNombres;
    }

    public String getCliApellidos() {
        return cliApellidos;
    }

    public void setCliApellidos(String cliApellidos) {
        this.cliApellidos = cliApellidos;
    }

    public String getCdProvincia() {
        return cdProvincia;
    }

    public void setCdProvincia(String cdProvincia) {
        this.cdProvincia = cdProvincia;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCdCiudad() {
        return cdCiudad;
    }

    public void setCdCiudad(String cdCiudad) {
        this.cdCiudad = cdCiudad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public BigInteger getCdDireccion() {
        return cdDireccion;
    }

    public void setCdDireccion(BigInteger cdDireccion) {
        this.cdDireccion = cdDireccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Long getCdSeguimiento() {
        return cdSeguimiento;
    }

    public void setCdSeguimiento(Long cdSeguimiento) {
        this.cdSeguimiento = cdSeguimiento;
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

    public BigInteger getDiasCotizacion() {
        return diasCotizacion;
    }

    public void setDiasCotizacion(BigInteger diasCotizacion) {
        this.diasCotizacion = diasCotizacion;
    }

    public Date getFcEstado() {
        return fcEstado;
    }

    public void setFcEstado(Date fcEstado) {
        this.fcEstado = fcEstado;
    }

    public BigInteger getDiasEstado() {
        return diasEstado;
    }

    public void setDiasEstado(BigInteger diasEstado) {
        this.diasEstado = diasEstado;
    }

    public BigInteger getCdEstado() {
        return cdEstado;
    }

    public void setCdEstado(BigInteger cdEstado) {
        this.cdEstado = cdEstado;
    }

    public String getNmEstado() {
        return nmEstado;
    }

    public void setNmEstado(String nmEstado) {
        this.nmEstado = nmEstado;
    }

    public BigInteger getCdRamo() {
        return cdRamo;
    }

    public void setCdRamo(BigInteger cdRamo) {
        this.cdRamo = cdRamo;
    }

    public String getRamo() {
        return ramo;
    }

    public void setRamo(String ramo) {
        this.ramo = ramo;
    }

    public BigInteger getCdProducto() {
        return cdProducto;
    }

    public void setCdProducto(BigInteger cdProducto) {
        this.cdProducto = cdProducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Long getCdAseguradora() {
        return cdAseguradora;
    }

    public void setCdAseguradora(Long cdAseguradora) {
        this.cdAseguradora = cdAseguradora;
    }

    public String getAseguradora() {
        return aseguradora;
    }

    public void setAseguradora(String aseguradora) {
        this.aseguradora = aseguradora;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Date getFcRenovacion() {
        return fcRenovacion;
    }

    public void setFcRenovacion(Date fcRenovacion) {
        this.fcRenovacion = fcRenovacion;
    }

    public Date getFcRecordatorio() {
        return fcRecordatorio;
    }

    public void setFcRecordatorio(Date fcRecordatorio) {
        this.fcRecordatorio = fcRecordatorio;
    }

    public Date getFcProspecto() {
        return fcProspecto;
    }

    public void setFcProspecto(Date fcProspecto) {
        this.fcProspecto = fcProspecto;
    }
    
    
    
}
