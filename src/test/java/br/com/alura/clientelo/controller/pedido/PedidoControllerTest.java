package br.com.alura.clientelo.controller.pedido;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import br.com.alura.clientelo.controller.pedido.dto.CadastroPedidoResultado;
import br.com.alura.clientelo.controller.pedido.dto.CriarPedidoDTO;
import br.com.alura.clientelo.controller.pedido.dto.DetalhePedidoVO;
import br.com.alura.clientelo.dao.PedidoService;
import br.com.alura.clientelo.modal.Categoria;
import br.com.alura.clientelo.modal.Cliente;
import br.com.alura.clientelo.modal.Endereco;
import br.com.alura.clientelo.modal.ItemPedido;
import br.com.alura.clientelo.modal.Pedido;
import br.com.alura.clientelo.modal.Produto;

@ActiveProfiles("testes")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PedidoService pedidoService;

    @Test
    public void deveRetornar201_consultarPedidoSendoVendedorCerto() throws Exception {
        Pedido pedido = gerarPedidoMockado();
        pedido.setId(1L);

        Mockito.when(pedidoService.cadastrar(pedido)).thenReturn(pedido);
        Mockito.when(pedidoService.findById(1L)).thenReturn(Optional.of(pedido));

        Pedido ultimoPedido = pedidoService.cadastrar(pedido);

        MvcResult resultado = mockMvc.perform(MockMvcRequestBuilders.get("/api/pedidos/{id}", ultimoPedido.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        DetalhePedidoVO detalhePedidoVO = objectMapper.readValue(resultado.getResponse().getContentAsString(), DetalhePedidoVO.class);
        assertEquals(1, detalhePedidoVO.getDetalheProduto().size());
        assertEquals(pedido.getPrecoFinal(), detalhePedidoVO.getValor());
        assertEquals(pedido.getDescontoTotal(), detalhePedidoVO.getDesconto());
        assertEquals(pedido.getData(), detalhePedidoVO.getDataPedido());
    }

    @Test
    public void deveRetornar404_QuandoIdDoProdutoNaoExiste() throws Exception {
        Pedido pedido = gerarPedidoMockado();
        pedido.setId(1L);

        Mockito.when(pedidoService.cadastrar(pedido)).thenReturn(pedido);
        Mockito.when(pedidoService.findById(1L)).thenReturn(Optional.empty());

        Pedido ultimoPedido = pedidoService.cadastrar(pedido);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pedidos/{id}", ultimoPedido.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", equalTo("Pedido ID 1 não existe!")));
    }

    private Pedido gerarPedidoMockado() {
        Categoria categoria = new Categoria("CATEGORIA", Categoria.Status.ATIVA);
        Endereco endereco = new Endereco("RUA", "NUMERO", "COMPLEMENTO", "BAIRRO", "CIDADE", "ESTADO");
        Produto produto = new Produto("Nome Produto", "DESC", 12, new BigDecimal("2.0"), categoria);
        Cliente cliente = new Cliente("NOME", "CPF", "TELEFONE", endereco);

        List<ItemPedido> itemPedido = new ArrayList<>();

        Pedido pedido = new Pedido(itemPedido, cliente, LocalDate.of(2022, 12, 15));

        itemPedido.add(new ItemPedido(new BigDecimal("1.0"), 1, pedido, produto, BigDecimal.ZERO, ItemPedido.TipoDesconto.NENHUM));
        return pedido;
    }

    @Test
    public void deveRetornar201_criarPedido() throws Exception {
        Set<CadastroPedidoResultado> cadastroPedidoResultados = new HashSet<>();
        cadastroPedidoResultados.add(new CadastroPedidoResultado(1L, 12L));
        CriarPedidoDTO criarPedidoDTO = new CriarPedidoDTO(1L, cadastroPedidoResultados);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/pedidos")
                .content(objectMapper.writeValueAsString(criarPedidoDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}