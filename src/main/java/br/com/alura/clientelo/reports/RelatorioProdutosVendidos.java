package br.com.alura.clientelo.reports;

import java.util.List;

import br.com.alura.clientelo.Pedido;
import br.com.alura.clientelo.reports.logic.OrdenacaoPedido;

public class RelatorioProdutosVendidos extends OperacaoPedido<Pedido> {
	
	public RelatorioProdutosVendidos(List<Pedido> pedidos) {
		super(pedidos);
	}

	@Override
	public void accept(OrdenacaoPedido<Pedido> ordenacaoPedido) {
		printTitle("Relatório de produtos mais vendidos");
		List<Pedido> ordenarPorCategoria = ordenacaoPedido.ordenarQuantidade(getPedidos());
		for (Pedido pedido : ordenarPorCategoria) {
			System.out.printf("""
					Nome: %s
					Quantidade: %d\n
					""", pedido.getProduto(), pedido.getQuantidade());
		}
	}

}
