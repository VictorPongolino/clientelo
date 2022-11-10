package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.modal.Pedido;
import br.com.alura.clientelo.persistence.DBProperties;
import br.com.alura.clientelo.persistence.PersistenceFactory;

import javax.persistence.EntityManager;
import java.util.Optional;

public class PedidoDAO {
    private EntityManager persistenceFactory;

    public PedidoDAO(PersistenceFactory persistenceFactory) {
        this.persistenceFactory = persistenceFactory.getInstance(DBProperties.CLIENT_ELO);
    }

    public void cadastrar(Pedido pedido){
        // TODO SEMANA 6
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return Optional.ofNullable(persistenceFactory.find(Pedido.class, id));
    }
}
