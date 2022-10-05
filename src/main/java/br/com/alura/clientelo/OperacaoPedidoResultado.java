package br.com.alura.clientelo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class OperacaoPedidoResultado {
	private final static NumberFormat PT_BR_CURRENCY = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	private List<Pedido> pedidos;

	public OperacaoPedidoResultado(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public void imprimirTopQuantidade(OrdenacaoPedido<Pedido> ordenacaoPedido) {
		List<Pedido> ordenarPorCategoria = ordenacaoPedido.ordenarQuantidade(pedidos);
		for (Pedido pedido : ordenarPorCategoria) {
			System.out.printf("""
					Nome: %s
					Quantidade: %d\n
					""", pedido.getProduto(), pedido.getQuantidade());
		}
	}
	
	public void imprimirTopCategoria(OrdenacaoPedido<Pedido> ordenacaoPedido) {
		List<Pedido> ordenarPorCategoria = ordenacaoPedido.ordenarPorCategoria(pedidos);
		for (Pedido pedido : ordenarPorCategoria) {
			System.out.printf("""
					Categoria: %s
					Nome: %s
					Montante: %s\n
					""", pedido.getCategoria(), pedido.getProduto(), toDinheiro(pedido.getPreco()));
		}
	}
	
	private String toDinheiro(BigDecimal dinheiro) {
		return PT_BR_CURRENCY.format(dinheiro.setScale(2, RoundingMode.HALF_DOWN));
	}

}
