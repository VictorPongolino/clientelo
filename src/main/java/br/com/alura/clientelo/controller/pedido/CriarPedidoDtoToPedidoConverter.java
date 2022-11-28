package br.com.alura.clientelo.controller.pedido;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import br.com.alura.clientelo.modal.Cliente;
import br.com.alura.clientelo.modal.ItemPedido;
import br.com.alura.clientelo.modal.Pedido;
import br.com.alura.clientelo.modal.Produto;
import br.com.alura.clientelo.repository.ClienteRepository;
import br.com.alura.clientelo.repository.ProdutoRepository;

@Component
public class CriarPedidoDtoToPedidoConverter {
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public CriarPedidoDtoToPedidoConverter(ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    public Pedido convert(CriarPedidoDTO criarPedidoDTO) {
        Cliente cliente = clienteRepository.findById(criarPedidoDTO.getIdCliente()).orElseThrow();

        LocalDate data = LocalDate.now();
        ArrayList<ItemPedido> itemPedidos = new ArrayList<>();
        Pedido pedido = new Pedido(itemPedidos, cliente, data);

        HashMap<Long, ItemPedido> pedidosFiltrados = new HashMap<>();
        criarPedidoDTO.getCadastroPedidoResultado().forEach(pedidoCadastrar -> {
            Produto produto = produtoRepository.findById(pedidoCadastrar.getIdProduto()).orElseThrow();
            BigDecimal precoUnitario = produto.getPreco();
            BigDecimal desconto = BigDecimal.ZERO;

            ItemPedido novoItem = new ItemPedido(precoUnitario, pedidoCadastrar.getQuantidadeVendida().intValue(), pedido, produto, desconto, ItemPedido.TipoDesconto.NENHUM);
            pedidosFiltrados.merge(pedidoCadastrar.getIdProduto(), novoItem, (itemAntigo, itemNovo) -> {
                itemNovo.adicionarItem(itemAntigo.getQuantidade());
                return itemNovo;
            });
        });

        itemPedidos.addAll(pedidosFiltrados.values());
        return pedido;
    }
}
