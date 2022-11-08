package br.com.alura.clientelo.leitor;

import br.com.alura.clientelo.modal.PedidoCSVDTO;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LeitorArquivos {

    public static PedidoCSVDTO[] from(URL url, ProcessadorArquivo processador) throws IOException, URISyntaxException {
        return processador.lerArquivo(url).toArray(new PedidoCSVDTO[0]);
    }
}
