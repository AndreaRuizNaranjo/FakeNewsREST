package com.dekses.jersey.docker.demo.Validador;

import com.dekses.jersey.docker.demo.*;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;

public class Singleton {

    private static Singleton INSTANCE = null;
    private final static String HOST = "localhost";
    private final static int PORT = 27017;

    private final MongoClient mongoClient;
    private DB db;

    private Singleton() throws UnknownHostException {
        this.mongoClient = new MongoClient(HOST, PORT);
        this.db = mongoClient.getDB("fakenewsvalidador");
    }

    public static Singleton getInstance() throws UnknownHostException {
        if (INSTANCE == null) {
            INSTANCE = new Singleton();
        }

        return INSTANCE;
    }

    public DB getDb() {
        return db;
    }

    public void setDb(DB db) {
        this.db = db;
    }

}
