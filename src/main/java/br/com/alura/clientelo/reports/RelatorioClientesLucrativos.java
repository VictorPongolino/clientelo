package br.com.alura.clientelo.reports;

import br.com.alura.clientelo.Pedido;
import br.com.alura.clientelo.reports.logic.OrdenacaoPedido;
import br.com.alura.clientelo.reports.logic.TopClienteGastos;

import java.util.List;
import java.util.Map;

public class RelatorioClientesLucrativos extends OperacaoPedido<Pedido> {
    public RelatorioClientesLucrativos(List<Pedido> pedidos) {
        super(pedidos);
    }

    @Override
    public void accept(OrdenacaoPedido<Pedido> ordenacaoPedido) {
        printTitle("Relatório de clientes mais lucrativos");
        for (Map.Entry<String, TopClienteGastos> pedidos : ordenacaoPedido.ordenarPorClienteLucrativo(getPedidos()).entrySet()) {
            System.out.printf("""
                   NOME: %s
                   Nº DE PEDIDOS: %d
                   MONTANTE GASTO: %s\n
                    """, pedidos.getKey(), pedidos.getValue().getQuantidade(), pedidos.getValue().getTotalGasto());
        }
    }
}
