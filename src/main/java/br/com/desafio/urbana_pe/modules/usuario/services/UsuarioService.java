package br.com.desafio.urbana_pe.modules.usuario.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.urbana_pe.exceptions.NotFoundException;
import br.com.desafio.urbana_pe.modules.usuario.models.UsuarioEntity;
import br.com.desafio.urbana_pe.modules.usuario.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioEntity inserir(UsuarioEntity usuarioEntity) {
        this.usuarioRepository
            .findByEmail(usuarioEntity.getEmail())
            .ifPresent((usuario) -> {
                throw new NotFoundException("Usuário já existe. Informe um e-mail diferente!");
        });
        return this.usuarioRepository.save(usuarioEntity);
    }

    public List<UsuarioEntity> buscarTodos() {
        return this.usuarioRepository.findAll();
    }

    public UsuarioEntity buscarUsuario(UUID id) {
        var usuario = this.usuarioRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Usuário não foi encontrado"));
        return usuario;
    }

    public void apagarUsuario(UUID id) {
        var usuario = this.usuarioRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Usuário não foi encontrado"));

        usuarioRepository.delete(usuario);
    }

    public UsuarioEntity alterarUsuario(UUID id, UsuarioEntity usuarioEntity) {
        UsuarioEntity usuario = this.usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não foi encontrado"));

        usuario.setNome(usuarioEntity.getNome());
        usuario.setEmail(usuarioEntity.getEmail());
        usuario.setSenha(usuarioEntity.getSenha());

        return this.usuarioRepository.save(usuario);
    }


}
