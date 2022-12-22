package br.com.alura.clientelo.controller.produto.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {

    private Long produtoId;
    public ProdutoNaoEncontradoException(Long produtoId) {
        super("Não foi encontrado produto ID " + produtoId);
        this.produtoId = produtoId;
    }

    public Long getProdutoId() {
        return produtoId;
    }
}
