package br.com.desafio.urbana_pe.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessagemDTO {
    
    private String messagem;
    private String campo;
}
