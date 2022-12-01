package br.com.alura.clientelo.dao;

import java.util.Optional;

import br.com.alura.clientelo.modal.Categoria;
import br.com.alura.clientelo.repository.CategoriaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Optional<Categoria> buscarPorId(Long categoriaId) {
        return categoriaRepository.findById(categoriaId);
    }
    @Transactional
    public Categoria cadastrar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
    public Page<Categoria> listaTodas(Pageable pagina) {
        return this.categoriaRepository.findAll(pagina);
    }
}
