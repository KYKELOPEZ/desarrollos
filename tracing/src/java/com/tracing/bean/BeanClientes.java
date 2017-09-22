/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracing.bean;

import com.tracing.dao.CiudadesDAO;
import com.tracing.dao.ClientesDAO;
import com.tracing.dao.DireccionesDAO;
import com.tracing.dao.ProvinciasDAO;
import com.tracing.dao.TelefonosDAO;
import com.tracing.modelo.Ciudades;
import com.tracing.modelo.Clientes;
import com.tracing.modelo.Direcciones;
import com.tracing.modelo.Provincias;
import com.tracing.modelo.Telefonos;
import com.tracing.modelo.Usuarios;
import com.tracing.util.Mensajes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author cjlop
 */
@ManagedBean
@ViewScoped
public class BeanClientes implements Bean, Serializable {

    private Usuarios usuarios;

    private Clientes clientes;
    private Clientes seleccionClientes;
    @EJB(beanName = "ClientesDAOImpl")
    private ClientesDAO clientesDAO;
    private List<Clientes> listaClientes;

    private Provincias provincias;
    @EJB(beanName = "ProvinciasDAOImpl")
    private ProvinciasDAO provinciasDAO;
    private List<Provincias> listaProvincias;
    private Long codigoProvincia;

    private Ciudades ciudades;
    @EJB(beanName = "CiudadesDAOImpl")
    private CiudadesDAO ciudadesDAO;
    private List<Ciudades> listaCiudades;
    private Long codigoCiudades;

    private Direcciones direcciones;
    @EJB(beanName = "DireccionesDAOImpl")
    private DireccionesDAO direccionesDAO;

    private Telefonos telefonos;
    private List<Telefonos> listaTelefonos;
    @EJB(beanName = "TelefonosDAOImpl")
    private TelefonosDAO telefonosDAO;

    private String email;
    private String emailDos;
    private String convencional;
    private String celular;

    private String tpIdentificacion = "9999999999";

    private Boolean btnregistrar;
    private Boolean btnActualizar;

    @PostConstruct
    @Override
    public void init() {
        usuarios = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        cancel();
        findAll();
    }

    @Override
    public void create() {
        try {
            if (validate()) {
                clientes.setCdCliente(clientesDAO.getMaxIdDos("cdCliente"));
                clientes.setUsaurio(usuarios.getUsuario());
                clientesDAO.create(clientes);
                if (!direcciones.getDireccion().equals("")) {
                    direcciones.setCdDireccion(direccionesDAO.getMaxIdDos("cdDireccion"));
                    direcciones.setCdCiudad(ciudadesDAO.findById(codigoCiudades));
                    direcciones.setCdCliente(clientes);
                    direccionesDAO.create(direcciones);
                }
                if (!email.equals("")) {
                    telefonos.setCdTelefono(telefonosDAO.getMaxIdDos("cdTelefono"));
                    telefonos.setCdCliente(clientes);
                    telefonos.setTpTelefono("CORREO ELECTRONICO");
                    telefonos.setNumTelefono(email);
                    telefonosDAO.create(telefonos);
                }
                if (!emailDos.equals("")) {
                    telefonos.setCdTelefono(telefonosDAO.getMaxIdDos("cdTelefono"));
                    telefonos.setCdCliente(clientes);
                    telefonos.setTpTelefono("CORREO ELECTRONICO");
                    telefonos.setNumTelefono(emailDos);
                    telefonosDAO.create(telefonos);
                }
                if (!convencional.equals("")) {
                    telefonos.setCdTelefono(telefonosDAO.getMaxIdDos("cdTelefono"));
                    telefonos.setCdCliente(clientes);
                    telefonos.setTpTelefono("TELEF - CONVENCIONAL");
                    telefonos.setNumTelefono(convencional);
                    telefonosDAO.create(telefonos);
                }

                if (!celular.equals("")) {
                    telefonos.setCdTelefono(telefonosDAO.getMaxIdDos("cdTelefono"));
                    telefonos.setCdCliente(clientes);
                    telefonos.setTpTelefono("TELEF - CELULAR");
                    telefonos.setNumTelefono(celular);
                    telefonosDAO.create(telefonos);
                }
                cancel();
                findAll();
                Mensajes.mensajeGrabarCorrecto();
            }
        } catch (Exception e) {
            Mensajes.mensajeError(e.getMessage());
            telefonosDAO.delete(telefonos);
            direccionesDAO.delete(direcciones);
            clientesDAO.delete(clientes);
        }
    }

