package com.github.renatocardosoalves.algalog.api.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var campos = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> new Campo(((FieldError) error).getField(), this.messageSource.getMessage(error, LocaleContextHolder.getLocale())))
                .toList();
        var problema = Problema.builder()
                .comStatus(status.value())
                .comDataHora(LocalDateTime.now())
                .comTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
                .comCampos(campos)
                .build();
        return super.handleExceptionInternal(ex, problema, headers, status, request);
    }

}
