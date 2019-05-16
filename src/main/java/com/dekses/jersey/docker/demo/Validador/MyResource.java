package com.dekses.jersey.docker.demo.Validador;

import com.dekses.jersey.docker.demo.*;
import java.net.UnknownHostException;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("fakenewsval")
public class MyResource {

 @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Validador> getValidador_JSON() throws UnknownHostException {
        List<Validador> listOfValidador = ValidadorDAO.getAllValidador();
        return listOfValidador;
    }
 
    // URI:
    // /contextPath/servletPath/employees/{empNo}
    @GET
    @Path("/{nombre}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Validador getValidador(@PathParam("nombre") String nombre) throws UnknownHostException {
        return ValidadorDAO.getValidador(nombre);
    }
 
    // URI:
    // /contextPath/servletPath/employees
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void addValidador(Validador v) {
        ValidadorDAO.addValidador(v);
    }
 
    @PUT
    @Path("/{idNombre}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void updateValidador(Validador v, @PathParam("idNombre") String idNombre) throws UnknownHostException {
        ValidadorDAO.updateValidador(v, idNombre);
    }
 
    @DELETE
    @Path("/{nombre}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deleteValidador(@PathParam("nombre") String nombre) {
        ValidadorDAO.deleteValidador(nombre);
    }    
}
