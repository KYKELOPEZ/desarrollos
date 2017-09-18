/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracing.dao.jpa;

import com.tracing.dao.ProductosDAO;
import com.tracing.modelo.Productos;
import javax.ejb.Stateless;

@Stateless
public class ProductosDAOImpl extends AbstractBaseGenericDAOImpl<Productos, Long>implements ProductosDAO {
    
}
