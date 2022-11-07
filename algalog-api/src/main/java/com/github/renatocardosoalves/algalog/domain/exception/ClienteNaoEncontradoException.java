package com.github.renatocardosoalves.algalog.domain.exception;

public class ClienteNaoEncontradoException extends RecursoNaoEncontradoException {

    public ClienteNaoEncontradoException(Long clienteId) {
        super("O cliente com id %d não foi encontrado.", clienteId);
    }

}
