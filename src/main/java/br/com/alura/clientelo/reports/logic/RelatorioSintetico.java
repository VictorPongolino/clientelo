package br.com.alura.clientelo.reports.logic;

import br.com.alura.clientelo.modal.Pedido;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RelatorioSintetico {


    public int getTotalPedidosRealizados(List<Pedido> pedidos) {
        return pedidos.size();
    }

    public int getTotalProdutosVendidos(List<Pedido> pedidos) {
        return pedidos.stream().mapToInt(Pedido::getQuantidade).reduce(0, Integer::sum);
    }

    public long getTotalCategorias(List<Pedido> pedidos) {
        return pedidos.stream().map(Pedido::getCategoria).distinct().count();
    }

    public BigDecimal getMontanteVendas(List<Pedido> pedidos) {
        return pedidos.stream().map(Pedido::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Optional<Pedido> getPedidoMaisBarato(List<Pedido> pedidos) {
        return pedidos.stream().min(Comparator.comparing(Pedido::getValorTotal));
    }

    public Optional<Pedido> getPedidoMaisCaro(List<Pedido> pedidos) {
        return pedidos.stream().max(Comparator.comparing(Pedido::getValorTotal));
    }
}
