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

      public static Periodista getPeriodista(String nombre) throws UnknownHostException {

        Singleton conexion = Singleton.getInstance();

        DBCollection coll = conexion.getDb().getCollection("fakenewsperiodista");

        Gson gson = new Gson();
        DBObject doc = new BasicDBObject("name", nombre);

        DBObject obj = coll.findOne(doc);
        Periodista p = gson.fromJson(obj.toString(), Periodista.class);
        System.out.println("encontre el periodista " + p);

        return p;

    }

    public static void addPeriodista(Periodista p) {

        try {

            Singleton conexion = Singleton.getInstance();

            DBCollection coll = conexion.getDb().getCollection("fakenewsperiodista");
            DBObject doc = new BasicDBObject("nombre", p.getNombre())
                    .append("email", p.getEmail())
                    .append("contrasena", p.getContrasena());

            coll.insert(doc);
            System.out.println("Periodista " + p.getNombre()+ " agregado exitosamente.");

        } catch (UnknownHostException e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }

    }

    public static void updatePeriodista(Periodista p, String idNombre) throws UnknownHostException {

        Singleton conexion = Singleton.getInstance();

        DBCollection coll = conexion.getDb().getCollection("fakenewsperiodista");
        DBObject document = new BasicDBObject();

        document.put("nombre", idNombre);

        DBObject searchQuery = new BasicDBObject().append("email", p.getEmail())
                .append("contrasena", p.getContrasena());

        coll.update(searchQuery, document);

        System.out.println("Periodista " + p.getNombre()+ " modificado exitosamente.");

    }

    public static void deletePeriodista(String nombre) {
        try {

            Singleton conexion = Singleton.getInstance();

            DBCollection coll = conexion.getDb().getCollection("fakenewsperiodista");
            DBObject document = new BasicDBObject();
            document.put("nombre", nombre);

            coll.remove(document);
            System.out.println("Periodista con nombre: " + nombre + " eliminado exitosamente.");

        } catch (UnknownHostException e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }

    }

    public static List getAllPeriodista() throws UnknownHostException {

        List<Periodista> Listaperiodistas = new ArrayList();

        try {

            Singleton conexion = Singleton.getInstance();

            DBCollection coll = conexion.getDb().getCollection("fakenewsperiodista");
            DBCursor cursor = coll.find();
            try {
                while (cursor.hasNext()) {
                    DBObject object = cursor.next();
                    Gson gson = new Gson();
                    Periodista p = gson.fromJson(object.toString(), Periodista.class);
                    Listaperiodistas.add(p);


                }
            } finally {
                cursor.close();
            }

        } catch (UnknownHostException e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }

        return Listaperiodistas;

    }

}
