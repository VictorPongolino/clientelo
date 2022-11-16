package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.modal.Cliente;
import br.com.alura.clientelo.persistence.DBProperties;
import br.com.alura.clientelo.persistence.PersistenceFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class ClienteDAO {

    private EntityManager persistenceFactory;

    public ClienteDAO() {
        this.persistenceFactory = PersistenceFactory.getInstance(DBProperties.CLIENT_ELO);
    }

    public Optional<Cliente> buscarPorId(Long clienteId) {
        return Optional.ofNullable(persistenceFactory.find(Cliente.class, clienteId));
    }
    public void cadastrar(Cliente cliente) {
        persistenceFactory.persist(cliente);
    }

    public Cliente atualizar(Cliente cliente) {
        return persistenceFactory.merge(cliente);
    }

    public void remover(Cliente cliente) {
        persistenceFactory.remove(cliente);
    }
    public List<Cliente> listaTodas() {
        TypedQuery<Cliente> query = persistenceFactory.createQuery("SELECT c FROM " + Cliente.class.getName() + " c", Cliente.class);
        return query.getResultList();
    }

    public List<Cliente> listaPorNome(String nome) {
        TypedQuery<Cliente> query = persistenceFactory.createQuery("SELECT c FROM " + Cliente.class.getName() + " c WHERE c.nome = :nome", Cliente.class);
        query.setParameter("nome", nome);
        return query.getResultList();
    }

    public List<Cliente> listaIndisponiveis() {
        TypedQuery<Cliente> query = persistenceFactory.createQuery("SELECT c FROM " + Cliente.class.getName() + " c WHERE c.qtdEstoque = 0", Cliente.class);
        return query.getResultList();
    }
}
