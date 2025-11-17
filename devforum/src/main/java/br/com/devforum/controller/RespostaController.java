package br.com.devforum.controller;

import br.com.devforum.domain.resposta.DadosAtualizacaoResposta;
import br.com.devforum.domain.resposta.DadosListagemResposta;
import br.com.devforum.service.DuvidaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respostas")
public class RespostaController {

    @Autowired
    private DuvidaService service; // Reutilizando o service, pois ele já contém a lógica

    @PutMapping
    public ResponseEntity<DadosListagemResposta> atualizarResposta(
            @RequestBody @Valid DadosAtualizacaoResposta dados
    ) {
        DadosListagemResposta resposta = service.atualizarResposta(dados.id(), dados.corpoResposta());
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirResposta(@PathVariable Long id) {
        service.excluirResposta(id);
        return ResponseEntity.noContent().build();
    }
}