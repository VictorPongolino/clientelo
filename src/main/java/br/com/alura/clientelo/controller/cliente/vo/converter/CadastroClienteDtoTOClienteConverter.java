package br.com.alura.clientelo.controller.cliente.vo.converter;

import br.com.alura.clientelo.controller.cliente.vo.CadastroClienteVO;
import br.com.alura.clientelo.modal.cliente.Cliente;
import br.com.alura.clientelo.modal.cliente.Endereco;
import org.springframework.stereotype.Component;

@Component
public class CadastroClienteDtoTOClienteConverter {
    public Cliente convert(CadastroClienteVO cadastroClienteDTO){
        Endereco endereco = new Endereco(cadastroClienteDTO.getRua(), cadastroClienteDTO.getNumero(), cadastroClienteDTO.getComplemento(), cadastroClienteDTO.getBairro(), cadastroClienteDTO.getCidade(), cadastroClienteDTO.getEstado());
        return new Cliente(cadastroClienteDTO.getNome(), cadastroClienteDTO.getCpf(), cadastroClienteDTO.getTelefone(),endereco);
    }
}
