/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "SEGUIMIENTO_ESTADOS")
public class SeguimientoEstados implements Serializable{
    
    @Id
    @Column(name = "CD_SEGEST")
    private Long cdSegest;
    
    @ManyToOne
    @JoinColumn(name = "CD_ESTADO")
    private SegEstados cdEstado;
    
    @ManyToOne
    @JoinColumn(name = "CD_SEGUIMIENTO")
    private Seguimientos cdSeguimiento;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "FC_ESTADO", insertable = false)
    private Date fcEstado;
    
    @Column(name = "OBSERVACION")
    private String  observacion;
    
    @Column(name = "ACTIVO", insertable = false)
    private Boolean activo;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FC_RECORDATORIO")
    private Date fcRecordatorio;
   

    public Long getCdSegest() {
        return cdSegest;
    }

    public void setCdSegest(Long cdSegest) {
        this.cdSegest = cdSegest;
    }

    public SegEstados getCdEstado() {
        return cdEstado;
    }

    public void setCdEstado(SegEstados cdEstado) {
        this.cdEstado = cdEstado;
    }

    public Seguimientos getCdSeguimiento() {
        return cdSeguimiento;
    }

    public void setCdSeguimiento(Seguimientos cdSeguimiento) {
        this.cdSeguimiento = cdSeguimiento;
    }

    public Date getFcEstado() {
        return fcEstado;
    }

    public void setFcEstado(Date fcEstado) {
        this.fcEstado = fcEstado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Date getFcRecordatorio() {
        return fcRecordatorio;
    }

    public void setFcRecordatorio(Date fcRecordatorio) {
        this.fcRecordatorio = fcRecordatorio;
    }    
}
