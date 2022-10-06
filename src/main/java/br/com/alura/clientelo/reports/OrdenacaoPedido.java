package br.com.alura.clientelo.reports;

import java.util.List;
import java.util.Map;

import br.com.alura.clientelo.Pedido;

public interface OrdenacaoPedido<T> {

	List<T> ordenarQuantidade(List<T> lista);
	List<T> ordenarPorCategoria(List<T> lista);
	Map<String, T> ordenarPorCategoriaMaiorPreco(List<Pedido> pedidos);
}
