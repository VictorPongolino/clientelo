package br.com.alura.clientelo.leitor;

import br.com.alura.clientelo.modal.PedidoCSVDTO;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public interface ProcessadorArquivo {
    List<PedidoCSVDTO> lerArquivo(URL url) throws IOException, URISyntaxException;
}
