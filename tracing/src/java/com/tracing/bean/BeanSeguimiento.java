package com.tracing.bean;

import com.oracle.jrockit.jfr.Producer;
import com.tracing.dao.AseguradorasDAO;
import com.tracing.dao.CierreDAO;
import com.tracing.dao.ClientesDAO;
import com.tracing.dao.FuenteDAO;
import com.tracing.dao.ProductosDAO;
import com.tracing.dao.RamosDAO;
import com.tracing.dao.ResultadoDAO;
import com.tracing.dao.SegEstadosDAO;
import com.tracing.dao.SeguimientoEstadosDAO;
import com.tracing.dao.SeguimientosDAO;
import com.tracing.dao.VSeguimientoDAO;
import com.tracing.modelo.Aseguradoras;
import com.tracing.modelo.Cierre;
import com.tracing.modelo.Clientes;
import com.tracing.modelo.Fuente;
import com.tracing.modelo.Productos;
import com.tracing.modelo.Ramos;
import com.tracing.modelo.RamosProd;
import com.tracing.modelo.Resultado;
import com.tracing.modelo.SeguimientoEstados;
import com.tracing.modelo.Seguimientos;
import com.tracing.modelo.Usuarios;
import com.tracing.modelo.VSeguimiento;
import com.tracing.util.Mensajes;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@ManagedBean
@ViewScoped
public class BeanSeguimiento implements Serializable {

    private Usuarios usuarios;

    private List<VSeguimiento> vistaSeguimiento;
    private List<VSeguimiento> cliSeguimiento;
    @EJB(beanName = "VSeguimientoDAOImpl")
    private VSeguimientoDAO vSeguimientoDAO;

    private Clientes clientes;
    @EJB(beanName = "ClientesDAOImpl")
    private ClientesDAO clientesDAO;
    private List<Clientes> listaClientes;

    private List<Fuente> listaFuentes;
    @EJB(beanName = "FuenteDAOImpl")
    private FuenteDAO fuenteDAO;
    private Long cdFuente;

    private SeguimientoEstados seguimientoEstados;
    @EJB(beanName = "SeguimientoEstadosDAOImpl")
    private SeguimientoEstadosDAO seguimientoEstadosDAO;

    private Seguimientos seguimientos;
    @EJB(beanName = "SeguimientosDAOImpl")
    private SeguimientosDAO seguimientosDAO;

    @EJB(beanName = "SegEstadosDAOImpl")
    private SegEstadosDAO segEstadosDAO;

    @EJB(beanName = "AseguradorasDAOImpl")
    private AseguradorasDAO aseguradorasDAO;
    private List<Aseguradoras> listaAseguradoras;
    private Long cdAseguradora;

    private List<Productos> listaProductos;
    private List<Productos> vistaProductos;
    @EJB(beanName = "ProductosDAOImpl")
    private ProductosDAO productosDAO;
    private Long cdProducto;

    private List<Resultado> listaResultados;
    @EJB(beanName = "ResultadoDAOImpl")
    private ResultadoDAO resultadoDAO;

    private List<Cierre> listaCierre;
    @EJB(beanName = "CierreDAOImpl")
    private CierreDAO cierreDAO;

    private List<Ramos> listaRamos;
    private List<Ramos> vistaRamos;
    @EJB(beanName = "RamosDAOImpl")
    private RamosDAO ramosDAO;
    private Long cdRamo;

    private Boolean fieldsetCliente;
    private Boolean fieldsetFuente;
    private Boolean fieldsetResultado;
    private Boolean fieldsetCotizacion;
    private Boolean fieldsetObservacion;
    private Boolean fieldsetFechaProspecto;
    private Boolean fieldsetCierre;
    private Boolean botonRegistrar;
    private Boolean botonContacto;
    private Boolean botonCotizado;
    private Boolean botonCerrar;
    private Boolean botonRechazo;
    private Boolean botonSeguimiento;
    private Boolean itemsContacto;
    private Boolean itemsCotizacion;
    private Boolean itemsCierre;

    private Boolean observacion;
    private Boolean observacionCierre;

    private Boolean fcRecordatorio;
    private String proceso;
    private String nmPantalla;
    String mensaje;

    private List<RamosProd> ramosProd;
    private RamosProd ramoProducto;

    private Date fechaActual = new Date();

