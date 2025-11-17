package br.com.devforum.domain.resposta;

import java.time.LocalDateTime;

public record DadosListagemResposta(
        Long id,
        String nomeAutor,
        String corpoResposta,
        LocalDateTime dataCriacao
) {
    public DadosListagemResposta(Resposta resposta) {
        this(
                resposta.getId(),
                resposta.getNomeAutor(),
                resposta.getCorpoResposta(),
                resposta.getDataCriacao()
        );
    }
}