package br.com.alura.clientelo.dao.relatorio;

import br.com.alura.clientelo.modal.ItemPedido;
import br.com.alura.clientelo.modal.Pedido;
import br.com.alura.clientelo.modal.Produto;
import br.com.alura.clientelo.persistence.DBProperties;
import br.com.alura.clientelo.persistence.PersistenceFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class OperacaoPedidoDAO {
    private EntityManager persistenceFactory;

    public OperacaoPedidoDAO(PersistenceFactory persistenceFactory) {
        this.persistenceFactory = persistenceFactory.getInstance(DBProperties.CLIENT_ELO);
    }

    public List<RelatorioVendasCategoriaDTO> getVendasPorCategoria() {
        TypedQuery<RelatorioVendasCategoriaDTO> query = persistenceFactory.createQuery("SELECT NEW " + RelatorioVendasCategoriaDTO.class.getName() + "(p.categoria.nome, SUM(*), SUM(p.itempedidos.precoUnitario)) FROM " + Pedido.class.getName() + " p GROUP BY p.categoria.nome", RelatorioVendasCategoriaDTO.class);
        return query.getResultList();
    }

    public List<RelatorioClienteFielDTO> getClienteFiel() {
        TypedQuery<RelatorioClienteFielDTO> query = persistenceFactory.createQuery("SELECT NEW " + RelatorioClienteFielDTO.class.getName() + "(p.cliente.nome, SUM(*), SUM(p.itempedidos.precoUnitario) as total) FROM " + Pedido.class.getName() + " p GROUP BY p.cliente.nome ORDER BY total DESC", RelatorioClienteFielDTO.class);
        query.setMaxResults(3);
        return query.getResultList();
    }

    public List<Produto> getProdutosTOP3() {
        TypedQuery<Produto> query = persistenceFactory.createQuery("SELECT i.produto FROM " + ItemPedido.class.getName() + " i GROUP BY i.produto", Produto.class);
        query.setMaxResults(3);
        return query.getResultList();
    }

}
