/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracing.dao.jpa;

import com.tracing.dao.TelefonosDAO;
import com.tracing.modelo.Telefonos;
import javax.ejb.Stateless;


@Stateless
public class TelefonosDAOImpl extends AbstractBaseGenericDAOImpl<Telefonos, Long>implements TelefonosDAO {
    
}
