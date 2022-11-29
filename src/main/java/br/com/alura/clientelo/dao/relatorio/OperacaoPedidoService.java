package br.com.alura.clientelo.dao.relatorio;

import br.com.alura.clientelo.modal.ItemPedido;
import br.com.alura.clientelo.modal.Pedido;
import br.com.alura.clientelo.modal.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperacaoPedidoService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<RelatorioVendasCategoriaDTO> getVendasPorCategoria() {
        String jpql = "SELECT NEW " + RelatorioVendasCategoriaDTO.class.getName() + "(c.nome, SUM(i.quantidade), SUM(i.precoUnitario * i.quantidade)) " +
                      "FROM " + Pedido.class.getName() + " p " +
                      "JOIN p.categoria c " +
                      "JOIN p.itempedidos i " +
                      "GROUP BY c.nome " +
                      "ORDER BY c.nome ASC";
        TypedQuery<RelatorioVendasCategoriaDTO> query = entityManager.createQuery(jpql, RelatorioVendasCategoriaDTO.class);
        return query.getResultList();
    }

    public List<RelatorioClienteFielDTO> getClientesLucrativos() {
        String jpql = "SELECT NEW " + RelatorioClienteFielDTO.class.getName() + "(p.cliente.nome, COUNT(*), SUM(i.precoUnitario * i.quantidade)) " +
                "FROM " + Pedido.class.getName() + " p " +
                "JOIN p.itempedidos i " +
                "GROUP BY p.cliente.nome";
        TypedQuery<RelatorioClienteFielDTO> query = entityManager.createQuery(jpql, RelatorioClienteFielDTO.class);
        query.setMaxResults(3);
        return query.getResultList();
    }

    public List<Produto> getProdutosTOP3() {
        String jpql = "SELECT i.produto FROM " + ItemPedido.class.getName() + " i GROUP BY i.produto HAVING COUNT(i.produto) > 3";
        TypedQuery<Produto> query = entityManager.createQuery(jpql, Produto.class);
        return query.getResultList();
    }

    public List<ClienteLucrativoDTO> getClienteFiel() {
        String jpql = "SELECT NEW " + ClienteLucrativoDTO.class.getName() + "(p.cliente.nome, COUNT(*), SUM(i.precoUnitario * i.quantidade)) " +
                "FROM " + Pedido.class.getName() + " p " +
                "JOIN p.itempedidos i " +
                "GROUP BY p.cliente.nome " +
                "ORDER BY SUM(i.precoUnitario * i.quantidade) DESC";
        TypedQuery<ClienteLucrativoDTO> query = entityManager.createQuery(jpql, ClienteLucrativoDTO.class);
        query.setMaxResults(2);
        return query.getResultList();
    }

}
