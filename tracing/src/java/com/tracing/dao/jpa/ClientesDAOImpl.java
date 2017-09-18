/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracing.dao.jpa;

import com.tracing.dao.ClientesDAO;
import com.tracing.modelo.Clientes;
import javax.ejb.Stateless;

@Stateless
public class ClientesDAOImpl extends AbstractBaseGenericDAOImpl<Clientes, Long>implements ClientesDAO {
    
}
