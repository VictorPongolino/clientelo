package br.com.alura.clientelo.controller.pedido;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

import br.com.alura.clientelo.dao.ClienteService;
import br.com.alura.clientelo.modal.cliente.Cliente;
import br.com.alura.clientelo.modal.pedido.Pedido;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
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
import br.com.alura.clientelo.modal.cliente.Endereco;
import br.com.alura.clientelo.modal.pedido.ItemPedido;
import br.com.alura.clientelo.modal.Produto;
import org.junit.runner.RunWith;

@ActiveProfiles("testes")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PedidoService pedidoService;
    @MockBean
    private ClienteService clienteService;

    private Pedido pedido;
    @Before
    public void setup() {
        Categoria categoria = new Categoria("CATEGORIA", Categoria.Status.ATIVA);
        Endereco endereco = new Endereco("RUA", "NUMERO", "COMPLEMENTO", "BAIRRO", "CIDADE", "ESTADO");
        Produto produto = new Produto("Nome Produto", "DESC", 12, new BigDecimal("2.0"), categoria);
        Cliente cliente = new Cliente("NOME", "CPF", "TELEFONE", endereco);

        List<ItemPedido> itemPedido = new ArrayList<>();

        pedido = new Pedido(itemPedido, cliente, LocalDate.of(2022, 12, 15));
        pedido.setId(1L);

        itemPedido.add(new ItemPedido(new BigDecimal("1.0"), 1, pedido, produto, BigDecimal.ZERO, ItemPedido.TipoDesconto.NENHUM));

        Mockito.when(pedidoService.cadastrar(pedido)).thenReturn(pedido);
        Mockito.when(pedidoService.findById(1L)).thenReturn(Optional.of(pedido));
        Mockito.when(clienteService.findById(1L)).thenReturn(Optional.of(cliente));
    }


    @Test
    public void deveRetornar201_consultarPedidoSendoVendedorCerto() throws Exception {

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
        Pedido ultimoPedido = pedidoService.cadastrar(pedido);
        Mockito.when(pedidoService.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pedidos/{id}", ultimoPedido.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", equalTo("Pedido ID 1 não existe!")));
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