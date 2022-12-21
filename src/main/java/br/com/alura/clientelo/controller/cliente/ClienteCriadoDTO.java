package br.com.alura.clientelo.controller.cliente;


public class ClienteCriadoDTO {

    private final Long id;
    private final String nome;
    private final String cpf;
    private final String telefone;
    private final EnderecoCadastradoDTO endereco;

    public ClienteCriadoDTO(Long id, String nome, String cpf, String telefone, EnderecoCadastradoDTO endereco) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public EnderecoCadastradoDTO getEndereco() {
        return endereco;
    }

    public static class EnderecoCadastradoDTO {
        private final String rua;
        private final String numero;
        private final String complemento;
        private final String bairro;
        private final String cidade;
        private final String estado;

        public EnderecoCadastradoDTO(String rua, String numero, String complemento, String bairro, String cidade, String estado) {
            this.rua = rua;
            this.numero = numero;
            this.complemento = complemento;
            this.bairro = bairro;
            this.cidade = cidade;
            this.estado = estado;
        }

        public String getRua() {
            return rua;
        }

        public String getNumero() {
            return numero;
        }

        public String getComplemento() {
            return complemento;
        }

        public String getBairro() {
            return bairro;
        }

        public String getCidade() {
            return cidade;
        }

        public String getEstado() {
            return estado;
        }
    }
}
