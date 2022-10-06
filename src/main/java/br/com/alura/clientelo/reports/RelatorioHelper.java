package br.com.alura.clientelo.reports;

public class RelatorioHelper<T> {

	private OrdenacaoPedido<T> ordenacao;

	public RelatorioHelper(OrdenacaoPedido<T> ordenacao) {
		this.ordenacao = ordenacao;
	}
	
	public void show(OperacaoPedido<T> pedido) {
		pedido.accept(ordenacao);
	}
}
