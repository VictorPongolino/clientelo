package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.modal.Produto;
import br.com.alura.clientelo.repository.ProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ProdutoServiceTest {

    private static final Long PRODUTO_ID_INVALIDO = 0L;
    @Autowired
    private ProdutoService produtoService;

    @MockBean
    private ProdutoRepository produtoRepository;

    @Test
    public void deveRetornarEmptyQuandoNaoForEncontradoUmID() {
        Mockito.when(produtoRepository.findById(PRODUTO_ID_INVALIDO)).thenReturn(Optional.empty());
        Optional<Produto> produto = produtoRepository.findById(PRODUTO_ID_INVALIDO);
        Assertions.assertTrue(produto.isEmpty());
    }
}
