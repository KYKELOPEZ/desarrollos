/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracing.dao.jpa;

import com.tracing.dao.CompaniasDAO;
import com.tracing.modelo.Companias;
import java.io.Serializable;
import javax.ejb.Stateless;

@Stateless
public class CompaniasDAOImpl extends AbstractBaseGenericDAOImpl<Companias , Long>implements CompaniasDAO {
    
}
