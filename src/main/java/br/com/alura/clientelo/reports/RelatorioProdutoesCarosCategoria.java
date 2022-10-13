package br.com.alura.clientelo.reports;

import java.util.List;

import br.com.alura.clientelo.Pedido;
import br.com.alura.clientelo.reports.logic.OrdenacaoPedido;

public class RelatorioProdutoesCarosCategoria extends OperacaoPedido<Pedido>{

	public RelatorioProdutoesCarosCategoria(List<Pedido> pedidos) {
		super(pedidos);
	}

	@Override
	public void accept(OrdenacaoPedido<Pedido> ordenacaoPedido) {
		printTitle("Relatório de produtos mais caros de cada categoria");
		ordenacaoPedido.ordenarPorCategoriaMaiorPreco(getPedidos()).forEach((categoria, pedido) -> {
				System.out.printf("CATEGORIA: %s\nPRODUTO: %s\nPREÇO: %s\n\n", categoria, pedido.getProduto(), toDinheiro(pedido.getPreco()));
			});
	}
}
