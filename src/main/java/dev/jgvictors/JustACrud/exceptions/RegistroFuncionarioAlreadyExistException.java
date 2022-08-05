package dev.jgvictors.JustACrud.exceptions;

public class RegistroFuncionarioAlreadyExistException extends Throwable {

    public RegistroFuncionarioAlreadyExistException(Long registro) {
        super("Já foi encontrado um funcionário com registro " + registro);
    }

}
