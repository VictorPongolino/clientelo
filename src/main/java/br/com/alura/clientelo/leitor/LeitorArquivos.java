package br.com.alura.clientelo.leitor;

import br.com.alura.clientelo.modal.Pedido;

import java.io.IOException;
import java.net.URL;

public class LeitorArquivos {
    public static Pedido[] from(URL url, ProcessadorArquivo processador) throws IOException {
        return processador.lerArquivo(url).toArray(new Pedido[0]);
    }
}
