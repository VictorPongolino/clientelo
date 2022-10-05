package br.com.alura.clientelo;

import java.util.List;

public interface OrdenacaoPedido<T> {

	List<T> ordenarQuantidade(List<T> lista);
	List<T> ordenarPorCategoria(List<T> lista);
}
