package br.com.alura.clientelo.persistence;

public enum DBProperties {

    CLIENT_ELO("clientelo");

    private String database;

    DBProperties(String database) {
        this.database = database;
    }

    public String getDatabase() {
        return this.database;
    }
}
