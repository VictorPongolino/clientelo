package br.com.alura.clientelo.reports;

import java.util.List;
import java.util.Map;

import br.com.alura.clientelo.Pedido;
import br.com.alura.clientelo.reports.logic.OrdenacaoPedido;

public class RelatorioProdutoesCarosCategoria extends OperacaoPedido<Pedido>{

	public RelatorioProdutoesCarosCategoria(List<Pedido> pedidos) {
		super(pedidos);
	}

	@Override
	public void accept(OrdenacaoPedido<Pedido> ordenacaoPedido) {
		printTitle("Relatório de produtos mais caros de cada categoria");
		Map<String, Pedido> ordenarPorCategoriaMaiorPreco = ordenacaoPedido.ordenarPorCategoriaMaiorPreco(getPedidos());
		for (Map.Entry<String, Pedido> pedidoEntrySet : ordenarPorCategoriaMaiorPreco.entrySet()) {
			Pedido pedido = pedidoEntrySet.getValue();
			System.out.printf("""
				CATEGORIA: %s
				PRODUTO: %s
				PREÇO: %s\n
			""", pedidoEntrySet.getKey(), pedido.getProduto(), toDinheiro(pedido.getPreco()));
		}		
	}
}
