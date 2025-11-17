package br.com.devforum.domain.resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DadosCadastroResposta(
        @NotBlank(message = "O nome do autor é obrigatório.")
        @Size(max = 100, message = "O nome do autor deve ter no máximo 100 caracteres.")
        String nomeAutor,

        @NotBlank(message = "O corpo da resposta é obrigatório.")
        String corpoResposta
) {}