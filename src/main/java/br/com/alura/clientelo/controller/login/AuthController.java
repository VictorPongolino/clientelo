package br.com.alura.clientelo.controller.login;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @PostMapping("/auth")
    public TokenLoginDTO auth() {
        /*
            TODO: Gerar JWT e fornecer no payload de retorno
         */
        return null;
    }
}
