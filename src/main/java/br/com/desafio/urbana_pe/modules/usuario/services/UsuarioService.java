package br.com.desafio.urbana_pe.modules.usuario.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.desafio.urbana_pe.exceptions.NotFoundException;
import br.com.desafio.urbana_pe.modules.usuario.models.UsuarioEntity;
import br.com.desafio.urbana_pe.modules.usuario.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioEntity inserir(UsuarioEntity usuarioEntity) {
        emailExistente(usuarioEntity.getEmail());
        usuarioEntity.setSenha(passwordEncoder.encode(usuarioEntity.getSenha()));
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
        usuario.setSenha(passwordEncoder.encode(usuarioEntity.getSenha()));

        return this.usuarioRepository.save(usuario);
    }

    public void emailExistente(String email) {
        if (usuarioRepository.findByEmail(email) != null) {
            throw new NotFoundException("Usuário já existe. Informe um e-mail diferente!");
        }
    }


}
