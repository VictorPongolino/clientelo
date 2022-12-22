package br.com.alura.clientelo.controller.login;

import br.com.alura.clientelo.controller.login.dto.AuthRequestDTO;
import br.com.alura.clientelo.dao.authentication.TokenService;
import br.com.alura.clientelo.dao.authentication.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    private final TokenService tokenService;
    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    public AuthController(TokenService tokenService, UsuarioService usuarioService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.usuarioService = usuarioService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody @Valid AuthRequestDTO authRequestDTO) {
        UsernamePasswordAuthenticationToken credentialsAuthentication = new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword());
        try {
            Authentication authenticate = authenticationManager.authenticate(credentialsAuthentication);
            return ResponseEntity.ok(tokenService.gerar(authenticate.getName()));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
