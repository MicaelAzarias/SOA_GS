package br.com.devforum.domain.resposta;

import br.com.devforum.domain.duvida.Duvida;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "respostas")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeAutor;

    @Column(columnDefinition = "TEXT")
    private String corpoResposta;

    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "duvida_id")
    private Duvida duvida;

    public Resposta(DadosCadastroResposta dados, Duvida duvida) {
        this.nomeAutor = dados.nomeAutor();
        this.corpoResposta = dados.corpoResposta();
        this.dataCriacao = LocalDateTime.now();
        this.duvida = duvida;
    }

    public void atualizar(String novoCorpo) {
        this.corpoResposta = novoCorpo;
    }
}