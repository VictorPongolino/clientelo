package br.com.alura.clientelo.reports;

import java.util.List;

import br.com.alura.clientelo.Pedido;
import br.com.alura.clientelo.reports.logic.OrdenacaoPedido;

public class RelatorioVendasCategoria extends OperacaoPedido<Pedido> {
	
	public RelatorioVendasCategoria(List<Pedido> pedidos) {
		super(pedidos);
	}

	@Override
	public void accept(OrdenacaoPedido<Pedido> ordenacaoPedido) {
		printTitle("Relatório de vendas por categoria");
		ordenacaoPedido.ordenarPorCategoria(getPedidos()).forEach((categoria, pedido) -> {
			System.out.printf("Categoria: %s\nQuantidade: %s\nMontante: %s\n\n", categoria, pedido.getQuantidade(), toDinheiro(pedido.getMontante()));
		});
	}

}