    //al abrir la pantalla seguimiento cliente
    @PostConstruct
    public void init() {
        try {
            usuarios = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            vistaSeguimiento = vSeguimientoDAO.findByFk("WHERE t.nmEstado NOT LIKE '%CERRADO%' AND t.usuario = '" + usuarios.getUsuario() + "' ORDER BY t.fcRegistro desc");
            clientes = new Clientes();
            listaFuentes = fuenteDAO.findByFk("WHERE t.estado  = 1 order by t.decFuente");
            seguimientoEstados = new SeguimientoEstados();
            seguimientos = new Seguimientos();
            listaAseguradoras = aseguradorasDAO.findByFk("WHERE t.estado = 1");
            ramosProd = new ArrayList<>();
            listaRamos = ramosDAO.findByFk("WHERE t.estado = 1");
            fieldsetCliente = true;
            fieldsetFuente = true;
            fieldsetResultado = false;
            fieldsetCotizacion = false;
            fieldsetObservacion = true;
            fieldsetCierre = false;
            fieldsetFechaProspecto = true;
            botonRegistrar = true;
            botonContacto = false;
            botonCotizado = false;
            botonCerrar = false;
            botonRechazo = false;
            botonSeguimiento = false;
            observacion = true;
            fcRecordatorio = false;
            observacionCierre = false;
            nmPantalla = "REGISTRAR";
            cdAseguradora = 0L;
            cdFuente = 0L;
            cdRamo = 0L;
            cdProducto = 0L;
            ramoProducto = new RamosProd();
        } catch (Exception e) {
            Mensajes.mensajeError(e.getCause().getCause().getMessage());
        }
    }

    //consulta cliente
    public void buscarCliente() {
        try {
            String query = "";
            
            if(!clientes.getIdentificacion().equals("")){
                query = "WHERE t.identificacion LIKE '%" + clientes.getIdentificacion() + "' "
                    + "AND trim(t.nombres) LIKE trim('%" + clientes.getNombres().toUpperCase() + "') "
                    + "AND trim(t.apellidos) LIKE trim('%" + clientes.getApellidos().toUpperCase() + "')";
            }else{
                query = "WHERE (t.identificacion LIKE '%" + clientes.getIdentificacion() + "' "
                    + "OR t.identificacion is null) "
                    + "AND trim(t.nombres) LIKE trim('%" + clientes.getNombres().toUpperCase() + "') "
                    + "AND trim(t.apellidos) LIKE trim('%" + clientes.getApellidos().toUpperCase() + "')";
            }
                
            listaClientes = clientesDAO.findByFk(query);
//            listaClientes = clientesDAO.findByFk("WHERE (t.identificacion LIKE '%" + clientes.getIdentificacion() + "' "
//                    + "OR t.identificacion is null) "
//                    + "AND trim(t.nombres) LIKE trim('%" + clientes.getNombres().toUpperCase() + "') "
//                    + "AND trim(t.apellidos) LIKE trim('%" + clientes.getApellidos().toUpperCase() + "')");

            if (listaClientes.isEmpty()) {
                RequestContext.getCurrentInstance().execute("PF('Confirmar').show();");
            } else if (listaClientes.size() == 1) {
                clientes = listaClientes.get(0);
                clienteSeguimiento(clientes);
            } else {
                RequestContext.getCurrentInstance().execute("PF('clientes').show();");
            }
        } catch (Exception e) {
            Mensajes.mensajeError(e.getCause().getCause().getMessage());
        }
    }

    public void seleccionarCliente(SelectEvent event) {
        try {
            clientes = (Clientes) event.getObject();
            RequestContext.getCurrentInstance().execute("PF('clientes').hide();");
            clienteSeguimiento(clientes);
        } catch (Exception e) {
            Mensajes.mensajeError(e.getCause().getCause().getMessage());
        }

    }

    public void clienteSeguimiento(Clientes cli) {
        VSeguimiento cliS;

        cliSeguimiento = vSeguimientoDAO.findByFk("WHERE t.nmEstado NOT LIKE '%CERRADO%' AND t.cdCliente = " + cli.getCdCliente() + "");
        if (cliSeguimiento.size() == 1) {
            cliS = cliSeguimiento.get(0);
            mensaje = "EL cliente " + cli.getNombres() + " " + cli.getApellidos() + " mantiene un seguimiento pendiente con el Asesor " + cliS.getAsesor();
            if (!cliS.getRamo().equals("-")) {
                mensaje = mensaje + " en el ramo " + cliS.getRamo();
            }
            mensaje = mensaje + " para mayor informacion verificar la tabla de seguimiento.";
            //Mensajes.mensajeInformacion(mensaje);
            RequestContext.getCurrentInstance().execute("PF('mensaje').show();");
        } else if (cliSeguimiento.size() > 1) {
            mensaje = "EL cliente " + cli.getNombres() + " " + cli.getApellidos() + " mantiene seguimientos pendientes en los ramos: <br><br>";
            for (VSeguimiento cliSeg : cliSeguimiento) {
                mensaje = mensaje + " * " + cliSeg.getRamo() + ".<br>";
            }
            mensaje = mensaje + "<br>Para mayor informacion ir a consulta de seguimientos.";
            //Mensajes.mensajeInformacion(mensaje);
            RequestContext.getCurrentInstance().execute("PF('mensaje').show();");
        }
    }

