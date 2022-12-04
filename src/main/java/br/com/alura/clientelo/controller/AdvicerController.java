package br.com.alura.clientelo.controller;

import br.com.alura.clientelo.controller.categoria.CategoriaNaoEncontradaException;
import br.com.alura.clientelo.controller.pedido.exception.ClienteNotFoundException;
import br.com.alura.clientelo.controller.pedido.exception.ItensForaDeEstoqueException;
import br.com.alura.clientelo.controller.pedido.exception.PedidoNaoExisteException;
import br.com.alura.clientelo.controller.produto.exception.ProdutoNaoEncontradoException;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class AdvicerController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ArrayList<String> erros = new ArrayList<>();
        for (ObjectError errors : ex.getAllErrors()) {
            erros.add(errors.getDefaultMessage());
        }

        APIErrorMessage errorMessage = new APIErrorMessage(ex.getMessage(), erros);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ItensForaDeEstoqueException.class)
    public ResponseEntity<APIErrorMessage> handleItensForaDeEstoqueException(ItensForaDeEstoqueException ex) {
        return new ResponseEntity<>(new APIErrorMessage(ex.getMessage(), ex.getPedidos()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoriaNaoEncontradaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<APIErrorMessage> handleCategoriaNaoEncontradaException(CategoriaNaoEncontradaException ex) {
        return new ResponseEntity<>(new APIErrorMessage(ex.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<APIErrorMessage> handleProdutoNaoEncontradoException(ProdutoNaoEncontradoException ex) {
        return new ResponseEntity<>(new APIErrorMessage(ex.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<APIErrorMessage> handleClienteNotFoundException(ClienteNotFoundException ex) {
        return new ResponseEntity<>(new APIErrorMessage(ex.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PedidoNaoExisteException.class)
    public ResponseEntity<APIErrorMessage> handlePedidoNaoExisteException(PedidoNaoExisteException ex) {
        return new ResponseEntity<>(new APIErrorMessage(ex.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class APIErrorMessage<T> {

        private String message;
        private List<String> detalhes;

        public APIErrorMessage(String message, List<String> detalhes) {
            this.message = message;
            this.detalhes = detalhes;
        }

        public String getMessage() {
            return message;
        }

        public List<String> getDetalhes() {
            return detalhes;
        }
    }
}
