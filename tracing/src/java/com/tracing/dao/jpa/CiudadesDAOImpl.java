/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracing.dao.jpa;

import com.tracing.dao.CiudadesDAO;
import com.tracing.modelo.Ciudades;
import javax.ejb.Stateless;

@Stateless
public class CiudadesDAOImpl extends AbstractBaseGenericDAOImpl<Ciudades, Long>implements CiudadesDAO {
    
}
