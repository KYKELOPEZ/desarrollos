/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracing.dao;

import com.tracing.modelo.Companias;
import javax.ejb.Local;

/**
 *
 * @author cjlop
 */
@Local
public interface CompaniasDAO extends BaseGenericDAO<Companias, Long>{
    
}
