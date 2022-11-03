package br.com.alura.clientelo.reports.relatorios;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toMap;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;

import br.com.alura.clientelo.modal.Pedido;
import br.com.alura.clientelo.reports.RelatorioMostruario;
import br.com.alura.clientelo.reports.utils.DinheiroUtils;

public class RelatorioProdutoesCarosCategoria extends OperacaoPedido<Pedido> implements RelatorioMostruario {

	public RelatorioProdutoesCarosCategoria(List<Pedido> pedidos) {
		super(pedidos);
	}

	LinkedHashMap<String, Pedido> getProdutoCaroPorCategoria() {
		return getPedidos().stream()
				.sorted(comparing(Pedido::getCategoria))
				.collect(toMap(Pedido::getCategoria, Function.identity(), (k, v) -> {
					BigDecimal preco1 = k.getPreco().multiply(new BigDecimal(k.getQuantidade()));
					BigDecimal preco2 = v.getPreco().multiply(new BigDecimal(v.getQuantidade()));
					return preco1.compareTo(preco2) > 0 ? k : v;
				}, LinkedHashMap::new));
	}

	@Override
	public void accept() {
//		printTitle("Relatório de produtos mais caros de cada categoria");
		getProdutoCaroPorCategoria().entrySet().forEach(categoriaPorPedido -> {
			Pedido pedido = categoriaPorPedido.getValue();
			System.out.printf("CATEGORIA: %s\nPRODUTO: %s\nPREÇO: R$ %s\n\n",
					categoriaPorPedido.getKey(), pedido.getProduto(), DinheiroUtils.formatarDinheiroBrasileiro(pedido.getPreco()));
		});

	}
}
