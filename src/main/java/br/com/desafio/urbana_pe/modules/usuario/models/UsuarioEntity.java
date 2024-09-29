package br.com.desafio.urbana_pe.modules.usuario.models;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity(name = "usuario")
@Data
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", nullable = false)
    @NotBlank(message = "Não pode ser vázio!")
    private String nome;

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = "Não pode ser vázio!")
    @Email(message = "Informe um email válido!")
    private String email;

    @Column(name = "senha", nullable = false)
    @NotBlank(message = "Não pode ser vázio!")
    @Length(min = 5, message = "A senha dever ter um nó minimo 10!")
    private String senha;

}
