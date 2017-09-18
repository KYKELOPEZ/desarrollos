/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracing.dao.jpa;

import com.tracing.dao.RamosDAO;
import com.tracing.modelo.Ramos;
import javax.ejb.Stateless;

@Stateless
public class RamosDAOImpl extends AbstractBaseGenericDAOImpl<Ramos, Long>implements RamosDAO {
    
}
