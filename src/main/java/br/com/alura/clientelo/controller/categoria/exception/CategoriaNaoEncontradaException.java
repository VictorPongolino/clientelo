package br.com.alura.clientelo.controller.categoria.exception;

public class CategoriaNaoEncontradaException extends RuntimeException {

    private Integer categoriaId;

    public CategoriaNaoEncontradaException(Integer categoriaId) {
        super("Não foi encontrada categoria de ID " + categoriaId);
        this.categoriaId = categoriaId;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }
}
