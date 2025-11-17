package br.com.devforum.domain.duvida;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DadosCadastroDuvida(
        @NotBlank(message = "O nome do autor é obrigatório.")
        @Size(max = 100, message = "O nome do autor deve ter no máximo 100 caracteres.")
        String nomeAutor,

        @NotBlank(message = "A linguagem ou tag é obrigatória.")
        @Size(max = 100, message = "A linguagem deve ter no máximo 100 caracteres.")
        String linguagem,

        @NotBlank(message = "O título é obrigatório.")
        @Size(max = 255, message = "O título deve ter no máximo 255 caracteres.")
        String titulo,

        @NotBlank(message = "O corpo da dúvida é obrigatório.")
        String corpoDuvida
) {}