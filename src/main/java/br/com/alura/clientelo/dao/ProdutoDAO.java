package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.modal.Produto;
import br.com.alura.clientelo.persistence.DBProperties;
import br.com.alura.clientelo.persistence.PersistenceFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class ProdutoDAO {
    private EntityManager persistenceFactory;

    public ProdutoDAO(PersistenceFactory persistenceFactory) {
        this.persistenceFactory = persistenceFactory.getInstance(DBProperties.CLIENT_ELO);
    }

    public Optional<Produto> buscarPorId(Long produtoId) {
        return Optional.ofNullable(persistenceFactory.find(Produto.class, produtoId));
    }
    public void cadastrar(Produto produto) {
        persistenceFactory.persist(produto);
    }
    public List<Produto> listaTodas() {
        TypedQuery<Produto> query = persistenceFactory.createQuery("SELECT c FROM " + Produto.class.getName() + " c", Produto.class);
        return query.getResultList();
    }

    public List<Produto> listaIndisponiveis() {
        TypedQuery<Produto> query = persistenceFactory.createQuery("SELECT c FROM " + Produto.class.getName() + " c WHERE c.qtdEstoque = 0", Produto.class);
        return query.getResultList();
    }
}
