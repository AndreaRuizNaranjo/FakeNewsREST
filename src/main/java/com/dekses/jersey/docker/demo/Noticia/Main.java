package com.dekses.jersey.docker.demo.Noticia;

import com.dekses.jersey.docker.demo.*;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Scanner;
import javax.ws.rs.core.MediaType;

/**
 * Main class.
 *
 */
public class Main {

    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/myapp/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
     * application.
     *
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.dekses.jersey.docker.demo package
        final ResourceConfig rc = new ResourceConfig().packages("com.dekses.jersey.docker.demo.Noticia");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        Client client = Client.create();
        WebResource webResource = null;
        ClientResponse response = null;

        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        // System.in.read();
        // server.stop();

        webResource = client.resource("http://localhost:8080/myapp/fakenews");

        try {

            int opcion = -1;

            Scanner lectura = new Scanner(System.in);
            System.out.println("¡Bienvenido al sistema de noticias Selecciona una opción: \n\n");
            Noticia not = new Noticia();

            do {

                System.out.println("1. Mostrar Todos.\n 2. Crear.\n 3. Buscar.\n 4.Modificar.\n 5. Borrar.\n 6. Salir.");
                opcion = lectura.nextInt();

                ObjectMapper mapper = new ObjectMapper();

                switch (opcion) {

                    case 1: // Mostrar Todos

                        List Listanoticias = NoticiaDAO.getAllNoticia();

                        for (int i = 0; i < Listanoticias.size(); i++) {
                            not = (Noticia) Listanoticias.get(i);
                            System.out.println(not.toString());
                        }

                        break;

                    case 2: // Crear

                        System.out.println("Ingrese el titulo de la noticia");
                        String ignorar = lectura.nextLine();
                        String titulo = lectura.nextLine();
                        not.setTitulo(titulo);

                        System.out.println("Ingrese el autor");
                        String autor = lectura.nextLine();
                        not.setAutor(autor);

                        System.out.println("Ingrese la fuente");
                        String fuente = lectura.nextLine();
                        not.setFuente(fuente);

                        System.out.println("Ingrese la fecha");
                        String fecha = lectura.nextLine();
                        not.setFecha(fecha);

                        System.out.println("Ingrese 1 si esta validada o 2 si no esta validado");
                        int aux = Integer.parseInt(lectura.nextLine());
                        boolean validado;
                        if (aux == 1) {
                            validado = true;
                        } else {
                            validado = false;
                        }
                        not.setValidado(validado);

                        String input = mapper.writeValueAsString(not);
                        response = webResource.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, input);
                        NoticiaDAO.addNoticia(not);
                        System.out.println("Listo! c: ");

                        break;

                    case 3: // Buscar.

                        System.out.println("Digite el titulo de la noticia: \n");
                        ignorar = lectura.nextLine();
                        String id = lectura.nextLine();

                        Noticia noticiaRetorno = NoticiaDAO.getNoticia(id);
                        System.out.println(noticiaRetorno.toString());

                        break;

                        case 4: // Modificar.
                        
                       System.out.println("Ingrese el titulo de la noticia");
                        ignorar = lectura.nextLine();
                        String Idtitulo = lectura.nextLine();
                        not.setTitulo(Idtitulo);

                        System.out.println("Ingrese el autor");
                        autor = lectura.nextLine();
                        not.setAutor(autor);

                        System.out.println("Ingrese la fuente");
                        fuente = lectura.nextLine();
                        not.setFuente(fuente);

                        System.out.println("Ingrese la fecha");
                        fecha = lectura.nextLine();
                        not.setFecha(fecha);

                        System.out.println("Ingrese 1 si esta validada o 2 si no esta validado");
                        aux = Integer.parseInt(lectura.nextLine());
                        
                        if (aux == 1) {
                            validado = true;
                        } else {
                            validado = false;
                        }
                        not.setValidado(validado);


                        NoticiaDAO.updateNoticia(not, Idtitulo);
                        System.out.println("Listo! c: ");

                          break;
                    case 5: // Borrar.

                        System.out.println("Digite el nombre de la noticia a eliminar: \n");
                        ignorar = lectura.nextLine();
                        titulo = lectura.nextLine();

                        NoticiaDAO.deleteNoticia(titulo);
                        System.out.println("Listo! c: ");

                        break;

                    default: // Salir.
                        lectura.close();
                        System.out.println("Adiós! :c ");

                }

            } while (opcion != 6);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
