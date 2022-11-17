package br.com.alura.clientelo.repository;

import br.com.alura.clientelo.modal.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Page<Produto> findByQtdEstoqueIs(Long qtdEstoque, Pageable pageable);
}
