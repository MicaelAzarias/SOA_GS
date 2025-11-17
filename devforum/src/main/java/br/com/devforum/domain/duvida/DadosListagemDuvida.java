package br.com.devforum.domain.duvida;

import br.com.devforum.domain.StatusDuvida;

import java.time.LocalDateTime;

public record DadosListagemDuvida(
        Long id,
        String nomeAutor,
        String linguagem,
        String titulo,
        StatusDuvida status,
        LocalDateTime dataCriacao,
        int totalRespostas
) {
    public DadosListagemDuvida(Duvida duvida) {
        this(
                duvida.getId(),
                duvida.getNomeAutor(),
                duvida.getLinguagem(),
                duvida.getTitulo(),
                duvida.getStatus(),
                duvida.getDataCriacao(),
                duvida.getRespostas().size()
        );
    }
}