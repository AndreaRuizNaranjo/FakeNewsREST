package com.dekses.jersey.docker.demo.Noticia;

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
@Path("fakenews")
public class MyResource {

 @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Noticia> getNoticia_JSON() throws UnknownHostException {
        List<Noticia> listOfNoticia = NoticiaDAO.getAllNoticia();
        return listOfNoticia;
    }
 
    // URI:
    // /contextPath/servletPath/employees/{empNo}
    @GET
    @Path("/{titulo}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Noticia getTitulo(@PathParam("titulo") String titulo) throws UnknownHostException {
        return NoticiaDAO.getNoticia(titulo);
    }
 
    // URI:
    // /contextPath/servletPath/employees
    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    public Noticia addNoticia(Noticia n) {
        System.out.println("alsfjapskdjfklasdf");
        NoticiaDAO.addNoticia(n);
        return n;
    }
 
    @PUT
    @Path("/{Idtitulo}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void updateNoticia(Noticia p, @PathParam("Idtitulo") String Idtitulo) throws UnknownHostException {
        NoticiaDAO.updateNoticia(p, Idtitulo);
    }
 
    @DELETE
    @Path("/{titulo}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deleteNoticia(@PathParam("titulo") String titulo) {
        NoticiaDAO.deleteNoticia(titulo);
    }    
}
