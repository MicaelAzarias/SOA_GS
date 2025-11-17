package br.com.devforum.service;

import br.com.devforum.domain.StatusDuvida;
import br.com.devforum.domain.duvida.*;
import br.com.devforum.domain.resposta.DadosCadastroResposta;
import br.com.devforum.domain.resposta.DadosListagemResposta;
import br.com.devforum.domain.resposta.Resposta;
import br.com.devforum.domain.resposta.RespostaRepository;
import br.com.devforum.infra.exception.RegraDeNegocioException;
import br.com.devforum.infra.mail.EmailService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DuvidaService {

    @Autowired
    private DuvidaRepository duvidaRepository;

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private EmailService emailService;

    /**
     * Cadastra uma nova dúvida.
     */
    @Transactional
    public DadosListagemDuvida cadastrarDuvida(DadosCadastroDuvida dados) {
        Duvida duvida = new Duvida(dados);
        duvidaRepository.save(duvida);
        return new DadosListagemDuvida(duvida);
    }

    /**
     * Cadastra uma nova resposta para uma dúvida.
     */
    @Transactional
    public DadosListagemResposta cadastrarResposta(Long idDuvida, DadosCadastroResposta dados) {
        Duvida duvida = buscarDuvidaPorId(idDuvida);

        if (duvida.getStatus() == StatusDuvida.FECHADA) {
            throw new RegraDeNegocioException("Não é possível adicionar respostas a uma dúvida fechada.");
        }

        Resposta resposta = new Resposta(dados, duvida);
        duvida.adicionarResposta(resposta); // O método adicionarResposta já atualiza o status

        respostaRepository.save(resposta);
        // O save da 'duvida' é gerenciado pelo JPA (dirty checking) devido ao @Transactional
        // Envia e-mail de notificação para o autor da dúvida (de forma assíncrona)
        String emailAutorDuvida = "email.do.autor@exemplo.com"; // Simulação
        emailService.enviarEmailNotificacao(
                emailAutorDuvida,
                "Sua dúvida recebeu uma nova resposta!",
                "A sua dúvida '" + duvida.getTitulo() + "' foi respondida por " + resposta.getNomeAutor() + "."
        );

        return new DadosListagemResposta(resposta);
    }

    /**
     * Lista todas as dúvidas (paginadas).
     */
    public Page<DadosListagemDuvida> listar(Pageable pageable, StatusDuvida status) {
        if (status != null) {
            return duvidaRepository.findByStatus(status, pageable).map(DadosListagemDuvida::new);
        }
        return duvidaRepository.findAll(pageable).map(DadosListagemDuvida::new);
    }

    /**
     * Detalha uma dúvida específica com suas respostas.
     */
    public DadosDetalhesDuvida detalhar(Long id) {
        Duvida duvida = buscarDuvidaPorId(id);
        List<DadosListagemResposta> respostas = respostaRepository.findByDuvidaId(id)
                .stream()
                .map(DadosListagemResposta::new)
                .toList();
        return new DadosDetalhesDuvida(duvida, respostas);
    }

    /**
     * Fecha uma dúvida.
     */
    @Transactional
    public void fechar(Long id) {
        Duvida duvida = buscarDuvidaPorId(id);
        duvida.fechar();
    }

    /**
     * Atualiza os dados de uma dúvida.
     */
    @Transactional
    public DadosListagemDuvida atualizarDuvida(DadosAtualizacaoDuvida dados) {
        Duvida duvida = buscarDuvidaPorId(dados.id());
        duvida.atualizar(dados);
        return new DadosListagemDuvida(duvida);
    }

    /**
     * Atualiza o corpo de uma resposta.
     */
    @Transactional
    public DadosListagemResposta atualizarResposta(Long idResposta, String novoCorpo) {
        Resposta resposta = buscarRespostaPorId(idResposta);
        resposta.atualizar(novoCorpo);
        return new DadosListagemResposta(resposta);
    }

    /**
     * Exclui uma dúvida
     */
    @Transactional
    public void excluirDuvida(Long id) {
        if (!duvidaRepository.existsById(id)) {
            throw new EntityNotFoundException("Dúvida não encontrada.");
        }
        duvidaRepository.deleteById(id);
    }

    /**
     * Exclui uma resposta.
     */
    @Transactional
    public void excluirResposta(Long id) {
        if (!respostaRepository.existsById(id)) {
            throw new EntityNotFoundException("Resposta não encontrada.");
        }
        // Lógica adicional: Se esta for a última resposta, talvez o status da dúvida deva voltar para 'ABERTA'?
        // Por simplicidade, vamos apenas excluir.
        respostaRepository.deleteById(id);
    }



    private Duvida buscarDuvidaPorId(Long id) {
        return duvidaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dúvida com ID " + id + " não encontrada."));
    }

    private Resposta buscarRespostaPorId(Long id) {
        return respostaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Resposta com ID " + id + " não encontrada."));
    }
}