    //Guardar Seguimiento
    public void registrarSeguimiento() {
        proceso = "REGISTRO";
        if (validar()) {
            try {
                //Registro tabla de seguimiento, cliente, fuente.
                seguimientos.setCdSeguimiento(seguimientosDAO.getMaxIdDos("cdSeguimiento"));
                seguimientos.setCdSegOrigen(seguimientos.getCdSeguimiento().intValue());
                seguimientos.setCdCliete(clientes);
                seguimientos.setCdFuente(fuenteDAO.findById(cdFuente));
                seguimientos.setUsuario(usuarios);
                seguimientosDAO.create(seguimientos);
                //Registro de estado del seguimiento, observacion.
                seguimientoEstados.setCdSegest(seguimientoEstadosDAO.getMaxIdDos("cdSegest"));
                seguimientoEstados.setCdSeguimiento(seguimientos);
                seguimientoEstados.setCdEstado(segEstadosDAO.findByFk("WHERE t.nmEstado = 'REGISTRADO'").get(0));
                seguimientoEstadosDAO.create(seguimientoEstados);
                Mensajes.mensajeGrabarCorrecto();
                init();
            } catch (Exception e) {
                Mensajes.mensajeError(e.getCause().getCause().getMessage());
            }
        }
    }

    public void seleccionarSeguimiento(VSeguimiento vs) {
        Long val = vs.getCdSeguimiento();
        seguimientos = new Seguimientos();
        seguimientos = seguimientosDAO.findById(val);
        try {
            if (seguimientos.getUsuario().getUsuario().equals(usuarios.getUsuario()) || usuarios.getUsuario().equals("CLOPEZ")) {
                //SEGUIMIENTO CONTACTO 
                if (vs.getNmEstado().equals("REGISTRADO") || vs.getNmEstado().equals("CONTACTADO") && vs.getResultado().equals("CONTACTO PENDIENTE")) {
                    seguimientos.setResultado("");
                    fieldsetCliente = false;
                    fieldsetFechaProspecto = false;
                    fieldsetFuente = false;
                    fieldsetResultado = true;
                    fieldsetCotizacion = false;
                    fieldsetObservacion = true;
                    fieldsetCierre = false;
                    botonRegistrar = false;
                    botonContacto = true;
                    botonCotizado = false;
                    botonCerrar = false;
                    botonRechazo = false;
                    observacion = true;
                    fcRecordatorio = true;
                    observacionCierre = false;
                    nmPantalla = "CONTACTO";
                    listaResultados = resultadoDAO.findByFk("WHERE t.proceso = 'CONTACTO'");
                    RequestContext.getCurrentInstance().execute("PF('registrar').show();");
                    //SEGUIMIENTO COTIZADO
                } else if ((vs.getNmEstado().equals("CONTACTADO") && vs.getResultado().equals("CONTACTADO"))
                        || vs.getNmEstado().equals("COTIZADO") && vs.getResultado().equals("PENDIENTE COTIZACION")) {
                    seguimientos.setResultado("");
                    fieldsetCliente = false;
                    fieldsetFechaProspecto = false;
                    fieldsetFuente = false;
                    fieldsetResultado = true;
                    fieldsetCotizacion = true;
                    fieldsetObservacion = true;
                    fieldsetCierre = false;
                    botonRegistrar = false;
                    botonContacto = false;
                    botonCotizado = true;
                    botonCerrar = false;
                    botonRechazo = false;
                    observacion = true;
                    observacionCierre = false;
                    fcRecordatorio = true;
                    nmPantalla = "COTIZACION";
                    listaResultados = resultadoDAO.findByFk("WHERE t.proceso = 'COTIZACION'");
                    RequestContext.getCurrentInstance().execute("PF('registrar').show();");
                    //SEGUIMIENTO CIERRE
                } else if ((vs.getNmEstado().equals("COTIZADO") && vs.getResultado().equals("ENVIO COTIZACION"))
                        || (vs.getNmEstado().equals("SEGUIMIENTO") && (vs.getResultado().equals("EN GESTION") || vs.getResultado().equals("PENDIENTE")))) {
                    seguimientos.setResultado("");
                    fieldsetCliente = false;
                    fieldsetFechaProspecto = false;
                    fieldsetFuente = false;
                    fieldsetResultado = true;
                    fieldsetCotizacion = false;
                    fieldsetObservacion = true;
                    fieldsetCierre = false;
                    botonRegistrar = false;
                    botonContacto = false;
                    botonCotizado = false;
                    botonCerrar = false;
                    botonRechazo = false;
                    observacion = true;
                    fcRecordatorio = true;
                    observacionCierre = false;
                    botonSeguimiento = true;
                    nmPantalla = "SEGUIMIENTO";
                    listaResultados = resultadoDAO.findByFk("WHERE t.proceso = 'SEGUIMIENTO'");
                    RequestContext.getCurrentInstance().execute("PF('registrar').show();");
                } else if (vs.getNmEstado().equals("SEGUIMIENTO") && vs.getResultado().equals("CONCRETADO")) {

                    seguimientos.setResultado("");
                    fieldsetCliente = false;
                    fieldsetFechaProspecto = false;
                    fieldsetFuente = false;
                    fieldsetResultado = true;
                    fieldsetCotizacion = false;
                    fieldsetObservacion = true;
                    fieldsetCierre = true;
                    botonRegistrar = false;
                    botonContacto = false;
                    botonCotizado = false;
                    botonCerrar = true;
                    botonRechazo = false;
                    observacion = true;
                    fcRecordatorio = false;
                    observacionCierre = false;
                    nmPantalla = "CIERRE";
                    listaResultados = resultadoDAO.findByFk("WHERE t.proceso = 'CIERRE'");
                    RequestContext.getCurrentInstance().execute("PF('registrar').show();");
                } else {
                    Mensajes.mensajeError("RESULTADO INCORRECTO.");
                }
            } else {
                Mensajes.mensajeError("IMPOSIBLE INGRESAR YA QUE NO ES EL USUARIO CORRESPONDIENTE AL SEGUIMIENTO.");
            }
        } catch (Exception e) {
            Mensajes.mensajeError(e.getLocalizedMessage());
        }

    }

