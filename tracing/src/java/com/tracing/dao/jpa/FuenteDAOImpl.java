/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracing.dao.jpa;

import com.tracing.dao.FuenteDAO;
import com.tracing.modelo.Fuente;
import javax.ejb.Stateless;

@Stateless
public class FuenteDAOImpl extends AbstractBaseGenericDAOImpl<Fuente, Long>implements FuenteDAO {
    
}
