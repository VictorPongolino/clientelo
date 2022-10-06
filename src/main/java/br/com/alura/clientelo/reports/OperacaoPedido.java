package br.com.alura.clientelo.reports;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public abstract class OperacaoPedido<T> {
	private final static NumberFormat PT_BR_CURRENCY = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	private List<T> pedidos;
	
	public OperacaoPedido(List<T> pedidos) {
		this.pedidos = pedidos;
	}
	
	protected void printTitle(String title) {
		System.out.println();
		System.out.println("---------------------------------------------------------------");
		System.out.println(title);
		System.out.println("---------------------------------------------------------------");
		System.out.println();
	}
	
	protected String toDinheiro(BigDecimal dinheiro) {
		return PT_BR_CURRENCY.format(dinheiro.setScale(2, RoundingMode.HALF_DOWN));
	}

	public List<T> getPedidos() {
		return pedidos;
	}

	public abstract void accept(OrdenacaoPedido<T> ordenacaoPedido);
}
