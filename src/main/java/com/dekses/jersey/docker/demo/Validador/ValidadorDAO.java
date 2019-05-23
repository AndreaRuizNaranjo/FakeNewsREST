package com.dekses.jersey.docker.demo.Validador;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class ValidadorDAO {

      public static Validador getValidador(String email) throws UnknownHostException {

        Singleton conexion = Singleton.getInstance();

        DBCollection coll = conexion.getDb().getCollection("fakenewsvalidador");

        Gson gson = new Gson();
        DBObject doc = new BasicDBObject("email", email);

        DBObject obj = coll.findOne(doc);
        Validador v = gson.fromJson(obj.toString(), Validador.class);
        

        return v;

    }

    public static void addValidador(Validador v) {

        try {

            Singleton conexion = Singleton.getInstance();

            DBCollection coll = conexion.getDb().getCollection("fakenewsvalidador");
            DBObject doc = new BasicDBObject("nombre", v.getNombre())
                    .append("email", v.getEmail())
                    .append("contrasena", v.getContrasena());

            coll.insert(doc);
            System.out.println("Validador " + v.getNombre()+ " agregado exitosamente.");

        } catch (UnknownHostException e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }

    }

    public static void updateValidador(Validador v, String idEmail) throws UnknownHostException {
           
        Singleton conexion = Singleton.getInstance();

        DBCollection coll = conexion.getDb().getCollection("fakenewsvalidador");
        BasicDBObject document = new BasicDBObject();
        
       document.append("$set", new BasicDBObject().append("contrasena", v.getContrasena()).append("nombre", v.getNombre())); 
        DBObject searchQuery = new BasicDBObject().append("email", v.getEmail());

        coll.update(searchQuery, document);

        System.out.println("Periodista " + v.getNombre()+ " modificado exitosamente.");
        
    }

    public static void deleteValidador(String email) {
        try {

            Singleton conexion = Singleton.getInstance();

            DBCollection coll = conexion.getDb().getCollection("fakenewsvalidador");
            DBObject document = new BasicDBObject();
            document.put("email", email);

            coll.remove(document);
            System.out.println("Validador con email: " + email + " eliminado exitosamente.");

        } catch (UnknownHostException e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }

    }

    public static List getAllValidador() throws UnknownHostException {

        List<Validador> Listavalidadores = new ArrayList();

        try {

            Singleton conexion = Singleton.getInstance();

            DBCollection coll = conexion.getDb().getCollection("fakenewsvalidador");
            DBCursor cursor = coll.find();
            try {
                while (cursor.hasNext()) {
                    DBObject object = cursor.next();
                    Gson gson = new Gson();
                    Validador v = gson.fromJson(object.toString(), Validador.class);
                    Listavalidadores.add(v);


                }
            } finally {
                cursor.close();
            }

        } catch (UnknownHostException e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }

        return Listavalidadores;

    }

}
