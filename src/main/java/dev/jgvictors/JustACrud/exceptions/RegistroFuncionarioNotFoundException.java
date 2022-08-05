package dev.jgvictors.JustACrud.exceptions;

public class RegistroFuncionarioNotFoundException extends Throwable {

    public RegistroFuncionarioNotFoundException(Long registro) {
        super("Não foi possivel encontrar o funcionário com registro " + registro);
    }

}
