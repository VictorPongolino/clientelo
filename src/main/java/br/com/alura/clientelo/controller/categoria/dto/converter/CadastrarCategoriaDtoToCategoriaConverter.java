package br.com.alura.clientelo.controller.categoria.dto.converter;

import br.com.alura.clientelo.controller.categoria.dto.CadastrarCategoriaDTO;
import br.com.alura.clientelo.modal.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CadastrarCategoriaDtoToCategoriaConverter {

    public Categoria converter(CadastrarCategoriaDTO categoriaDTO) {
        return new Categoria(categoriaDTO.getNome(), Categoria.Status.ATIVA);
    }
}
