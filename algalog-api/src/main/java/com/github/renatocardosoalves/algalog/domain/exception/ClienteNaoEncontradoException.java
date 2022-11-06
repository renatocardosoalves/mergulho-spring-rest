package com.github.renatocardosoalves.algalog.domain.exception;

public class ClienteNaoEncontradoException extends RuntimeException {

    public ClienteNaoEncontradoException(Long clienteId) {
        super(String.format("O cliente com id %d não foi encontrado.", clienteId));
    }

}
