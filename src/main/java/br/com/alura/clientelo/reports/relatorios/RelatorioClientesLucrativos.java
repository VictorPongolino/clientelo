package br.com.alura.clientelo.reports.relatorios;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toMap;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.com.alura.clientelo.modal.Pedido;
import br.com.alura.clientelo.reports.RelatorioMostruario;
import br.com.alura.clientelo.reports.logic.data.TopClienteGastos;
import br.com.alura.clientelo.reports.utils.DinheiroUtils;

public class RelatorioClientesLucrativos extends OperacaoPedido<Pedido> implements RelatorioMostruario {
    public RelatorioClientesLucrativos(List<Pedido> pedidos) {
        super(pedidos);
    }

    private LinkedHashMap<String, TopClienteGastos> getClientesLucrativos() {
        Map<String, TopClienteGastos> tops = new LinkedHashMap<>();
        getPedidos().stream()
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

    @Override
    public void accept() {
//        printTitle("Relatório de clientes mais lucrativos");
        getClientesLucrativos().entrySet().forEach(pedidos -> {
            System.out.printf("NOME: %s\nNº DE PEDIDOS: %d\nMONTANTE GASTO: R$ %s\n\n", pedidos.getKey(), pedidos.getValue().getQuantidade(), DinheiroUtils.formatarDinheiroBrasileiro(pedidos.getValue().getTotalGasto()));
        });
    }
}
