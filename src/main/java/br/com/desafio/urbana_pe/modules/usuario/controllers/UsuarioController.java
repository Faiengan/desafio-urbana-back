package br.com.desafio.urbana_pe.modules.usuario.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.urbana_pe.modules.usuario.models.UsuarioEntity;
import br.com.desafio.urbana_pe.modules.usuario.services.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Object> criar(@Valid @RequestBody UsuarioEntity usuarioEtity) {
        try {
            var resultado = this.usuarioService.inserir(usuarioEtity);
            return ResponseEntity.status(HttpStatus.OK).body(resultado);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar-todos")
    public ResponseEntity<Object> buscarTodos() {
        try {
            var resultado =  this.usuarioService.buscarTodos();
            return ResponseEntity.status(HttpStatus.OK).body(resultado);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarUsuario(@PathVariable UUID id) {
        try {
            var resultado =  this.usuarioService.buscarUsuario(id);
            return ResponseEntity.status(HttpStatus.OK).body(resultado);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable UUID id) {
        try {
            this.usuarioService.apagarUsuario(id);
            return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso!");
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> alterarUsuario(@PathVariable UUID id, @Valid @RequestBody UsuarioEntity usuarioEntity) {
        try {
            var usuario = this.usuarioService.alterarUsuario(id, usuarioEntity);
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
