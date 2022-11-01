package br.com.alura.clientelo.reports;

import br.com.alura.clientelo.Pedido;
import br.com.alura.clientelo.ProcessadorDeCsv;
import br.com.alura.clientelo.reports.logic.data.QuantidadeCategoria;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RelatorioVendasCategoriaTest {

    private OrdenacaoPedidoImpl ordenacaoPedidoImpl = new OrdenacaoPedidoImpl();

    @Test
    public void estaNaOrdemVendasCategorias() {
        Map<String, QuantidadeCategoria> produtos = ordenacaoPedidoImpl.ordenarPorCategoria(obterRelatorios());

        Integer[] quantidadeCategorias = produtos.values().stream().map(QuantidadeCategoria::getQuantidade).limit(5).collect(Collectors.toList()).toArray(new Integer[0]);
        BigDecimal[] precos = produtos.values().stream().map(QuantidadeCategoria::getMontante).limit(5).collect(Collectors.toList()).toArray(new BigDecimal[0]);

        // TODO: Mudar para JUNIT 5
        assertEquals(precos, new BigDecimal[]{ new BigDecimal("1507.64"), new BigDecimal("1987.97"), new BigDecimal("12378.98"), new BigDecimal("97801.50"), new BigDecimal("64698.40")});
        assertEquals(quantidadeCategorias, new Integer[]{ 9, 2, 4, 11, 9});
        assertEquals(Arrays.copyOfRange(produtos.keySet().toArray(), 0, 5), new String[]{ "LIVROS", "AUTOMOTIVA", "MÓVEIS", "CELULARES", "INFORMÁTICA" });
    }

    private List<Pedido> obterRelatorios() {
        return Arrays.asList(ProcessadorDeCsv.processaArquivo("pedidos.csv"));
    }
}