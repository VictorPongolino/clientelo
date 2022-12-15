package br.com.alura.clientelo.controller.categoria;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("testes")
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CategoriaController.class)
class CategoriaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void deveRetornar201_quandoCadastrarComDTOcorreto() throws Exception {
        CadastrarCategoriaDTO cadastro = new CadastrarCategoriaDTO("TESTE");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/categorias")
                .content(objectMapper.writeValueAsString(cadastro))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}