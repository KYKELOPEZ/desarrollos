/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracing.dao.jpa;

import com.tracing.dao.SeguimientosDAO;
import com.tracing.modelo.Seguimientos;
import javax.ejb.Stateless;

@Stateless
public class SeguimientosDAOImpl extends AbstractBaseGenericDAOImpl<Seguimientos, Long>implements SeguimientosDAO {
    
}
