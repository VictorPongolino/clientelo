package br.com.alura.clientelo.reports.logic;

import br.com.alura.clientelo.Pedido;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface RelatorioSinteticoPedido {
    int getTotalPedidosRealizados(List<Pedido> pedido);
    int getTotalProdutosVendidos(List<Pedido> pedidos);
    long getTotalCategorias(List<Pedido> pedidos);
    BigDecimal getMontanteVendas(List<Pedido> pedidos);
    Optional<Pedido> getPedidoMaisBarato(List<Pedido> pedidos);
    Optional<Pedido> getPedidoMaisCaro(List<Pedido> pedidos);
}
