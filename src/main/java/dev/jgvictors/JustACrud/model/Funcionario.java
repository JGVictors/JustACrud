package dev.jgvictors.JustACrud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Funcionario {

    @Id
    private Long registro;
    private String nome;
    private String email;
    private String telefone;
    private Float salario;

}
