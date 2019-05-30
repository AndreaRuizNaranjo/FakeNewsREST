/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dekses.jersey.docker.demo.puntosValidador;

import com.dekses.jersey.docker.demo.Validador.*;

/**
 *
 * @author andre
 */
public class PuntosValidador {
    private String email;
    private String puntos;
    private boolean validado; 
 

   public PuntosValidador() {
            }

    public PuntosValidador(String email, String puntos, boolean validado) {
        this.email = email;
        this.puntos = puntos;
        this.validado = validado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPuntos() {
        return puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }

    public boolean getValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    @Override
    public String toString() {
        return "Puntos{" + "email=" + email + ", puntos=" + puntos + ", validado=" + validado + '}';
    }

    
    
}
