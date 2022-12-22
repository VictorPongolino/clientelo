package br.com.alura.clientelo.controller.cliente.dto.converter;

import br.com.alura.clientelo.controller.cliente.vo.CadastroClienteVO;
import br.com.alura.clientelo.modal.cliente.Cliente;
import br.com.alura.clientelo.modal.cliente.Endereco;
import org.springframework.stereotype.Component;

@Component
public class ClienteToCadastroClienteConverter {
    public CadastroClienteVO convert(Cliente clienteCadastrado) {
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
        return new CadastroClienteVO(nome, cpf, telefone, rua, numero, complemento, bairro, cidade, estado);
    }
}
