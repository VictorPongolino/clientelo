package br.com.alura.clientelo.reports.relatorios;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import br.com.alura.clientelo.reports.RelatorioMostruario;

public class RelatorioProdutosVendidos extends OperacaoPedido<Pedido> implements RelatorioMostruario {
	
	public RelatorioProdutosVendidos(List<Pedido> pedidos) {
		super(pedidos);
	}

	private List<Pedido> getProdutosMaisVendidos(int limit) {
		return getPedidos().stream()
				.sorted(comparing(Pedido::getQuantidade).reversed())
				.limit(limit)
				.collect(toList());
	}

	@Override
	public void accept() {
//		printTitle("Relatório de produtos mais vendidos");
		getProdutosMaisVendidos(3).forEach(pedido -> System.out.printf("Nome: %s\nQuantidade: %d\n", pedido.getProduto(), pedido.getQuantidade()));
	}

}
