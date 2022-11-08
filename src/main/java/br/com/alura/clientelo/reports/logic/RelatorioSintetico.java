package br.com.alura.clientelo.reports.logic;

import br.com.alura.clientelo.modal.PedidoCSVDTO;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RelatorioSintetico {


    public int getTotalPedidosRealizados(List<PedidoCSVDTO> pedidos) {
        return pedidos.size();
    }

    public int getTotalProdutosVendidos(List<PedidoCSVDTO> pedidos) {
        return pedidos.stream().mapToInt(PedidoCSVDTO::getQuantidade).reduce(0, Integer::sum);
    }

    public long getTotalCategorias(List<PedidoCSVDTO> pedidos) {
        return pedidos.stream().map(PedidoCSVDTO::getCategoria).distinct().count();
    }

    public BigDecimal getMontanteVendas(List<PedidoCSVDTO> pedidos) {
        return pedidos.stream().map(PedidoCSVDTO::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Optional<PedidoCSVDTO> getPedidoMaisBarato(List<PedidoCSVDTO> pedidos) {
        return pedidos.stream().min(Comparator.comparing(PedidoCSVDTO::getValorTotal));
    }

    public Optional<PedidoCSVDTO> getPedidoMaisCaro(List<PedidoCSVDTO> pedidos) {
        return pedidos.stream().max(Comparator.comparing(PedidoCSVDTO::getValorTotal));
    }
}
