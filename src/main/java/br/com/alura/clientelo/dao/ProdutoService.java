package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.modal.Produto;
import br.com.alura.clientelo.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Optional<Produto> buscarPorId(Long produtoId) {
        return produtoRepository.findById(produtoId);
    }
    public Produto cadastrar(Produto produto) {
        return produtoRepository.save(produto);
    }
    public Page<Produto> listaTodas(Pageable pageable) {
        return this.produtoRepository.findAll(pageable);
    }

    public Page<Produto> listaIndisponiveis(Pageable pageable) {
        return this.produtoRepository.findByQtdEstoqueIs(0L, pageable);
    }
}
