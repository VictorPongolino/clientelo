package br.com.alura.clientelo.controller.cliente;

import br.com.alura.clientelo.modal.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteToListagemClienteDtoConverter {

    public ListagemClienteVO convert(Cliente cliente) {
        String estadp = cliente.getEndereco().getEstado();
        String cidade = cliente.getEndereco().getCidade();
        String local = String.format("%s/%s", cidade,estadp);
        return new ListagemClienteVO(cliente.getNome(), cliente.getCpf(), cliente.getTelefone(), local);
    }

}
