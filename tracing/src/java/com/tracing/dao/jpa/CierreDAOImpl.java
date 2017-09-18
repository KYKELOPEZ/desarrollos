/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracing.dao.jpa;

import com.tracing.dao.CierreDAO;
import com.tracing.modelo.Cierre;
import javax.ejb.Stateless;

@Stateless
public class CierreDAOImpl extends AbstractBaseGenericDAOImpl<Cierre, Long>implements CierreDAO {
    
}