    public void productoPorRamo() {
        listaProductos = productosDAO.findByFk("where t.cdRamo.cdRamo = " + cdRamo + " order by t.producto ");
    }

    public void contacto() {
        proceso = "CONTACTO";
        if (validar()) {
            try {
                seguimientosDAO.update(seguimientos);
                seguimientoEstados.setCdSegest(seguimientoEstadosDAO.getMaxIdDos("cdSegest"));
                seguimientoEstados.setCdSeguimiento(seguimientos);
                seguimientoEstados.setCdEstado(segEstadosDAO.findByFk("WHERE t.nmEstado = 'CONTACTADO'").get(0));
                seguimientoEstadosDAO.create(seguimientoEstados);
                Mensajes.mensajeGrabarCorrecto();
                init();
                RequestContext.getCurrentInstance().execute("PF('registrar').hide();");
            } catch (Exception e) {
                Mensajes.mensajeError(e.getMessage());
            }
        }
    }

    public void cotizacion() {
        proceso = "COTIZACION";
        if (validar()) {
            try {
                seguimientos.setCdAseguradora(aseguradorasDAO.findById(cdAseguradora));
                if (!ramosProd.isEmpty()) {
                    seguimientos.setCdRamo(ramosProd.get(0).getCdRamo());
                    if (ramosProd.get(0).getCdProducto() != null) {
                        seguimientos.setCdProducto(ramosProd.get(0).getCdProducto());
                    }
                }
                seguimientosDAO.update(seguimientos);
                seguimientoEstados.setCdSegest(seguimientoEstadosDAO.getMaxIdDos("cdSegest"));
                seguimientoEstados.setCdSeguimiento(seguimientos);
                seguimientoEstados.setCdEstado(segEstadosDAO.findByFk("WHERE t.nmEstado = 'COTIZADO'").get(0));
                seguimientoEstadosDAO.create(seguimientoEstados);
                if (ramosProd.size() > 1) {
                    for (int i = 1; i < ramosProd.size(); i++) {
                        seguimientos.setCdSeguimiento(seguimientosDAO.getMaxIdDos("cdSeguimiento"));
                        seguimientos.setCdRamo(ramosProd.get(i).getCdRamo());
                        if (ramosProd.get(i).getCdProducto() != null) {
                            seguimientos.setCdProducto(ramosProd.get(i).getCdProducto());
                        } else {
                            seguimientos.setCdProducto(null);
                        }
                        seguimientosDAO.create(seguimientos);
//                        seguimientoEstados.setCdSegest(seguimientoEstadosDAO.getMaxIdDos("cdSegest"));
//                        seguimientoEstados.setCdSeguimiento(seguimientos);
//                        seguimientoEstados.setCdEstado(segEstadosDAO.findByFk("WHERE t.nmEstado = 'COTIZADO - PENDIENTE'").get(0));
//                        seguimientoEstadosDAO.create(seguimientoEstados);
                    }
                    //LLAMAR A PROCEDIMIENTO PARA COPIAR HISTORIAL DE SEGUIMIENTO
                    Connection cn = null;
                    try {
                        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                        //cn = DriverManager.getConnection("jdbc:oracle:thin:@130.130.130.124:1521:efiquality", "seguimiento", "seguimiento");
                        cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "seguimiento", "seguimiento");
                        cn.setAutoCommit(false);

                        CallableStatement cst = cn.prepareCall("{call sp_copia_seguimiento_ramo(?)}");
                        cst.setInt(1, seguimientos.getCdSegOrigen());
                        cst.executeUpdate();

                    } catch (SQLException ex) {
                        cn.rollback();
                    }

                }
                Mensajes.mensajeGrabarCorrecto();
                init();
                RequestContext.getCurrentInstance().execute("PF('registrar').hide();");
            } catch (Exception e) {
                Mensajes.mensajeError(e.getMessage());
            }
        }
    }

    public void seguimiento() {
        proceso = "SEGUIMIENTO";
        if (validar()) {
            try {
                seguimientosDAO.update(seguimientos);
                seguimientoEstados.setCdSegest(seguimientoEstadosDAO.getMaxIdDos("cdSegest"));
                seguimientoEstados.setCdSeguimiento(seguimientos);
                seguimientoEstados.setCdEstado(segEstadosDAO.findByFk("WHERE t.nmEstado = 'SEGUIMIENTO'").get(0));
                seguimientoEstadosDAO.create(seguimientoEstados);

                Connection cn = null;
                try {
                    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                    //cn = DriverManager.getConnection("jdbc:oracle:thin:@130.130.130.124:1521:efiquality", "seguimiento", "seguimiento");
                    cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "seguimiento", "seguimiento");
                    cn.setAutoCommit(false);

                    CallableStatement cst = cn.prepareCall("{call sp_copia_est_seg_activo(?,?)}");
                    cst.setInt(1, seguimientos.getCdSegOrigen());
                    cst.setInt(2, seguimientos.getCdSeguimiento().intValue());
                    cst.executeUpdate();

                } catch (SQLException ex) {
                    cn.rollback();
                }
                Mensajes.mensajeGrabarCorrecto();
                init();
                RequestContext.getCurrentInstance().execute("PF('registrar').hide();");
            } catch (Exception e) {
                Mensajes.mensajeError(e.getMessage());
            }
        }
    }

    public void cerrar() {
        proceso = "CIERRE";
        if (validar()) {
            seguimientosDAO.update(seguimientos);
            seguimientoEstados.setCdSegest(seguimientoEstadosDAO.getMaxIdDos("cdSegest"));
            seguimientoEstados.setCdSeguimiento(seguimientos);
            seguimientoEstados.setCdEstado(segEstadosDAO.findByFk("WHERE t.nmEstado = 'VENTA - CERRADO'").get(0));
            seguimientoEstadosDAO.create(seguimientoEstados);
            Mensajes.mensajeGrabarCorrecto();
            init();
            RequestContext.getCurrentInstance().execute("PF('registrar').hide();");
        }
    }

    public void cerrarSegReecazo() {
        proceso = "RECHAZO";
        if (validar()) {
            seguimientosDAO.update(seguimientos);
            seguimientoEstados.setCdSegest(seguimientoEstadosDAO.getMaxIdDos("cdSegest"));
            seguimientoEstados.setCdSeguimiento(seguimientos);
            if (seguimientos.getResultado().equals("NO CONTACTADO")) {
                seguimientoEstados.setCdEstado(segEstadosDAO.findByFk("WHERE t.nmEstado = 'NO CONTACTADO - CERRADO'").get(0));
            } else if (seguimientos.getResultado().equals("NO COTIZADO")) {
                seguimientoEstados.setCdEstado(segEstadosDAO.findByFk("WHERE t.nmEstado = 'NO COTIZADO - CERRADO'").get(0));
            } else if (seguimientos.getResultado().equals("NO GANADO")) {
                seguimientoEstados.setCdEstado(segEstadosDAO.findByFk("WHERE t.nmEstado = 'NO GANADO - CERRADO'").get(0));
            }
            seguimientoEstadosDAO.create(seguimientoEstados);
            Mensajes.mensajeGrabarCorrecto();
            init();
            RequestContext.getCurrentInstance().execute("PF('registrar').hide();");
        }
    }

    public void rechazos() {
        try {
            if (seguimientos.getResultado().equals("NO CONTACTADO")
                    || seguimientos.getResultado().equals("NO COTIZADO")
                    || seguimientos.getResultado().equals("NO GANADO")) {
                observacion = false;
                observacionCierre = true;
                fieldsetCotizacion = false;
                botonRegistrar = false;
                botonContacto = false;
                botonCotizado = false;
                botonCerrar = false;
                fcRecordatorio = false;
                botonRechazo = true;
                listaCierre = cierreDAO.findByFk("WHERE t.cdResultado.desResultado = '" + seguimientos.getResultado() + "'");
            } else {
                observacion = true;

                observacionCierre = false;
                if (seguimientos.getResultado().equals("CONTACTADO") || seguimientos.getResultado().equals("CONTACTO PENDIENTE")) {
                    botonRegistrar = false;
                    
                    botonContacto = true;
                    botonCotizado = false;
                    botonCerrar = false;
                    botonRechazo = false;
                    fcRecordatorio = true;
                } else if (seguimientos.getResultado().equals("ENVIO COTIZACION") || seguimientos.getResultado().equals("PENDIENTE COTIZACION")) {
                    if (seguimientos.getResultado().equals("ENVIO COTIZACION")) {
                        fieldsetCotizacion = true;
                        
                    } else {
                        fieldsetCotizacion = false;
                        
                    }
                    botonRegistrar = false;
                    botonContacto = false;
                    botonCotizado = true;
                    botonCerrar = false;
                    botonRechazo = false;
                } else if (seguimientos.getResultado().equals("VENTA REALIZADA")) {
                    botonRegistrar = false;
                    botonContacto = false;
                    botonCotizado = false;
                    botonCerrar = true;
                    botonRechazo = false;
                }
            }

        } catch (Exception e) {
        }
    }

    public void seleccionarRamo() {
        try {
            int cont = 0;
            for (Ramos r : vistaRamos) {
                if (cdRamo == r.getCdRamo()) {
                    cont = cont + 1;
                }
            }
            if (cont == 0) {
                vistaRamos.add(ramosDAO.findById(cdRamo));
                cdRamo = 0L;
            } else {
                Mensajes.mensajeError("El ramo ya se encuentra seleccionado");
            }
        } catch (Exception e) {
            Mensajes.mensajeError(e.getMessage());
        }
    }

    public void selecccionarRamoProd() {
        try {
            if(cdRamo != 0L){
            int cont = 0;
            for (RamosProd p : ramosProd) {
                if (cdProducto == 0L) {
                    if (p.getCdRamo().getCdRamo() == cdRamo && p.getCdProducto() == null) {
                        cont = cont + 1;
                    }
                } else if (p.getCdProducto() != null) {
                    if (p.getCdRamo().getCdRamo() == cdRamo && p.getCdProducto().getCdProducto() == cdProducto) {
                        cont = cont + 1;
                    }
                }
            }
            if (cont == 0) {
                ramoProducto.setCdRamo(ramosDAO.findById(cdRamo));
                if (cdProducto != 0L) {
                    ramoProducto.setCdProducto(productosDAO.findById(cdProducto));
                }
                ramosProd.add(ramoProducto);
                cdProducto = 0L;
                cdRamo = 0L;
                ramoProducto = new RamosProd();
                
            } else {
                Mensajes.mensajeError("El ramo con el producto seleccionado ya se encuentra registrado");
            }
            }else{
                Mensajes.mensajeError("Para añadir debe seleccionar un ramo");
            }
        } catch (Exception e) {
        }
    }

    public void eliminarRamo(RamosProd ramProd) {
        try {
            ramosProd.remove(ramProd);
        } catch (Exception e) {
            Mensajes.mensajeError(e.getCause().getCause().getMessage());
        }
    }

    public void registrarCliente() {
        clientes = clientesDAO.findById(clientesDAO.getMaxIdDos("cdCliente") - 1L);
        RequestContext.getCurrentInstance().execute("PF('wvRegistrar').hide();");
    }

    public Boolean validar() {
        Boolean validacion = true;

        if (proceso.equals("REGISTRO")) {
            if (cdFuente == 0) {
                Mensajes.mensajeError("Seleccione la fuente del cliente para continuar.");
                return false;
            }
            if (seguimientos.getFcProspecto() == null) {
                Mensajes.mensajeError("Ingrese fecha de prospectacion del cliente.");
                return false;
            }

        } else if (proceso.equals("CONTACTO")) {
            if (seguimientos.getResultado().equals("")) {
                Mensajes.mensajeError("Seleccione el resultado del contacto.");
                return false;
            }
        } else if (proceso.equals("COTIZACION")) {
            if (seguimientos.getResultado().equals("")) {
                Mensajes.mensajeError("Seleccione el resultado del contacto.");
                return false;
            }
            if (!seguimientos.getResultado().equals("PENDIENTE COTIZACION")) {
                if (seguimientos.getFcCotizacion() == null) {
                    Mensajes.mensajeError("Ingrese fecha de cotizacion.");
                    return false;
                }

                if (ramosProd.isEmpty()) {
                    Mensajes.mensajeError("Para registrar agrege al menos un ramo.");
                    return false;
                }
            }
        } else if (proceso.equals("SEGUIMIENTO")) {
            if (seguimientos.getResultado().equals("")) {
                Mensajes.mensajeError("Seleccione el resultado del contacto.");
                return false;
            }
        }else if (proceso.equals("CIERRE")){
            if (seguimientos.getResultado().equals("")) {
                Mensajes.mensajeError("Seleccione el resultado del contacto.");
                return false;
            }
        }else if(proceso.equals("RECHAZO")){
            if (seguimientos.getResultado().equals("")) {
                Mensajes.mensajeError("Seleccione el resultado del contacto.");
                return false;
            }
            
            if(cdAseguradora == 0L){
                Mensajes.mensajeError("Seleccione una compania de seguros.");
                return false;
            }
            
            if(seguimientoEstados.getDetalleCierre().equals("")){
                Mensajes.mensajeError("Ingrese un detalle de cierre.");
                return false;
            }
        }
        if (seguimientoEstados.getObservacion().equals("")) {
            Mensajes.mensajeError("Para continuar ingrese una observacion.");
            return false;
        }

        return validacion;
    }

    //Metodos de acceso
    public List<VSeguimiento> getVistaSeguimiento() {
        return vistaSeguimiento;
    }

    public void setVistaSeguimiento(List<VSeguimiento> vistaSeguimiento) {
        this.vistaSeguimiento = vistaSeguimiento;
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

    public List<Fuente> getListaFuentes() {
        return listaFuentes;
    }

    public void setListaFuentes(List<Fuente> listaFuentes) {
        this.listaFuentes = listaFuentes;
    }

    public SeguimientoEstados getSeguimientoEstados() {
        return seguimientoEstados;
    }

    public void setSeguimientoEstados(SeguimientoEstados seguimientoEstados) {
        this.seguimientoEstados = seguimientoEstados;
    }

    public Long getCdFuente() {
        return cdFuente;
    }

    public void setCdFuente(Long cdFuente) {
        this.cdFuente = cdFuente;
    }

    public Seguimientos getSeguimientos() {
        return seguimientos;
    }

    public void setSeguimientos(Seguimientos seguimientos) {
        this.seguimientos = seguimientos;
    }

    public List<Aseguradoras> getListaAseguradoras() {
        return listaAseguradoras;
    }

    public void setListaAseguradoras(List<Aseguradoras> listaAseguradoras) {
        this.listaAseguradoras = listaAseguradoras;
    }

    public Long getCdAseguradora() {
        return cdAseguradora;
    }

    public void setCdAseguradora(Long cdAseguradora) {
        this.cdAseguradora = cdAseguradora;
    }

    public List<Ramos> getListaRamos() {
        return listaRamos;
    }

    public void setListaRamos(List<Ramos> listaRamos) {
        this.listaRamos = listaRamos;
    }

    public Long getCdRamo() {
        return cdRamo;
    }

    public void setCdRamo(Long cdRamo) {
        this.cdRamo = cdRamo;
    }

    public List<Ramos> getVistaRamos() {
        return vistaRamos;
    }

    public void setVistaRamos(List<Ramos> vistaRamos) {
        this.vistaRamos = vistaRamos;
    }

    public Boolean getFieldsetCliente() {
        return fieldsetCliente;
    }

    public void setFieldsetCliente(Boolean fieldsetCliente) {
        this.fieldsetCliente = fieldsetCliente;
    }

    public Boolean getFieldsetFuente() {
        return fieldsetFuente;
    }

    public void setFieldsetFuente(Boolean fieldsetFuente) {
        this.fieldsetFuente = fieldsetFuente;
    }

    public Boolean getFieldsetResultado() {
        return fieldsetResultado;
    }

    public void setFieldsetResultado(Boolean fieldsetResultado) {
        this.fieldsetResultado = fieldsetResultado;
    }

    public Boolean getFieldsetCotizacion() {
        return fieldsetCotizacion;
    }

    public void setFieldsetCotizacion(Boolean fieldsetCotizacion) {
        this.fieldsetCotizacion = fieldsetCotizacion;
    }

    public Boolean getFieldsetObservacion() {
        return fieldsetObservacion;
    }

    public void setFieldsetObservacion(Boolean fieldsetObservacion) {
        this.fieldsetObservacion = fieldsetObservacion;
    }

    public Boolean getBotonRegistrar() {
        return botonRegistrar;
    }

    public void setBotonRegistrar(Boolean botonRegistrar) {
        this.botonRegistrar = botonRegistrar;
    }

    public Boolean getBotonContacto() {
        return botonContacto;
    }

    public void setBotonContacto(Boolean botonContacto) {
        this.botonContacto = botonContacto;
    }

    public Boolean getBotonCotizado() {
        return botonCotizado;
    }

    public void setBotonCotizado(Boolean botonCotizado) {
        this.botonCotizado = botonCotizado;
    }

    public Boolean getBotonCerrar() {
        return botonCerrar;
    }

    public void setBotonCerrar(Boolean botonCerrar) {
        this.botonCerrar = botonCerrar;
    }

    public List<Productos> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Productos> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Boolean getFieldsetCierre() {
        return fieldsetCierre;
    }

    public void setFieldsetCierre(Boolean fieldsetCierre) {
        this.fieldsetCierre = fieldsetCierre;
    }

    public Long getCdProducto() {
        return cdProducto;
    }

    public void setCdProducto(Long cdProducto) {
        this.cdProducto = cdProducto;
    }

    public Boolean getItemsContacto() {
        return itemsContacto;
    }

    public void setItemsContacto(Boolean itemsContacto) {
        this.itemsContacto = itemsContacto;
    }

    public Boolean getItemsCotizacion() {
        return itemsCotizacion;
    }

    public void setItemsCotizacion(Boolean itemsCotizacion) {
        this.itemsCotizacion = itemsCotizacion;
    }

    public Boolean getItemsCierre() {
        return itemsCierre;
    }

    public void setItemsCierre(Boolean itemsCierre) {
        this.itemsCierre = itemsCierre;
    }

    public List<Resultado> getListaResultados() {
        return listaResultados;
    }

    public void setListaResultados(List<Resultado> listaResultados) {
        this.listaResultados = listaResultados;
    }

    public List<Cierre> getListaCierre() {
        return listaCierre;
    }

    public void setListaCierre(List<Cierre> listaCierre) {
        this.listaCierre = listaCierre;
    }

    public Boolean getObservacion() {
        return observacion;
    }

    public void setObservacion(Boolean observacion) {
        this.observacion = observacion;
    }

    public Boolean getObservacionCierre() {
        return observacionCierre;
    }

    public void setObservacionCierre(Boolean observacionCierre) {
        this.observacionCierre = observacionCierre;
    }

    public boolean isBotonRechazo() {
        return botonRechazo;
    }

    public void setBotonRechazo(boolean botonRechazo) {
        this.botonRechazo = botonRechazo;
    }

    public String getNmPantalla() {
        return nmPantalla;
    }

    public void setNmPantalla(String nmPantalla) {
        this.nmPantalla = nmPantalla;
    }

    public Boolean getFieldsetFechaProspecto() {
        return fieldsetFechaProspecto;
    }

    public void setFieldsetFechaProspecto(Boolean fieldsetFechaProspecto) {
        this.fieldsetFechaProspecto = fieldsetFechaProspecto;
    }

    public Boolean getFcRecordatorio() {
        return fcRecordatorio;
    }

    public void setFcRecordatorio(Boolean fcRecordatorio) {
        this.fcRecordatorio = fcRecordatorio;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public List<Productos> getVistaProductos() {
        return vistaProductos;
    }

    public void setVistaProductos(List<Productos> vistaProductos) {
        this.vistaProductos = vistaProductos;
    }

    public List<RamosProd> getRamosProd() {
        return ramosProd;
    }

    public void setRamosProd(List<RamosProd> ramosProd) {
        this.ramosProd = ramosProd;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public Boolean getBotonRechazo() {
        return botonRechazo;
    }

    public void setBotonRechazo(Boolean botonRechazo) {
        this.botonRechazo = botonRechazo;
    }

    public Boolean getBotonSeguimiento() {
        return botonSeguimiento;
    }

    public void setBotonSeguimiento(Boolean botonSeguimiento) {
        this.botonSeguimiento = botonSeguimiento;
    }

}
