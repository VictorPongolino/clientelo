package br.com.alura.clientelo;

import br.com.alura.clientelo.leitor.LeitorArquivos;
import br.com.alura.clientelo.leitor.ProcessadorDeCsv;
import br.com.alura.clientelo.leitor.dto.PedidoCSVDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException, URISyntaxException {
        URL url = ClassLoader.getSystemResource("pedidos.csv");
        PedidoCSVDTO[] pedidos = LeitorArquivos.from(url, new ProcessadorDeCsv());

        // TODO - SEMANA 6
    }
}
