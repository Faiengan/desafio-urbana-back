package br.com.desafio.urbana_pe.modules.cartao.controllers;

import java.util.Optional;
import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.urbana_pe.modules.cartao.models.CartaoEntity;
import br.com.desafio.urbana_pe.modules.cartao.services.CartaoService;
import br.com.desafio.urbana_pe.modules.usuario.models.UsuarioEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("cartao")
public class CartaoController {
    
    @Autowired
    private CartaoService cartaoService;
    
    @PostMapping("/cadastrar/{usuario_id}")
    public ResponseEntity<Object> cadastrarCartaoUsuario(@Valid @RequestBody CartaoEntity cartaoEntity, @PathVariable UUID usuario_id) {
        try {
            var resultado = this.cartaoService.cadastrarCartaoUsuario(cartaoEntity, usuario_id);
            return ResponseEntity.status(HttpStatus.OK).body(resultado);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar-todos")
    public ResponseEntity<Object> buscarTodos(@RequestParam(value = "usuario_id", required = false) UUID usuario_id) {
        try {
            var resultado = this.cartaoService.buscarTodos(Optional.ofNullable(usuario_id));
            return ResponseEntity.status(HttpStatus.OK).body(resultado);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCartao(@PathVariable UUID id) {
        try {
            this.cartaoService.apagarCartao(id);
            return ResponseEntity.status(HttpStatus.OK).body("Cartão deletado com sucesso!");
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> alterarStatus(@PathVariable UUID id, @RequestParam boolean status) {
        try {
            var resultado = this.cartaoService.alterarStatus(id, status);
            return ResponseEntity.status(HttpStatus.OK).body(resultado);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
