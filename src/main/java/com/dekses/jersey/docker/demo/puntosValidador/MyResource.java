package com.dekses.jersey.docker.demo.puntosValidador;

import com.dekses.jersey.docker.demo.Validador.*;
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
@Path("fakenewspuntosval")
public class MyResource {

 @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<PuntosValidador> getPuntosvalidador_JSON() throws UnknownHostException {
        List<PuntosValidador> listOfPuntos = PuntosValidadorDAO.getAllPuntosvalidador();
        return listOfPuntos;
    }
 
    // URI:
    // /contextPath/servletPath/employees/{empNo}
    @GET
    @Path("/{email}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public PuntosValidador getPuntosvalidador(@PathParam("email") String email) throws UnknownHostException {
        return PuntosValidadorDAO.getpuntosvalidador(email);
    }
 
    // URI:
    // /contextPath/servletPath/employees
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void addPuntosvalidador(PuntosValidador pu) {
        PuntosValidadorDAO.addPuntosvalidador(pu);
    }
 
    @PUT
    @Path("/{idEmail}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void updatePuntosvalidador(PuntosValidador pu, @PathParam("idEmail") String idEmail) throws UnknownHostException {
        PuntosValidadorDAO.updatePuntosvalidador(pu, idEmail);
    }
 
    @DELETE
    @Path("/{email}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deletePuntosvalidador(@PathParam("email") String email) {
        PuntosValidadorDAO.deletePuntosvalidador(email);
    }    
}
