package br.com.devforum.domain.duvida;

import br.com.devforum.domain.StatusDuvida;
import br.com.devforum.domain.resposta.DadosListagemResposta;

import java.time.LocalDateTime;
import java.util.List;

public record DadosDetalhesDuvida(
        Long id,
        String nomeAutor,
        String linguagem,
        String titulo,
        String corpoDuvida,
        StatusDuvida status,
        LocalDateTime dataCriacao,
        List<DadosListagemResposta> respostas
) {
    public DadosDetalhesDuvida(Duvida duvida, List<DadosListagemResposta> respostas) {
        this(
                duvida.getId(),
                duvida.getNomeAutor(),
                duvida.getLinguagem(),
                duvida.getTitulo(),
                duvida.getCorpoDuvida(),
                duvida.getStatus(),
                duvida.getDataCriacao(),
                respostas
        );
    }
}