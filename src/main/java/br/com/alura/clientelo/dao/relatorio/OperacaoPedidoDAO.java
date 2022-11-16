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

    public OperacaoPedidoDAO() {
        this.persistenceFactory = PersistenceFactory.getInstance(DBProperties.CLIENT_ELO);
    }

    public List<RelatorioVendasCategoriaDTO> getVendasPorCategoria() {
        String jpql = "SELECT NEW " + RelatorioVendasCategoriaDTO.class.getName() + "(c.nome, SUM(i.quantidade), SUM(i.precoUnitario * i.quantidade)) " +
                      "FROM " + Pedido.class.getName() + " p " +
                      "JOIN p.categoria c " +
                      "JOIN p.itempedidos i " +
                      "GROUP BY c.nome " +
                      "ORDER BY c.nome ASC";
        TypedQuery<RelatorioVendasCategoriaDTO> query = persistenceFactory.createQuery(jpql, RelatorioVendasCategoriaDTO.class);
        return query.getResultList();
    }

    public List<RelatorioClienteFielDTO> getClienteFiel() {
        String jpql = "SELECT NEW " + RelatorioClienteFielDTO.class.getName() + "(c.nome, COUNT(*), SUM(i.precoUnitario * i.quantidade)) " +
                "FROM " + Pedido.class.getName() + " p " +
                "JOIN p.cliente c " +
                "JOIN p.itempedidos i " +
                "GROUP BY c.nome " +
                "ORDER BY SUM(i.precoUnitario * i.quantidade) DESC, c.nome ASC";
        TypedQuery<RelatorioClienteFielDTO> query = persistenceFactory.createQuery(jpql, RelatorioClienteFielDTO.class);
        query.setMaxResults(2);
        return query.getResultList();
    }

    public List<Produto> getProdutosTOP3() {
        String jpql = "SELECT i.produto FROM " + ItemPedido.class.getName() + " i GROUP BY i.produto ORDER BY i.produto DESC";
        TypedQuery<Produto> query = persistenceFactory.createQuery(jpql, Produto.class);
        query.setMaxResults(3);
        return query.getResultList();
    }

}
