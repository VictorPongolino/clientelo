package br.com.alura.clientelo.controller.cliente;

import br.com.alura.clientelo.modal.Cliente;
import br.com.alura.clientelo.modal.Endereco;
import org.springframework.stereotype.Component;

@Component
public class ClienteToCadastroClienteConverter {
    public CadastroClienteDTO convert(Cliente clienteCadastrado) {
        Endereco endereco = clienteCadastrado.getEndereco();
        String nome = clienteCadastrado.getNome();
        String cpf = clienteCadastrado.getCpf();
        String telefone = clienteCadastrado.getTelefone();
        String rua = endereco.getRua();
        String numero = endereco.getNumero();
        String complemento = endereco.getComplemento();
        String bairro = endereco.getBairro();
        String cidade = endereco.getCidade();
        String estado = endereco.getEstado();
        return new CadastroClienteDTO(nome, cpf, telefone, rua, numero, complemento, bairro, cidade, estado);
    }
}
