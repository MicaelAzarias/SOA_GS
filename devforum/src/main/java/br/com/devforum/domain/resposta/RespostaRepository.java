package br.com.devforum.domain.resposta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {
    // Busca todas as respostas de uma dúvida específica
    List<Resposta> findByDuvidaId(Long duvidaId);
}