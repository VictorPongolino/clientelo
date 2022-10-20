package br.com.alura.clientelo.reports.logic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import br.com.alura.clientelo.Pedido;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class OrdenacaoPedidoImpl implements OrdenacaoPedido<Pedido> {

	@Override
	public List<Pedido> ordenarQuantidade(List<Pedido> pedidos) {
		return pedidos.stream()
				.sorted(comparing(Pedido::getQuantidade).reversed())
				.limit(3)
				.collect(toList());
	}

	@Override
	public Map<String, QuantidadeCategoria> ordenarPorCategoria(List<Pedido> pedidos) {
		return pedidos.stream()
				.sorted(comparing(Pedido::getCategoria))
				.collect(toMap(Pedido::getCategoria, valor -> new QuantidadeCategoria(valor.getQuantidade(), valor.getPreco().multiply(new BigDecimal(valor.getQuantidade()))), (k, v) -> {
					k.atualizar(v.getQuantidade(), v.getMontante());
					return k;
				}));
	}
	
	@Override
	public Map<String, Pedido> ordenarPorCategoriaMaiorPreco(List<Pedido> pedidos) {
		return pedidos.stream()
				.sorted(comparing(Pedido::getCategoria))
				.collect(toMap(Pedido::getCategoria, pedido -> pedido, (k, v) -> {
					BigDecimal preco1 = k.getPreco().multiply(new BigDecimal(k.getQuantidade()));
					BigDecimal preco2 = v.getPreco().multiply(new BigDecimal(v.getQuantidade()));
					return preco1.compareTo(preco2) > 0 ? k : v;
				}, LinkedHashMap::new));
	}

	@Override
	public Map<String, Integer> ordenarPorClientes(List<Pedido> lista) {
		LinkedHashMap<String, Integer> ordemPorClientes = lista.stream()
					.sorted(comparing(Pedido::getCliente))
					.collect(groupingBy(Pedido::getCliente, LinkedHashMap::new, summingInt(x -> 1)));

		return ordemPorClientes.entrySet()
				.stream()
				.sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
				.collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> x, LinkedHashMap::new));
	}

	@Override
	public Map<String, TopClienteGastos> ordenarPorClienteLucrativo(List<Pedido> lista) {
		Map<String, TopClienteGastos> tops = new LinkedHashMap<>();
		lista.stream()
			.sorted(comparing(Pedido::getCliente))
			.sorted((o1, o2) -> o2.getPreco().multiply(new BigDecimal(o2.getQuantidade())).compareTo(o1.getPreco().multiply(new BigDecimal(o1.getQuantidade()))))
			.forEach(pedido -> {
				BigDecimal totalPedido = pedido.getPreco().multiply(new BigDecimal(pedido.getQuantidade())).setScale(2, RoundingMode.HALF_DOWN);
				tops.merge(pedido.getCliente(), new TopClienteGastos(1, totalPedido), (cliente1, cliente2) -> {
					cliente1.contabilizar(1, totalPedido);
					return cliente1;
				});
			});

		return tops.entrySet().stream().limit(2).collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> x, LinkedHashMap::new));
	}
}
