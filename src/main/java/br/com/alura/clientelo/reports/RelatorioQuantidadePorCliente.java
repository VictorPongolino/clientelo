package br.com.alura.clientelo.reports;

import br.com.alura.clientelo.Pedido;
import br.com.alura.clientelo.reports.logic.OrdenacaoPedido;

import java.util.List;
import java.util.Map;

public class RelatorioQuantidadePorCliente extends OperacaoPedido<Pedido> {
    public RelatorioQuantidadePorCliente(List<Pedido> pedidos) {
        super(pedidos);
    }

    @Override
    public void accept(OrdenacaoPedido<Pedido> ordenacaoPedido) {
        printTitle("Relatório de clientes fiéis");
        Map<String, Integer> quantidadePedidoCliete = ordenacaoPedido.ordenarPorClientes(getPedidos());
        for (Map.Entry<String, Integer> relatorioQuantidadePedidoCliente : quantidadePedidoCliete.entrySet()) {
            System.out.printf("""
                    Nº DE PEDIDOS: %s
                    NOME: %s\n
                    """, relatorioQuantidadePedidoCliente.getValue(), relatorioQuantidadePedidoCliente.getKey());
        }
    }
}
