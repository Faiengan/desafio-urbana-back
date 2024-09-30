package br.com.desafio.urbana_pe.modules.usuario.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.urbana_pe.modules.usuario.models.UsuarioEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {
    UserDetails findByEmail(String email);
}
