package com.dekses.jersey.docker.demo;

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
@Path("fakenewsper")
public class MyResource {

 @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Periodista> getPeriodista_JSON() throws UnknownHostException {
        List<Periodista> listOfPeriodista = PeriodistaDAO.getAllPeriodista();
        return listOfPeriodista;
    }
 
    // URI:
    // /contextPath/servletPath/employees/{empNo}
    @GET
    @Path("/{nombre}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Periodista getPeriodista(@PathParam("nombre") String nombre) throws UnknownHostException {
        return PeriodistaDAO.getPeriodista(nombre);
    }
 
    // URI:
    // /contextPath/servletPath/employees
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void addPeriodista(Periodista p) {
        PeriodistaDAO.addPeriodista(p);
    }
 
    @PUT
    @Path("/{idNombre}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void updatePeriodista(Periodista p, @PathParam("idNombre") String idNombre) throws UnknownHostException {
        PeriodistaDAO.updatePeriodista(p, idNombre);
    }
 
    @DELETE
    @Path("/{nombre}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deletePeriodista(@PathParam("nombre") String nombre) {
        PeriodistaDAO.deletePeriodista(nombre);
    }    
}
