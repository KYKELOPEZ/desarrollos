/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracing.dao.jpa;

import com.tracing.dao.PerfilesDAO;
import com.tracing.modelo.Perfiles;
import javax.ejb.Stateless;

@Stateless
public class PerfilesDAOImpl extends AbstractBaseGenericDAOImpl<Perfiles, Long>implements PerfilesDAO {
    
}
