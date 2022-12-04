package br.com.alura.clientelo.controller;

import br.com.alura.clientelo.controller.AdvicerController.APIErrorMessage.APIErrorItem;
import br.com.alura.clientelo.controller.categoria.CategoriaNaoEncontradaException;
import br.com.alura.clientelo.controller.pedido.exception.ClienteNotFoundException;
import br.com.alura.clientelo.controller.pedido.exception.ItensForaDeEstoqueException;
import br.com.alura.clientelo.controller.pedido.exception.ItensForaDeEstoqueException.ItemForaDeEstoque;
import br.com.alura.clientelo.controller.pedido.exception.PedidoNaoExisteException;
import br.com.alura.clientelo.controller.produto.exception.ProdutoNaoEncontradoException;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AdvicerController {

    @ExceptionHandler(ItensForaDeEstoqueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIErrorMessage handleItensForaDeEstoqueException(ItensForaDeEstoqueException ex) {
        List<APIErrorItem> errors = ex.getPedidos().stream()
                .map(er -> new APIErrorItem(er.getNome(), er.getQuantidade().toString(), "Produto inexistente"))
                .collect(Collectors.toList());
        return new APIErrorMessage(ex.getMessage(), null, errors);
    }

    @ExceptionHandler(CategoriaNaoEncontradaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIErrorMessage handleCategoriaNaoEncontradaException(CategoriaNaoEncontradaException ex) {
        return new APIErrorMessage(ex.getMessage(), null, null);
    }

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIErrorMessage handleProdutoNaoEncontradoException(ProdutoNaoEncontradoException ex) {
        return new APIErrorMessage(ex.getMessage(), null, null);
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIErrorMessage handleClienteNotFoundException(ClienteNotFoundException ex) {
        return new APIErrorMessage(ex.getMessage(), null, null);
    }

    @ExceptionHandler(PedidoNaoExisteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIErrorMessage handlePedidoNaoExisteException(PedidoNaoExisteException ex) {
        return new APIErrorMessage(ex.getMessage(), null, null);
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class APIErrorMessage<T> {
        private String mensagem;
        private String descricao;
        private List<APIErrorItem> erros;

        public APIErrorMessage(String mensagem, String descricao, List<APIErrorItem> erros) {
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

        public List<APIErrorItem> getErros() {
            return erros;
        }

        static class APIErrorItem {
            private String item;
            private String valor;
            private String message;

            public APIErrorItem(String item, String valor, String message) {
                this.item = item;
                this.valor = valor;
                this.message = message;
            }

            public String getItem() {
                return item;
            }

            public String getValor() {
                return valor;
            }

            public String getMessage() {
                return message;
            }
        }
    }
}
