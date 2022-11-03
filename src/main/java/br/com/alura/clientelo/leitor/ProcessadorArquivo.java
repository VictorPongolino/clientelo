package br.com.alura.clientelo.leitor;

import br.com.alura.clientelo.modal.Pedido;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public interface ProcessadorArquivo {
    List<Pedido> lerArquivo(URL url) throws IOException;
}
