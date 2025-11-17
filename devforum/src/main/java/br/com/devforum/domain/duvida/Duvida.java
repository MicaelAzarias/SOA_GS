package br.com.devforum.domain.duvida;

import br.com.devforum.domain.StatusDuvida;
import br.com.devforum.domain.resposta.Resposta;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "duvidas")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Duvida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeAutor;
    private String linguagem;
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String corpoDuvida;

    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    private StatusDuvida status;

    @OneToMany(mappedBy = "duvida", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resposta> respostas = new ArrayList<>();

    public Duvida(DadosCadastroDuvida dados) {
        this.nomeAutor = dados.nomeAutor();
        this.linguagem = dados.linguagem();
        this.titulo = dados.titulo();
        this.corpoDuvida = dados.corpoDuvida();
        this.dataCriacao = LocalDateTime.now();
        this.status = StatusDuvida.ABERTA;
    }

    /**
     * Adiciona uma resposta e atualiza o status da dúvida, se for a primeira resposta.
     */
    public void adicionarResposta(Resposta resposta) {
        this.respostas.add(resposta);
        if (this.status == StatusDuvida.ABERTA) {
            this.status = StatusDuvida.RESPONDIDA;
        }
    }

    /**
     * Fecha a dúvida.
     */
    public void fechar() {
        this.status = StatusDuvida.FECHADA;
    }

    /**
     * Atualiza os dados da dúvida.
     */
    public void atualizar(DadosAtualizacaoDuvida dados) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.corpoDuvida() != null) {
            this.corpoDuvida = dados.corpoDuvida();
        }
        if (dados.linguagem() != null) {
            this.linguagem = dados.linguagem();
        }
    }
}