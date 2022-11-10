package br.com.alura.clientelo.dao;

import java.util.List;
import java.util.Optional;

import br.com.alura.clientelo.modal.Categoria;
import br.com.alura.clientelo.persistence.DBProperties;
import br.com.alura.clientelo.persistence.PersistenceFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class CategoriaDAO {

    private EntityManager persistenceFactory;

    public CategoriaDAO(PersistenceFactory persistenceFactory) {
        this.persistenceFactory = persistenceFactory.getInstance(DBProperties.CLIENT_ELO);
    }

    public Optional<Categoria> buscarPorId(Long categoriaId) {
        return Optional.ofNullable(persistenceFactory.find(Categoria.class, categoriaId));
    }
    public void cadastrar(Categoria categoria) {
        persistenceFactory.persist(categoria);
    }
    public List<Categoria> listaTodas() {
        TypedQuery<Categoria> query = persistenceFactory.createQuery("SELECT c FROM " + Categoria.class.getName() + " c", Categoria.class);
        return query.getResultList();
    }

    public Optional<Categoria> listarPorNome(String nome) {
        // TODO
        return null;
    }
}
