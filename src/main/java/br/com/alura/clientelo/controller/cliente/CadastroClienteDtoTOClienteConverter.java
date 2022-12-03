package br.com.alura.clientelo.controller.cliente;

import br.com.alura.clientelo.modal.Cliente;
import br.com.alura.clientelo.modal.Endereco;
import org.springframework.stereotype.Component;

@Component
public class CadastroClienteDtoTOClienteConverter {
    public Cliente convert(CadastroClienteDTO cadastroClienteDTO){
        Endereco endereco = new Endereco(cadastroClienteDTO.getRua(), cadastroClienteDTO.getNumero(), cadastroClienteDTO.getComplemento(), cadastroClienteDTO.getBairro(), cadastroClienteDTO.getCidade(), cadastroClienteDTO.getEstado());
        return new Cliente(cadastroClienteDTO.getNome(), cadastroClienteDTO.getCpf(), cadastroClienteDTO.getTelefone(),endereco);
    }
}
