package com.dekses.jersey.docker.demo.puntosPeriodista;

import com.dekses.jersey.docker.demo.puntosValidador.*;
import com.dekses.jersey.docker.demo.Validador.*;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class PuntosPeriodistaDAO {

      public static PuntosPeriodista getpuntosperiodista(String email) throws UnknownHostException {

        Singleton conexion = Singleton.getInstance();

        DBCollection coll = conexion.getDb().getCollection("fakenewspuntosperiodista");

        Gson gson = new Gson();
        DBObject doc = new BasicDBObject("email", email);

        DBObject obj = coll.findOne(doc);
        PuntosPeriodista pu = gson.fromJson(obj.toString(), PuntosPeriodista.class);
        

        return pu;

    }

    public static void addPuntosperiodista(PuntosPeriodista pu) {

        try {

            Singleton conexion = Singleton.getInstance();

            DBCollection coll = conexion.getDb().getCollection("fakenewspuntosperiodista");
            DBObject doc = new BasicDBObject("puntos", pu.getPuntos())
                    .append("email", pu.getEmail())
                    .append("validado", pu.getValidado());

            coll.insert(doc);
            System.out.println("los puntos " + pu.getPuntos()+ " se han sumado exitosamente.");

        } catch (UnknownHostException e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }

    }

    public static void updatePuntosperiodista(PuntosPeriodista pu, String idEmail) throws UnknownHostException {
           
        Singleton conexion = Singleton.getInstance();

        DBCollection coll = conexion.getDb().getCollection("fakenewspuntosperiodista");
        BasicDBObject document = new BasicDBObject();
        
       document.append("$set", new BasicDBObject().append("puntos", pu.getPuntos()).append("validado", pu.getValidado())); 
        DBObject searchQuery = new BasicDBObject().append("email", pu.getEmail());

        coll.update(searchQuery, document);

        System.out.println("Puntos " + pu.getPuntos()+ " modificado exitosamente.");
        
    }

    public static void deletePuntosperiodista(String email) {
        try {

            Singleton conexion = Singleton.getInstance();

            DBCollection coll = conexion.getDb().getCollection("fakenewspuntosperiodista");
            DBObject document = new BasicDBObject();
            document.put("email", email);

            coll.remove(document);
            System.out.println("los puntos del usuario: " + email + " se han eliminado exitosamente.");

        } catch (UnknownHostException e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }

    }

    public static List getAllPuntosperiodista() throws UnknownHostException {

        List<PuntosPeriodista> ListaPuntos = new ArrayList();

        try {

            Singleton conexion = Singleton.getInstance();

            DBCollection coll = conexion.getDb().getCollection("fakenewspuntosperiodista");
            DBCursor cursor = coll.find();
            try {
                while (cursor.hasNext()) {
                    DBObject object = cursor.next();
                    Gson gson = new Gson();
                    PuntosPeriodista pu = gson.fromJson(object.toString(), PuntosPeriodista.class);
                    ListaPuntos.add(pu);


                }
            } finally {
                cursor.close();
            }

        } catch (UnknownHostException e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }

        return ListaPuntos;

    }

}
