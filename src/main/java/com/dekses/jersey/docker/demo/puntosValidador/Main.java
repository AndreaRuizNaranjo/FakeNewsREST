package com.dekses.jersey.docker.demo.puntosValidador;

import com.dekses.jersey.docker.demo.Validador.*;
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
        final ResourceConfig rc = new ResourceConfig().packages("com.dekses.jersey.docker.demo.puntosValidador");

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

        webResource = client.resource("http://localhost:8080/myapp/fakenewspuntosval");

        try {

            int opcion = -1;

            Scanner lectura = new Scanner(System.in);
            System.out.println("¡Bienvenido al sistema de puntos Selecciona una opción: \n\n");
            PuntosValidador pun = new PuntosValidador();

            do {

                System.out.println("1. Mostrar Todos.\n 2. Crear.\n 3. Buscar.\n 4.Modificar.\n 5. Borrar.\n 6. Salir.");
                opcion = lectura.nextInt();

                ObjectMapper mapper = new ObjectMapper();

                switch (opcion) {
                    
                    case 1: // Mostrar Todos

                        List Listapuntos = PuntosValidadorDAO.getAllPuntosvalidador();

                        for (int i = 0; i < Listapuntos.size(); i++) {
                            pun = (PuntosValidador) Listapuntos.get(i);
                            System.out.println(pun.toString());
                        }

                        break;

                    case 2: // Crear

                        System.out.println("Ingrese el email");
                        String ignorar = lectura.nextLine();
                        String email = lectura.nextLine();
                        pun.setEmail(email);

                        System.out.println("Ingrese los puntos");
                        String puntos = lectura.nextLine();
                        pun.setPuntos(puntos);

                        System.out.println("Ingrese 1 si esta validada o 2 si no esta validado");
                        int aux = Integer.parseInt(lectura.nextLine());
                        boolean validado;
                        if (aux == 1) {
                            validado = true;
                        } else {
                            validado = false;
                        }
                        pun.setValidado(validado);


                        String input = mapper.writeValueAsString(pun);
                        response = webResource.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, input);
                        PuntosValidadorDAO.addPuntosvalidador(pun);
                        System.out.println("Listo! c: ");

                        break;

                    case 3: // Buscar.

                        System.out.println("Digite el email del usuario a buscar: \n");
                        ignorar = lectura.nextLine();
                        String id = lectura.nextLine();

                        PuntosValidador puntosRetorno = PuntosValidadorDAO.getpuntosvalidador(id);
                        System.out.println(puntosRetorno.toString());

                        break;

                    case 4: // Modificar.
                        
                        System.out.println("Ingrese el email");
                        ignorar = lectura.nextLine();
                        String idEmail = lectura.nextLine();
                        pun.setEmail(idEmail);

                        System.out.println("Ingrese los puntos");
                        puntos = lectura.nextLine();
                        pun.setPuntos(puntos);

                        System.out.println("Ingrese 1 si esta validada o 2 si no esta validado");
                        aux = Integer.parseInt(lectura.nextLine());
                       
                        if (aux == 1) {
                            validado = true;
                        } else {
                            validado = false;
                        }
                        pun.setValidado(validado);

                        PuntosValidadorDAO.updatePuntosvalidador(pun, idEmail);
                        System.out.println("Listo! c: ");

                        break;

                    case 5: // Borrar.

                        System.out.println("Digite el email del usuario a eliminar: \n");
                        ignorar = lectura.nextLine();
                        email = lectura.nextLine();

                        PuntosValidadorDAO.deletePuntosvalidador(email);
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


