package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.modal.pedido.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.alura.clientelo.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional
    public Pedido cadastrar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Long getTotalPedidosByClienteId(Long id) {
        return pedidoRepository.countByClienteId(id);
    }

    public Page<Pedido> findAll(Pageable pageable) {
        return pedidoRepository.findAll(pageable);
    }

    public Optional<Pedido> findById(Long pedidoId) {
        return pedidoRepository.findById(pedidoId);
    }
}
