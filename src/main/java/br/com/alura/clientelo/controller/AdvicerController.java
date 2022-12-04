package br.com.alura.clientelo.controller;

import br.com.alura.clientelo.controller.pedido.exception.ItensForaDeEstoqueException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class AdvicerController {

    @ExceptionHandler(ItensForaDeEstoqueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIErrorMessage handleItensForaDeEstoqueException(ItensForaDeEstoqueException ex) {
        return new APIErrorMessage(ex.getMessage(), null, ex.getPedidos());
    }

    public static class APIErrorMessage<T> {
        private String mensagem;
        private String descricao;
        private List<T> erros;

        public APIErrorMessage(String mensagem, String descricao, List<T> erros) {
            this.mensagem = mensagem;
            this.descricao = descricao;
            this.erros = erros;
        }

        public String getMensagem() {
            return mensagem;
        }

        public String getDescricao() {
            return descricao;
        }

        public List<T> getErros() {
            return erros;
        }
    }
}
