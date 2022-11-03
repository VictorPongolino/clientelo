package br.com.alura.clientelo.reports.relatorios;

import static org.junit.jupiter.api.Assertions.*;

import br.com.alura.clientelo.modal.Pedido;
import br.com.alura.clientelo.reports.PedidoFake;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class RelatorioProdutoesCarosCategoriaTest {

    private RelatorioProdutoesCarosCategoria subject = new RelatorioProdutoesCarosCategoria(PedidoFake.getPedidos());

    @Test
    public void isPrimeiroAutomotiva() {
        Map<String, Pedido> pedidos = subject.getProdutoCaroPorCategoria();

        Pedido primeiroPedido = pedidos.values().stream().findFirst().orElse(null);

        assertNotNull(primeiroPedido);
        assertEquals("AUTOMOTIVA", primeiroPedido.getCategoria());
        assertEquals("Jogo de pneus", primeiroPedido.getProdutos());
        assertEquals(new BigDecimal("1276.79"), primeiroPedido.getPreco());
    }

    @Test
    public void testarComUnicoPedido() {
        List<Pedido> pedidosFake = new ArrayList<>();
        pedidosFake.add(new Pedido("CATEGORIA", "PRODUTO", "CLIENTE", new BigDecimal("1"), 1, LocalDate.of(2022, 1, 1)));
        assertEquals(1, subject.getProdutoCaroPorCategoria().size());
    }

    @Test
    public void testarSemPedidos() {
        List<Pedido> pedidosFake = new ArrayList<>();
        assertTrue(subject.getProdutoCaroPorCategoria().isEmpty());
    }
}