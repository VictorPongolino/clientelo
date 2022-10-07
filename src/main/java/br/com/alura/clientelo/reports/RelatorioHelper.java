package br.com.alura.clientelo.reports;

import br.com.alura.clientelo.reports.logic.OrdenacaoPedido;

public class RelatorioHelper<T> {

	private final OrdenacaoPedido<T> ordenacao;

	public RelatorioHelper(OrdenacaoPedido<T> ordenacao) {
		this.ordenacao = ordenacao;
	}
	
	public void show(OperacaoPedido<T> pedido) {
		pedido.accept(ordenacao);
	}
}
