package br.com.alura.clientelo.dao;

import java.util.List;
import java.util.Optional;

import br.com.alura.clientelo.modal.Categoria;
import br.com.alura.clientelo.persistence.PersistenceFactory;

public class CategoriaDAO {

    private PersistenceFactory persistenceFactory;

    public CategoriaDAO(PersistenceFactory persistenceFactory) {
        this.persistenceFactory = persistenceFactory;
    }

    public Optional<Categoria> buscarPorId() {}
    public Categoria cadastrar(Categoria categoria) {}
    public List<Categoria> listaTodas() {}
}
