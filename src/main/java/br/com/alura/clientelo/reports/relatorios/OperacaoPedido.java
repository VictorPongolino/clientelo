package br.com.alura.clientelo.reports.relatorios;

import java.util.Collections;
import java.util.List;

public abstract class OperacaoPedido<T> {
	private final List<T> pedidos;
	
	public OperacaoPedido(List<T> pedidos) {
		this.pedidos = pedidos;
	}

	public List<T> getPedidos() {
		return Collections.unmodifiableList(pedidos);
	}
}
