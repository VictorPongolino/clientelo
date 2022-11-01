package br.com.alura.clientelo.reports;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

class RelatorioProdutoesCarosCategoriaTest {

    private OrdenacaoPedidoImpl ordenacaoPedido = new OrdenacaoPedidoImpl();

    @Test
    public void isPrimeiroAutomotiva() {
        Map<String, Pedido> pedidos = ordenacaoPedido.ordenarPorCategoriaMaiorPreco(PedidoFake.getPedidos());

        Pedido primeiroPedido = pedidos.values().stream().findFirst().orElse(null);

        assertNotNull(primeiroPedido);
        assertEquals("AUTOMOTIVA", primeiroPedido.getCategoria());
        assertEquals("Jogo de pneus", primeiroPedido.getProduto());
        assertEquals(new BigDecimal("1276.79"), primeiroPedido.getPreco());
    }

    @Test
    public void testarComUnicoPedido() {
        List<Pedido> pedidosFake = new ArrayList<>();
        pedidosFake.add(new Pedido("CATEGORIA", "PRODUTO", "CLIENTE", new BigDecimal("1"), 1, LocalDate.of(2022, 1, 1)));
        assertEquals(1, ordenacaoPedido.ordenarPorCategoriaMaiorPreco(pedidosFake).size());
    }

    @Test
    public void testarSemPedidos() {
        List<Pedido> pedidosFake = new ArrayList<>();
        assertTrue(ordenacaoPedido.ordenarPorCategoriaMaiorPreco(pedidosFake).isEmpty());
    }
}