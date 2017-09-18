/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracing.bean;

/**
 *
 * @author u_murillod
 */
public interface Bean {
    void init();
    void create();
    void delete();
    void update();
    void findAll();
    void find();
    void cancel();
    Boolean validate();
}
