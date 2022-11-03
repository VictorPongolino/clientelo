package br.com.alura.clientelo.modal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categoria {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Status status;

    private Categoria() {}
    public Categoria(Integer id, String nome, Status status) {
        this.id = id;
        this.nome = nome;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Status getStatus() {
        return status;
    }

    public enum Status {
        ATIVA, INATIVA
    }
}
