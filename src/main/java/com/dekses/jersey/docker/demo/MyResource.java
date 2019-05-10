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
@Path("fakenews")
public class MyResource {

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Periodista> getPatient_GSON() throws UnknownHostException {
        List<Periodista> listOfPeriodista = PeriodistaDAO.getAllPeriodistas();
        return listOfPeriodista;
    }
 
   
    // URI:
    // /contextPath/servletPath/employees
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void addPatient(Periodista p) {
        PeriodistaDAO.addPeriodista(p);
    }
 
    @PUT
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void updatePatient(Periodista p) throws UnknownHostException {
        PeriodistaDAO.updatePeriodista(p);
    }
 
    @DELETE
    @Path("/{email}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deletePatient(@PathParam("email") String email) {
        PeriodistaDAO.deletePeriodista(email);
    }    
    
}
