package br.com.alura.clientelo.controller.login;

import javax.validation.constraints.NotNull;

public class AuthRequestDTO {
    @NotNull
    private final String username;
    @NotNull
    private final String password;

    public AuthRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

