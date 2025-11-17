package br.com.devforum.domain.resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoResposta(
        @NotNull
        Long id,

        @NotBlank(message = "O corpo da resposta não pode estar em branco.")
        String corpoResposta
) {
}