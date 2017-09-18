/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracing.dao.jpa;

import com.tracing.dao.MenuDAO;
import com.tracing.modelo.Menu;
import javax.ejb.Stateless;

@Stateless
public class MenuDAOImpl extends AbstractBaseGenericDAOImpl<Menu, Long>implements MenuDAO {
    
}
