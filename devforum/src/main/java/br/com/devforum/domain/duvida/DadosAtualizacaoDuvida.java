package br.com.devforum.domain.duvida;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DadosAtualizacaoDuvida(
        @NotNull
        Long id,

        @Size(max = 100, message = "A linguagem deve ter no máximo 100 caracteres.")
        String linguagem,

        @Size(max = 255, message = "O título deve ter no máximo 255 caracteres.")
        String titulo,

        String corpoDuvida
) {}