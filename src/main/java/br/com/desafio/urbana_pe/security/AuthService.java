package br.com.desafio.urbana_pe.security;

import br.com.desafio.urbana_pe.modules.usuario.models.UsuarioEntity;
import br.com.desafio.urbana_pe.modules.usuario.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UsuarioRepository repository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }
}
