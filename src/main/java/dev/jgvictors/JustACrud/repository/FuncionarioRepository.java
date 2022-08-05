package dev.jgvictors.JustACrud.repository;

import dev.jgvictors.JustACrud.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
