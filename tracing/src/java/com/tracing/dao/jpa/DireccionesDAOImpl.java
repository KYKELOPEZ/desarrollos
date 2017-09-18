/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracing.dao.jpa;

import com.tracing.dao.DireccionesDAO;
import com.tracing.modelo.Direcciones;
import javax.ejb.Stateless;

@Stateless
public class DireccionesDAOImpl extends AbstractBaseGenericDAOImpl<Direcciones, Long>implements DireccionesDAO {
    
}
