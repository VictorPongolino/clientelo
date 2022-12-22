package br.com.alura.clientelo.controller.login.dto;

public class TokenLoginDTO {
    private final String token;

    public TokenLoginDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
