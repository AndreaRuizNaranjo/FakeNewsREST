package com.dekses.jersey.docker.demo;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class PeriodistaDAO {

    

    public static void addPeriodista(Periodista p) {

        try {

            Singleton conexion = Singleton.getInstance();

            DBCollection coll = conexion.getDb().getCollection("fakenewsperiodista");
            DBObject doc = new BasicDBObject("name", p.getNombre())
                    .append("email", p.getEmail())
                    .append("contrasena", p.getContrasena());

            coll.insert(doc);
            System.out.println("Periodista " + p.getNombre()+ " agregado exitosamente.");

        } catch (UnknownHostException e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }

    }

    public static void updatePeriodista(Periodista p) throws UnknownHostException {

        Singleton conexion = Singleton.getInstance();

        DBCollection coll = conexion.getDb().getCollection("fakenewsperiodista");
        DBObject document = new BasicDBObject();
        document.put("nombre", p.getNombre());

        DBObject searchQuery = new BasicDBObject().append("name", p.getNombre())
                .append("email", p.getEmail())
                .append("contrasena", p.getContrasena());
        coll.update(searchQuery, document);

        System.out.println("Periodista " + p.getNombre()+ " modificado exitosamente.");
        
    }

    public static void deletePeriodista(String email) {
        try {

            Singleton conexion = Singleton.getInstance();

            DBCollection coll = conexion.getDb().getCollection("fakenewsperiodista");
            DBObject document = new BasicDBObject();
            document.put("email", email);

            coll.remove(document);
            System.out.println("Periodista con email: " + email + " eliminado exitosamente.");

        } catch (UnknownHostException e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }

    }

    public static List getAllPeriodistas() throws UnknownHostException {

        List<Periodista> Periodistas = new ArrayList();

        try {

            Singleton conexion = Singleton.getInstance();

            DBCollection coll = conexion.getDb().getCollection("fakenewsperiodista");
            DBCursor cursor = coll.find();
            try {
                while (cursor.hasNext()) {
                    DBObject object = cursor.next();
                    Gson gson = new Gson();
                    Periodista p = gson.fromJson(object.toString(), Periodista.class);
                    
                    System.out.println("Se encontraron todos los periodistas." + Periodistas);

                }
            } finally {
                cursor.close();
            }

        } catch (UnknownHostException e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }

        return Periodistas;

    }

}