    public void seleccionar(Clientes cli) {
        try {
            int cont = 1;
            clientes = cli;
            btnActualizar = true;
            btnregistrar = false;
            if (!direccionesDAO.findByFk("WHERE t.cdCliente.cdCliente = " + cli.getCdCliente()).isEmpty()) {
                direcciones = direccionesDAO.findByFk("WHERE t.cdCliente.cdCliente = " + cli.getCdCliente()).get(0);
                if (direcciones.getCdCiudad() != null) {
                    ciudades = ciudadesDAO.findById(direcciones.getCdCiudad().getCdCiudad());
                    codigoCiudades = ciudades.getCdCiudad();
                    provincias = provinciasDAO.findById(ciudades.getCdProvincia().getCdProvincia());
                    codigoProvincia = provincias.getCdProvincia();
                    listaCiudades = ciudadesDAO.findByFk("WHERE t.cdProvincia.cdProvincia = " + codigoProvincia);
                }
            }
            listaTelefonos = telefonosDAO.findByFk("WHERE t.cdCliente.cdCliente = " + cli.getCdCliente());
            for (Telefonos t : listaTelefonos) {
                if (t.getTpTelefono().equals("TELEF - CELULAR")) {
                    celular = t.getNumTelefono();
                } else if (t.getTpTelefono().equals("TELEF - CONVENCIONAL")) {
                    convencional = t.getNumTelefono();
                } else if (t.getTpTelefono().equals("CORREO ELECTRONICO")) {

                    if (cont == 1) {
                        email = t.getNumTelefono();
                        cont = 2;
                    } else if (cont == 2) {
                        emailDos = t.getNumTelefono();
                        cont = 0;
                    }
                }
            }

            RequestContext.getCurrentInstance().execute("PF('wvRegistrar').show();");

        } catch (Exception e) {

        }
    }

    @Override
    public void delete() {

    }

    public void eliminar(Clientes cli) {
        try {
            clientesDAO.delete(cli);
            findAll();
            Mensajes.mensajeInformacion("El registro se elimino correctamente.");
        } catch (Exception e) {
            Mensajes.mensajeError(e.getLocalizedMessage());
        }

    }

    @Override
    public void update() {
        try {
            Telefonos telConvencional = new Telefonos();
            Telefonos telCelular = new Telefonos();
            List<Telefonos> correos = new ArrayList<Telefonos>();
            clientesDAO.update(clientes);
            if (direcciones.getCdDireccion() != null) {
                if (codigoCiudades != direcciones.getCdCiudad().getCdCiudad()) {
                    direcciones.setCdCiudad(ciudadesDAO.findById(codigoCiudades));
                }
                direccionesDAO.update(direcciones);
            } else if (!direcciones.getDireccion().equals("")) {
                direcciones.setCdDireccion(direccionesDAO.getMaxIdDos("cdDireccion"));
                direcciones.setCdCiudad(ciudadesDAO.findById(codigoCiudades));
                direcciones.setCdCliente(clientes);
                direccionesDAO.create(direcciones);
            }

            if (!telefonosDAO.findByFk("where t.cdCliente.cdCliente = " + clientes.getCdCliente() + " and t.tpTelefono = 'TELEF - CONVENCIONAL'").isEmpty()) {
                telConvencional = telefonosDAO.findByFk("where t.cdCliente.cdCliente = " + clientes.getCdCliente() + " and t.tpTelefono = 'TELEF - CONVENCIONAL'").get(0);
                telConvencional.setNumTelefono(convencional);
                telefonosDAO.update(telConvencional);
            } else if (!convencional.equals("")) {
                telConvencional.setCdTelefono(telefonosDAO.getMaxIdDos("cdTelefono"));
                telConvencional.setCdCliente(clientes);
                telConvencional.setTpTelefono("TELEF - CONVENCIONAL");
                telConvencional.setNumTelefono(convencional);
                telefonosDAO.create(telConvencional);
            }
            if (!telefonosDAO.findByFk("where t.cdCliente.cdCliente = " + clientes.getCdCliente() + " and t.tpTelefono = 'TELEF - CELULAR'").isEmpty()) {
                telCelular = telefonosDAO.findByFk("where t.cdCliente.cdCliente = " + clientes.getCdCliente() + " and t.tpTelefono = 'TELEF - CELULAR'").get(0);
                telCelular.setNumTelefono(celular);
                telefonosDAO.update(telCelular);
            } else if (!celular.equals("")) {
                telCelular.setCdTelefono(telefonosDAO.getMaxIdDos("cdTelefono"));
                telCelular.setCdCliente(clientes);
                telCelular.setTpTelefono("TELEF - CELULAR");
                telCelular.setNumTelefono(celular);
                telefonosDAO.create(telCelular);
            }
            correos = telefonosDAO.findByFk("where t.cdCliente.cdCliente = " + clientes.getCdCliente() + " and t.tpTelefono = 'CORREO ELECTRONICO'");
            Telefonos correo = new Telefonos();
            if (correos.isEmpty()) {
                if (!email.equals("")) {
                    correo.setCdTelefono(telefonosDAO.getMaxIdDos("cdTelefono"));
                    correo.setCdCliente(clientes);
                    correo.setTpTelefono("CORREO ELECTRONICO");
                    correo.setNumTelefono(email);
                    correos.add(correo);
                    correo = new Telefonos();
                }
                if (!emailDos.equals("")) {
                    correo.setCdTelefono(telefonosDAO.getMaxIdDos("cdTelefono"));
                    correo.setCdCliente(clientes);
                    correo.setTpTelefono("CORREO ELECTRONICO");
                    correo.setNumTelefono(emailDos);
                    correos.add(correo);
                }
                for (Telefonos t : correos) {
                    telefonosDAO.create(t);
                }
            } else if (correos.size() == 1) {
                correo = correos.get(0);
                if (!email.equals("")) {
                    correo.setNumTelefono(email);
                    telefonosDAO.update(correo);
                }
                if (!emailDos.equals("")) {
                    correo.setCdTelefono(telefonosDAO.getMaxIdDos("cdTelefono"));
                    correo.setCdCliente(clientes);
                    correo.setTpTelefono("CORREO ELECTRONICO");
                    correo.setNumTelefono(emailDos);
                    telefonosDAO.create(correo);
                }

            } else if (correos.size() == 2) {
                int cont = 1;
                for (Telefonos t : correos) {
                    if (cont == 1) {
                        t.setNumTelefono(email);
                        telefonosDAO.update(t);
                        cont = 2;
                    } else if (cont == 2) {
                        t.setNumTelefono(emailDos);
                        telefonosDAO.update(t);
                    }
                }
            }
            cancel();
            findAll();
            RequestContext.getCurrentInstance().execute("PF('wvRegistrar').hide();");
            Mensajes.mensajeGrabarCorrecto();
        } catch (Exception e) {
            Mensajes.mensajeError(e.getCause().getLocalizedMessage());
            Mensajes.mensajeGrabarNoCorrecto();
        }
    }

