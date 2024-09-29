package br.com.desafio.urbana_pe.modules.cartao.models;

import java.util.UUID;

import br.com.desafio.urbana_pe.modules.usuario.models.UsuarioEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity(name = "cartao")
@Data
public class CartaoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "numero", nullable = false, unique = true)
    @NotBlank(message = "Não pode ser vázio!")
    private String numero;

    @Column(name = "nome", nullable = false)
    @NotBlank(message = "Não pode ser vázio!")
    private String nome;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "tipo", nullable = false)
    @NotNull(message = "Informe o tipo do cartão!")
    @Enumerated(EnumType.STRING)
    private CartaoTipo cartaoTipo;

    @ManyToOne
    @JoinColumn(name = "usuario_id", insertable = false, updatable = false)
    private UsuarioEntity usuarioEtity;

    @Column(name = "usuario_id", nullable = false)
    private UUID usuarioId;
}
