package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.modal.Produto;
import br.com.alura.clientelo.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    private final ObjectMapper objectMapper;

    public ProdutoService(ProdutoRepository produtoRepository, ObjectMapper objectMapper) {
        this.produtoRepository = produtoRepository;
        this.objectMapper = objectMapper;
    }

    public Optional<Produto> buscarPorId(Long produtoId) {
        return produtoRepository.findById(produtoId);
    }
    @Transactional
    public Produto cadastrar(Produto produto) {
        return produtoRepository.save(produto);
    }
    public Page<Produto> listaTodas(Pageable pageable) {
        return this.produtoRepository.findAll(pageable);
    }

    public Page<Produto> listaIndisponiveis(Pageable pageable) {
        return this.produtoRepository.findByQtdEstoqueIs(0L, pageable);
    }

    public Optional<Produto> findById(Long produtoId) {
        return produtoRepository.findById(produtoId);
    }

    @Transactional
    public Produto atualizar(Produto produtoDadosNovos) {
        return produtoRepository.save(objectMapper.convertValue(produtoDadosNovos, Produto.class));
    }
}
