package br.com.alura.clientelo.controller.login;

public class TokenLoginDTO {
    private final String token;

    public TokenLoginDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
