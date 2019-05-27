/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dekses.jersey.docker.demo.Noticia;

import com.dekses.jersey.docker.demo.*;

/**
 *
 * @author white
 */
public class Noticia {
   
    private String titulo;
    private String autor;
    private String fuente;
    private String fecha;
    private String descripcion;
    private boolean validado;
   
    

   public Noticia() {
            }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }


    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
     public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
  

    public boolean getValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    @Override
    public String toString() {
        return "Noticia{" + "titulo=" + titulo + ", autor=" + autor + ", fuente=" + fuente + ", fecha=" + fecha + ", descripcion=" + descripcion + ", validado=" + validado + '}';
    }

   
   
    
    
}
