package dev.jgvictors.JustACrud.service;

import dev.jgvictors.JustACrud.exceptions.RegistroFuncionarioAlreadyExistException;
import dev.jgvictors.JustACrud.exceptions.RegistroFuncionarioNotFoundException;
import dev.jgvictors.JustACrud.model.Funcionario;
import dev.jgvictors.JustACrud.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> getAll() {
        return funcionarioRepository.findAll();
    }

    public void create(Funcionario funcionario) throws RegistroFuncionarioAlreadyExistException {
        if (funcionarioRepository.findById(funcionario.getRegistro()).isPresent()) {
            throw new RegistroFuncionarioAlreadyExistException(funcionario.getRegistro());
        }
        funcionarioRepository.save(funcionario);
    }

    public Funcionario get(Long registro) throws RegistroFuncionarioNotFoundException {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(registro);
        if (funcionario.isEmpty()) {
            throw new RegistroFuncionarioNotFoundException(registro);
        }
        return funcionario.get();
    }

    public void update(Funcionario funcionario) throws RegistroFuncionarioNotFoundException {
        if (funcionarioRepository.findById(funcionario.getRegistro()).isEmpty()) {
            throw new RegistroFuncionarioNotFoundException(funcionario.getRegistro());
        }
        funcionarioRepository.save(funcionario);
    }

    public void delete(Long registro) throws RegistroFuncionarioNotFoundException {
        if (funcionarioRepository.findById(registro).isEmpty()) {
            throw new RegistroFuncionarioNotFoundException(registro);
        }
        funcionarioRepository.deleteById(registro);
    }

}
