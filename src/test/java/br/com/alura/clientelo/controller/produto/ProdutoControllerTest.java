package br.com.alura.clientelo.controller.produto;


import br.com.alura.clientelo.controller.produto.dto.CriarProdutoDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import br.com.alura.clientelo.dao.CategoriaService;
import br.com.alura.clientelo.modal.Categoria;

@ActiveProfiles("testes")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CategoriaService categoriaService;

    @Before
    public void setup() {
        categoriaService.cadastrar(new Categoria("PÃO", Categoria.Status.ATIVA));
    }

    @Test
    public void deveRetornarCreated_criacaoProdutoSucesso() throws Exception {
        CriarProdutoDTO criarProdutoDTO = new CriarProdutoDTO("Nome", new BigDecimal("2.0"), "DESCRIÇÃO", 2, 1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/produtos")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(criarProdutoDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

}
