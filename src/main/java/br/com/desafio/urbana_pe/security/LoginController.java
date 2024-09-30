package br.com.desafio.urbana_pe.security;

import br.com.desafio.urbana_pe.modules.usuario.models.UsuarioEntity;
import br.com.desafio.urbana_pe.security.dto.LoginDTO;
import br.com.desafio.urbana_pe.security.dto.TokenDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    final private AuthenticationManager authenticationManager;

    final private TokenService tokenService;

    LoginController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping()
    public ResponseEntity<TokenDTO> authenticate(@RequestBody LoginDTO loginPayload) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginPayload.getEmail(), loginPayload.getSenha());
        var login = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UsuarioEntity) login.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(new TokenDTO(token,"Bearer", "guilhermeBackend"));
    }
}
