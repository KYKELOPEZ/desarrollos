/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracing.dao.jpa;

import com.tracing.dao.UsuariosDAO;
import com.tracing.modelo.Usuarios;
import javax.ejb.Stateless;

@Stateless
public class UsuariosDAOImpl extends AbstractBaseGenericDAOImpl<Usuarios, Long>implements UsuariosDAO {
    
}
