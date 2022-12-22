package br.com.alura.clientelo.dao.relatorio;

import br.com.alura.clientelo.modal.pedido.ItemPedido;
import br.com.alura.clientelo.modal.Produto;

import br.com.alura.clientelo.modal.pedido.Pedido;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class OperacaoPedidoService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<RelatorioVendasCategoriaVO> getVendasPorCategoria() {
        String jpql = "SELECT NEW " + RelatorioVendasCategoriaVO.class.getName() + "(c.nome, SUM(i.quantidade), SUM(i.precoUnitario * i.quantidade)) " +
                      "FROM " + Pedido.class.getName() + " p " +
                      "JOIN p.itempedidos i " +
                      "JOIN i.produto.categoria c " +
                      "GROUP BY c.nome " +
                      "ORDER BY c.nome ASC";
        TypedQuery<RelatorioVendasCategoriaVO> query = entityManager.createQuery(jpql, RelatorioVendasCategoriaVO.class);
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
