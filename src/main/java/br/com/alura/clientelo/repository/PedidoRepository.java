package br.com.alura.clientelo.repository;

import br.com.alura.clientelo.modal.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Long countByClienteId(Long id);
}
