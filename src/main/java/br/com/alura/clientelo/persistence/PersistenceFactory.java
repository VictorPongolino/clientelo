package br.com.alura.clientelo.persistence;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class PersistenceFactory {

    protected static EntityManager getInstance(String database) {
        return Persistence.createEntityManagerFactory(database).createEntityManager();
    }

    public static EntityManager getInstance(DBProperties dbProperties) {
        return getInstance(dbProperties.getDatabase());
    }
}
