package com.dekses.jersey.docker.demo.puntos;

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
@Path("fakenewspun")
public class MyResource {

 @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Puntos> getPuntos_JSON() throws UnknownHostException {
        List<Puntos> listOfPuntos = PuntosDAO.getAllPuntos();
        return listOfPuntos;
    }
 
    // URI:
    // /contextPath/servletPath/employees/{empNo}
    @GET
    @Path("/{email}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Puntos getPuntos(@PathParam("email") String email) throws UnknownHostException {
        return PuntosDAO.getpuntos(email);
    }
 
    // URI:
    // /contextPath/servletPath/employees
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void addPuntos(Puntos pu) {
        PuntosDAO.addPuntos(pu);
    }
 
    @PUT
    @Path("/{idEmail}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void updatePuntos(Puntos pu, @PathParam("idEmail") String idEmail) throws UnknownHostException {
        PuntosDAO.updatePuntos(pu, idEmail);
    }
 
    @DELETE
    @Path("/{email}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deletePuntos(@PathParam("email") String email) {
        PuntosDAO.deletePuntos(email);
    }    
}
