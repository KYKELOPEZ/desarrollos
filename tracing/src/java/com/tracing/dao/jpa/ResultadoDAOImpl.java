/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracing.dao.jpa;

import com.tracing.dao.ResultadoDAO;
import com.tracing.modelo.Resultado;
import javax.ejb.Stateless;

@Stateless
public class ResultadoDAOImpl extends AbstractBaseGenericDAOImpl<Resultado, Long>implements ResultadoDAO {
    
}
