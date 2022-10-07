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
		List<Pedido> ordenarPorCategoria = ordenacaoPedido.ordenarPorCategoria(getPedidos());
		for (Pedido pedido : ordenarPorCategoria) {
			System.out.printf("""
					Categoria: %s
					Nome: %s
					Montante: %s\n
					""", pedido.getCategoria(), pedido.getProduto(), toDinheiro(pedido.getPreco()));
		}
	}

}
