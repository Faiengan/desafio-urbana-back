package br.com.desafio.urbana_pe.modules.cartao.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.urbana_pe.modules.cartao.models.CartaoEntity;

public interface CartaoRepository extends JpaRepository<CartaoEntity, UUID>{
    Optional<CartaoEntity> findByNumero(String numero);
    List<CartaoEntity> findByUsuarioId(UUID usuario_id);
}
