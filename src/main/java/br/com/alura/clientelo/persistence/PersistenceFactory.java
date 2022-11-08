package br.com.alura.clientelo.persistence;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PersistenceFactory {

    private static final Map<String, EntityManager> connection = new ConcurrentHashMap<>();

    protected static EntityManager getInstance(String database) {
        connection.putIfAbsent(database, Persistence.createEntityManagerFactory(database).createEntityManager());
        return connection.get(database);
    }

    public static EntityManager getInstance(DBProperties dbProperties) {
        return getInstance(dbProperties.getDatabase());
    }
}
