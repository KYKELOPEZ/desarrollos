/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracing.dao.jpa;

import com.tracing.dao.ProvinciasDAO;
import com.tracing.modelo.Provincias;
import javax.ejb.Stateless;

@Stateless
public class ProvinciasDAOImpl extends AbstractBaseGenericDAOImpl<Provincias, Long>implements ProvinciasDAO {
    
}
