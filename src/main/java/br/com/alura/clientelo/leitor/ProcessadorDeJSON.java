package br.com.alura.clientelo.leitor;

import br.com.alura.clientelo.modal.Pedido;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class ProcessadorDeJSON implements ProcessadorArquivo {
    @Override
    public List<Pedido> lerArquivo(URL url) throws IOException {
        ObjectMapper objectMapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        return objectMapper.readValue(url, new TypeReference<>(){});
    }
}
