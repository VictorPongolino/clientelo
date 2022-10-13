package br.com.alura.clientelo.reports.logic;

import java.util.List;
import java.util.Map;

import br.com.alura.clientelo.Pedido;

public interface OrdenacaoPedido<T> {

	List<T> ordenarQuantidade(List<T> lista);
	Map<String, QuantidadeCategoria> ordenarPorCategoria(List<T> lista);
	Map<String, T> ordenarPorCategoriaMaiorPreco(List<Pedido> pedidos);
	Map<String, Integer> ordenarPorClientes(List<T> lista);

	Map<String, TopClienteGastos> ordenarPorClienteLucrativo(List<T> lista);
}
