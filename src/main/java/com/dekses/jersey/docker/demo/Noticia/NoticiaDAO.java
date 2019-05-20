package com.dekses.jersey.docker.demo.Noticia;

import com.dekses.jersey.docker.demo.*;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class NoticiaDAO {

      public static Noticia getNoticia(String titulo) throws UnknownHostException {

        Singleton conexion = Singleton.getInstance();

        DBCollection coll = conexion.getDb().getCollection("fakenewsnoticia");

        Gson gson = new Gson();
        DBObject doc = new BasicDBObject("titulo", titulo);

        DBObject obj = coll.findOne(doc);
        Noticia n = gson.fromJson(obj.toString(), Noticia.class);
        

        return n;

    }

    public static void addNoticia(Noticia n) {

        try {

            Singleton conexion = Singleton.getInstance();

            DBCollection coll = conexion.getDb().getCollection("fakenewsnoticia");
            DBObject doc = new BasicDBObject("titulo", n.getTitulo())
                    .append("autor", n.getAutor())
                    .append("fuente", n.getFuente())
                    .append("fecha", n.getFecha())
                    .append("validado", n.getValidado());

            coll.insert(doc);
            System.out.println("Noticia " + n.getTitulo()+ " agregado exitosamente.");

        } catch (UnknownHostException e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }

    }

//    public static void updatePeriodista(Noticia n, String idTitulo) throws UnknownHostException {
//
//        Singleton conexion = Singleton.getInstance();
//
//        DBCollection coll = conexion.getDb().getCollection("fakenewsnoticia");
//        DBObject document = new BasicDBObject();
//
//        document.put("titulo", idTitulo);
//
//        DBObject searchQuery = new BasicDBObject().append("email", p.getEmail())
//                .append("contrasena", p.getContrasena());
//
//        coll.update(searchQuery, document);
//
//        System.out.println("Periodista " + p.getNombre()+ " modificado exitosamente.");
//
//    }

    public static void deleteNoticia(String titulo) {
        try {

            Singleton conexion = Singleton.getInstance();

            DBCollection coll = conexion.getDb().getCollection("fakenewsnoticia");
            DBObject document = new BasicDBObject();
            document.put("titulo", titulo);

            coll.remove(document);
            System.out.println("Noticia con titulo: " + titulo + " eliminado exitosamente.");

        } catch (UnknownHostException e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }

    }

    public static List getAllNoticia() throws UnknownHostException {

        List<Noticia> Listanoticias = new ArrayList();

        try {

            Singleton conexion = Singleton.getInstance();

            DBCollection coll = conexion.getDb().getCollection("fakenewsnoticia");
            DBCursor cursor = coll.find();
            try {
                while (cursor.hasNext()) {
                    DBObject object = cursor.next();
                    Gson gson = new Gson();
                    Noticia n = gson.fromJson(object.toString(), Noticia.class);
                    Listanoticias.add(n);


                }
            } finally {
                cursor.close();
            }

        } catch (UnknownHostException e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }

        return Listanoticias;

    }

}
