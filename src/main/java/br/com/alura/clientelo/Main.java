package br.com.alura.clientelo;

import br.com.alura.clientelo.leitor.LeitorArquivos;
import br.com.alura.clientelo.leitor.ProcessadorDeCsv;
import br.com.alura.clientelo.modal.Pedido;
import br.com.alura.clientelo.reports.logic.RelatorioSintetico;
import br.com.alura.clientelo.reports.utils.DinheiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import br.com.alura.clientelo.reports.RelatorioHelper;
import br.com.alura.clientelo.reports.relatorios.RelatorioClientesLucrativos;
import br.com.alura.clientelo.reports.relatorios.RelatorioProdutoesCarosCategoria;
import br.com.alura.clientelo.reports.relatorios.RelatorioProdutosVendidos;
import br.com.alura.clientelo.reports.relatorios.RelatorioQuantidadePorCliente;
import br.com.alura.clientelo.reports.relatorios.RelatorioVendasCategoria;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException, URISyntaxException {
        URL url = ClassLoader.getSystemResource("pedidos.csv");
        Pedido[] pedidos = LeitorArquivos.from(url, new ProcessadorDeCsv());

        List<Pedido> todosPedidos = Arrays.asList(pedidos);

        RelatorioSintetico relatorioSintetico = new RelatorioSintetico();
        System.out.printf("TOTAL DE PEDIDOS REALIZADOS: %d\n", relatorioSintetico.getTotalPedidosRealizados(todosPedidos));
        System.out.printf("TOTAL DE PRODUTOS VENDIDOS: %d\n", relatorioSintetico.getTotalProdutosVendidos(todosPedidos));
        System.out.printf("TOTAL DE CATEGORIAS: %d\n", relatorioSintetico.getTotalCategorias(todosPedidos));
        System.out.printf("MONTANTE DE VENDAS: R$ %s\n", DinheiroUtils.formatarDinheiroBrasileiro(relatorioSintetico.getMontanteVendas(todosPedidos)));

        Pedido pedidoBarato = relatorioSintetico.getPedidoMaisBarato(todosPedidos).get();
        Pedido pedidoCaro = relatorioSintetico.getPedidoMaisCaro(todosPedidos).get();
        System.out.printf("PEDIDO MAIS BARATO: R$ %s (%s)\n", DinheiroUtils.formatarDinheiroBrasileiro(pedidoBarato.getPreco()), pedidoBarato.getProduto());
        System.out.printf("PEDIDO MAIS CARO: R$ %s (%s)\n", DinheiroUtils.formatarDinheiroBrasileiro(pedidoCaro.getPreco()), pedidoCaro.getProduto());

        RelatorioHelper.mostrar(new RelatorioVendasCategoria(todosPedidos));
        RelatorioHelper.mostrar(new RelatorioProdutosVendidos(todosPedidos));
        RelatorioHelper.mostrar(new RelatorioVendasCategoria(todosPedidos));
        RelatorioHelper.mostrar(new RelatorioProdutoesCarosCategoria(todosPedidos));
        RelatorioHelper.mostrar(new RelatorioQuantidadePorCliente(todosPedidos));
        RelatorioHelper.mostrar(new RelatorioClientesLucrativos(todosPedidos));
    }
}
