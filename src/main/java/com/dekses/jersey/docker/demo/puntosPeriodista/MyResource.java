package com.dekses.jersey.docker.demo.puntosPeriodista;

import com.dekses.jersey.docker.demo.puntosValidador.*;
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
@Path("fakenewspuntosper")
public class MyResource {

 @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<PuntosPeriodista> getPuntosperiodista_JSON() throws UnknownHostException {
        List<PuntosPeriodista> listOfPuntos = PuntosPeriodistaDAO.getAllPuntosperiodista();
        return listOfPuntos;
    }
 
    // URI:
    // /contextPath/servletPath/employees/{empNo}
    @GET
    @Path("/{email}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public PuntosPeriodista getPuntosperiodista(@PathParam("email") String email) throws UnknownHostException {
        return PuntosPeriodistaDAO.getpuntosperiodista(email);
    }
 
    // URI:
    // /contextPath/servletPath/employees
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void addPuntosperiodista(PuntosPeriodista pu) {
        PuntosPeriodistaDAO.addPuntosperiodista(pu);
    }
 
    @PUT
    @Path("/{idEmail}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void updatePuntosperiodista(PuntosPeriodista pu, @PathParam("idEmail") String idEmail) throws UnknownHostException {
        PuntosPeriodistaDAO.updatePuntosperiodista(pu, idEmail);
    }
 
    @DELETE
    @Path("/{email}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deletePuntosperiodista(@PathParam("email") String email) {
        PuntosPeriodistaDAO.deletePuntosperiodista(email);
    }    
}
