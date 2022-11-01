package br.com.alura.clientelo.reports;

import br.com.alura.clientelo.Pedido;
import br.com.alura.clientelo.ProcessadorDeCsv;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RelatorioProdutosVendidosTest {

    private OrdenacaoPedidoImpl ordenacaoPedidoImpl = new OrdenacaoPedidoImpl();

    @Test
    public void retornarCategoria() {
        List<Pedido> pedidos = ordenacaoPedidoImpl.ordenarQuantidade(obterRelatorios());
        Pedido p1 = pedidos.get(0);
        Pedido p2 = pedidos.get(1);
        Pedido p3 = pedidos.get(2);

        assertTrue(p1.getProduto().equals("iPhone 13 Pro") && p1.getQuantidade() == 6 && p1.getCategoria().equals("CELULARES"));
        assertTrue(p2.getProduto().equals("Galaxy S22 Ultra") && p2.getQuantidade() == 5 && p2.getCategoria().equals("CELULARES"));
        assertTrue(p3.getProduto().equals("Galaxy Tab S8") && p3.getQuantidade() == 4 && p3.getCategoria().equals("INFORMÁTICA"));
    }

    @Test
    public void retornarCategoriaVazia() {
        List<Pedido> pedidos = ordenacaoPedidoImpl.ordenarQuantidade(Collections.emptyList());
        assertTrue(pedidos.isEmpty());
    }

    private List<Pedido> obterRelatorios() {
        return Arrays.asList(ProcessadorDeCsv.processaArquivo("pedidos.csv"));
    }
}