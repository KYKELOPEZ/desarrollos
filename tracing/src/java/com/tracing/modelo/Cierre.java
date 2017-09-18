package com.tracing.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author cjlop
 */
@Entity
@Table(name = "CIERRE")
public class Cierre implements Serializable{
    
    @Id
    @Column(name = "CD_CIERRE")
    private Long cdCierre;
    
    @Column(name = "DES_CIERRE")
    private String desCierre;
    
    @ManyToOne
    @JoinColumn(name = "CD_RESULTADO")
    private Resultado cdResultado;

    public Long getCdCierre() {
        return cdCierre;
    }

    public void setCdCierre(Long cdCierre) {
        this.cdCierre = cdCierre;
    }

    public String getDesCierre() {
        return desCierre;
    }

    public void setDesCierre(String desCierre) {
        this.desCierre = desCierre;
    }

    public Resultado getCdResultado() {
        return cdResultado;
    }

    public void setCdResultado(Resultado cdResultado) {
        this.cdResultado = cdResultado;
    }
    
    
}
