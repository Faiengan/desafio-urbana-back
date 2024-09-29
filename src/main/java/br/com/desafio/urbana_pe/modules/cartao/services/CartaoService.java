package br.com.desafio.urbana_pe.modules.cartao.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.urbana_pe.exceptions.NotFoundException;
import br.com.desafio.urbana_pe.modules.cartao.models.CartaoEntity;
import br.com.desafio.urbana_pe.modules.cartao.repositories.CartaoRepository;
import br.com.desafio.urbana_pe.modules.usuario.models.UsuarioEntity;
import br.com.desafio.urbana_pe.modules.usuario.services.UsuarioService;


@Service
public class CartaoService {
    
    @Autowired
    private CartaoRepository cartaoRepository;
    
    @Autowired
    private UsuarioService usuarioService;


    public CartaoEntity cadastrarCartaoUsuario(CartaoEntity cartaoEntity, UUID usuario_id) {
        this.usuarioService.buscarUsuario(usuario_id);
        this.cartaoRepository
            .findByNumero(cartaoEntity.getNumero())
            .ifPresent((n) -> {
                throw new NotFoundException("Já exite um cartão com esse número. Informe um número diferente!");
        });
        cartaoEntity.setUsuarioId(usuario_id);
        cartaoEntity.setStatus(true);
        return this.cartaoRepository.save(cartaoEntity);
    }

    public List<CartaoEntity> buscarTodos(Optional<UUID> usuario_id) {
        if (usuario_id.isPresent()) {
            return this.cartaoRepository.findByUsuarioId(usuario_id.get());
        } else {
            return this.cartaoRepository.findAll();
        }
    }

    public void apagarCartao(UUID id) {
        var cartao = this.cartaoRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("O cartão não foi encontrado"));

        cartaoRepository.delete(cartao);
    }

    public CartaoEntity alterarStatus(UUID id, boolean status) {
        var cartao = this.cartaoRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("O cartão não foi encontrado"));
        cartao.setStatus(status ? status : false);
        return this.cartaoRepository.save(cartao);
    }


}
