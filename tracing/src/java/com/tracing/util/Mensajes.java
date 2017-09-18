package com.tracing.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

public class Mensajes {

    public static void mensajeInformacion(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info..!", msg));
    }

    public static void mensajeError(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error..!", msg));
    }

    public static void mensajeAdvertencia(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia..!", msg));
    }

    public static void mensajeGrabarCorrecto() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info..!", "Registro almacenado correctamente"));
    }

    public static void mensajeGrabarNoCorrecto() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info..!", "Existe un problema con la información ingresada por favor verificar"));
    }

      public static void mensajeGrabar(){
            FacesMessage context = new FacesMessage(FacesMessage.SEVERITY_INFO,"!Informacion","Registro Grabado con Exitoso");
            RequestContext.getCurrentInstance().showMessageInDialog(context);
        }
}
