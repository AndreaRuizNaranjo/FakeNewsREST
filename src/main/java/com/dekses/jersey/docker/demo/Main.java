package com.dekses.jersey.docker.demo;

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
    public static final String BASE_URI = "http://localhost:8080/myapp/fakenews";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
     * application.
     *
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.dekses.jersey.docker.demo package
        final ResourceConfig rc = new ResourceConfig().packages("com.dekses.jersey.docker.demo");

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
            System.out.println("Bienvenido al sistema de periodistas");
            Periodista periodistas = new Periodista();

            do {

                System.out.println("1. Mostrar Todos \n 2. Crear. \n 3.Modificar \n 4. Borrar \n 5. Salir");
                opcion = lectura.nextInt();

                ObjectMapper mapper = new ObjectMapper();

                switch (opcion) {
                    case 1: // Mostrar Todos

                        List Periodistas = PeriodistaDAO.getAllPeriodistas();

                        for (int i = 0; i < Periodistas.size(); i++) {
                            periodistas = (Periodista) Periodistas.get(i);
                            Periodistas.toString();
                        }
                        System.out.println("La lista de los periodistas son: " + Periodistas);

                        break;

                    case 2: // Crear

                        System.out.println("Ingrese el nombre");
                        String nombre = lectura.next();
                        periodistas.setNombre(nombre);

                        System.out.println("Ingrese el email");
                        String email = lectura.next();
                        periodistas.setEmail(email);

                        System.out.println("Ingrese la contraseña");
                        String contrasena = lectura.next();
                        periodistas.setContrasena(contrasena);


                        String input = mapper.writeValueAsString(periodistas);

                        response = webResource.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, input);

                        PeriodistaDAO.addPeriodista(periodistas);

                        System.out.println("Agregado exitosamente");

                        break;

                    case 3: // Modificar.

                        System.out.println("Ingrese el nombre");
                        nombre = lectura.next();
                        periodistas.setNombre(nombre);

                        System.out.println("Ingrese el email");
                        email = lectura.next();
                        periodistas.setEmail(email);

                        System.out.println("Ingrese la fecha de nacimiento");
                        contrasena = lectura.next();
                        periodistas.setContrasena(contrasena);
                        PeriodistaDAO.updatePeriodista(periodistas);
                        
                        System.out.println("Modificado exitosamente");

                        break;

                    case 4: // Borrar.

                        System.out.println("Digite el email del usuario a eliminar: \n");
                        email = lectura.next();

                        PeriodistaDAO.deletePeriodista(email);
                        System.out.println("Eliminado exitosamente ");

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