    @Override
    public void findAll() {
        listaClientes = clientesDAO.findAllOrderBy("nombres");
        listaProvincias = provinciasDAO.findByFk("where t.estado = 1 order by t.provincia");
    }

    @Override
    public void find() {
        listaCiudades = ciudadesDAO.findByFk("where t.cdProvincia.cdProvincia = '" + codigoProvincia + "'");
    }

    @Override
    public void cancel() {
        clientes = new Clientes();
        seleccionClientes = new Clientes();
        ciudades = new Ciudades();
        provincias = new Provincias();
        direcciones = new Direcciones();
        telefonos = new Telefonos();
        convencional = null;
        codigoCiudades = 0L;
        codigoProvincia = 0L;
        celular = null;
        email = null;
        emailDos = null;
        btnActualizar = false;
        btnregistrar = true;
    }

    @Override
    public Boolean validate() {
        Boolean valor = true;
        return valor;
    }

    public void longitudIdentificacion() {
        if (clientes.getTpIdentificacion().equals("CEDULA")) {
            tpIdentificacion = "9999999999";
        } else {
            tpIdentificacion = "9999999999999";
        }
    }

    public void actualizar(Clientes cli) {
        try {
            seleccionClientes = cli;
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('').show();");
        } catch (Exception e) {
        }

    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    public List<Clientes> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Clientes> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public Provincias getProvincias() {
        return provincias;
    }

    public void setProvincias(Provincias provincias) {
        this.provincias = provincias;
    }

    public List<Provincias> getListaProvincias() {
        return listaProvincias;
    }

    public void setListaProvincias(List<Provincias> listaProvincias) {
        this.listaProvincias = listaProvincias;
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

    public Direcciones getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(Direcciones direcciones) {
        this.direcciones = direcciones;
    }

    public Telefonos getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(Telefonos telefonos) {
        this.telefonos = telefonos;
    }

    public Long getCodigoProvincia() {
        return codigoProvincia;
    }

    public void setCodigoProvincia(Long codigoProvincia) {
        this.codigoProvincia = codigoProvincia;
    }

    public Long getCodigoCiudades() {
        return codigoCiudades;
    }

    public void setCodigoCiudades(Long codigoCiudades) {
        this.codigoCiudades = codigoCiudades;
    }

    public String getTpIdentificacion() {
        return tpIdentificacion;
    }

    public void setTpIdentificacion(String tpIdentificacion) {
        this.tpIdentificacion = tpIdentificacion;
    }

    public Clientes getSeleccionClientes() {
        return seleccionClientes;
    }

    public void setSeleccionClientes(Clientes seleccionClientes) {
        this.seleccionClientes = seleccionClientes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailDos() {
        return emailDos;
    }

    public void setEmailDos(String emailDos) {
        this.emailDos = emailDos;
    }

    public String getConvencional() {
        return convencional;
    }

    public void setConvencional(String convencional) {
        this.convencional = convencional;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Boolean getBtnregistrar() {
        return btnregistrar;
    }

    public void setBtnregistrar(Boolean btnregistrar) {
        this.btnregistrar = btnregistrar;
    }

    public Boolean getBtnActualizar() {
        return btnActualizar;
    }

    public void setBtnActualizar(Boolean btnActualizar) {
        this.btnActualizar = btnActualizar;
    }

    public List<Telefonos> getListaTelefonos() {
        return listaTelefonos;
    }

    public void setListaTelefonos(List<Telefonos> listaTelefonos) {
        this.listaTelefonos = listaTelefonos;
    }

}
