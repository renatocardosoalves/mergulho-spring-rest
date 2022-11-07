package com.github.renatocardosoalves.algalog.api.exception;

import com.github.renatocardosoalves.algalog.domain.exception.ClienteNaoEncontradoException;
import com.github.renatocardosoalves.algalog.domain.exception.NegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var campos = this.getCampos(ex);
        var problema = this.criarProblema(status, "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.", campos);
        return super.handleExceptionInternal(ex, problema, headers, status, request);
    }

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<Object> handleClienteNaoEncontradoException(ClienteNaoEncontradoException ex, WebRequest request) {
        return this.lancarProblema(HttpStatus.NOT_FOUND, ex, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocioException(NegocioException ex, WebRequest request) {
        return this.lancarProblema(HttpStatus.BAD_REQUEST, ex, request);
    }

    private List<Campo> getCampos(BindException ex) {
        return ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> new Campo(((FieldError) error).getField(), this.messageSource.getMessage(error, LocaleContextHolder.getLocale())))
                .toList();
    }

    private Problema criarProblema(HttpStatus status, String titulo, List<Campo> campos) {
        return Problema.builder()
                .comStatus(status.value())
                .comDataHora(LocalDateTime.now())
                .comTitulo(titulo)
                .comCampos(campos)
                .build();
    }

    private ResponseEntity<Object> lancarProblema(HttpStatus status, Exception ex, WebRequest request) {
        var problema = this.criarProblema(status, ex.getMessage(), null);
        return super.handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

}
