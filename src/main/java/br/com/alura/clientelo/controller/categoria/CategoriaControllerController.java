package br.com.alura.clientelo.controller.categoria;

import br.com.alura.clientelo.dao.CategoriaService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaControllerController {
    private final CategoriaService categoriaService;
    private final CadastrarCategoriaDtoToCategoria cadastrarCategoriaConverter;

    public CategoriaControllerController(CategoriaService categoriaService, CadastrarCategoriaDtoToCategoria converter) {
        this.categoriaService = categoriaService;
        this.cadastrarCategoriaConverter = converter;
    }

    @PostMapping
    public void cadastrar(@RequestBody @Valid CadastrarCategoriaDTO categoriaDTO) {
        categoriaService.cadastrar(cadastrarCategoriaConverter.converter(categoriaDTO));
    }
}
