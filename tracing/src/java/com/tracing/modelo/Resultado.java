/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracing.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RESULTADO")
public class Resultado implements Serializable{
    
    @Id
    @Column(name = "CD_RESULTADO")
    private Long cdResultado;
    @Column(name = "DES_RESULTADO")
    private String desResultado;
    @Column(name = "ESTADO")
    private Boolean estado;
    
    @Column(name = "PROCESO")
    private String proceso;

    public Long getCdResultado() {
        return cdResultado;
    }

    public void setCdResultado(Long cdResultado) {
        this.cdResultado = cdResultado;
    }

    public String getDesResultado() {
        return desResultado;
    }

    public void setDesResultado(String desResultado) {
        this.desResultado = desResultado;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }
    
    
}
