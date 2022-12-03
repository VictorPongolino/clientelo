package br.com.alura.clientelo.controller.categoria;

import br.com.alura.clientelo.modal.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CadastrarCategoriaDtoToCategoria {

    public Categoria converter(CadastrarCategoriaDTO categoriaDTO) {
        return new Categoria(categoriaDTO.getNome(), Categoria.Status.ATIVA);
    }
}
