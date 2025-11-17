package br.com.devforum.controller;

import br.com.devforum.domain.StatusDuvida;
import br.com.devforum.domain.duvida.DadosAtualizacaoDuvida;
import br.com.devforum.domain.duvida.DadosCadastroDuvida;
import br.com.devforum.domain.duvida.DadosDetalhesDuvida;
import br.com.devforum.domain.duvida.DadosListagemDuvida;
import br.com.devforum.domain.resposta.DadosCadastroResposta;
import br.com.devforum.domain.resposta.DadosListagemResposta;
import br.com.devforum.service.DuvidaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/duvidas")
public class DuvidaController {

        @Autowired
        private DuvidaService service;

        @PostMapping
        public ResponseEntity<DadosListagemDuvida> cadastrarDuvida(
                @RequestBody @Valid DadosCadastroDuvida dados,
                UriComponentsBuilder uriBuilder
        ) {
                DadosListagemDuvida duvida = service.cadastrarDuvida(dados);
                URI uri = uriBuilder.path("/duvidas/{id}").buildAndExpand(duvida.id()).toUri();
                return ResponseEntity.created(uri).body(duvida);
        }

        @GetMapping
        public ResponseEntity<Page<DadosListagemDuvida>> listarDuvidas(
                @PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable pageable,
                @RequestParam(required = false) StatusDuvida status
        ) {
                Page<DadosListagemDuvida> pagina = service.listar(pageable, status);
                return ResponseEntity.ok(pagina);
        }

        @GetMapping("/{id}")
        public ResponseEntity<DadosDetalhesDuvida> detalharDuvida(@PathVariable Long id) {
                DadosDetalhesDuvida detalhes = service.detalhar(id);
                return ResponseEntity.ok(detalhes);
        }

        @PutMapping
        public ResponseEntity<DadosListagemDuvida> atualizarDuvida(
                @RequestBody @Valid DadosAtualizacaoDuvida dados
        ) {
                DadosListagemDuvida duvida = service.atualizarDuvida(dados);
                return ResponseEntity.ok(duvida);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> excluirDuvida(@PathVariable Long id) {
                service.excluirDuvida(id);
                return ResponseEntity.noContent().build();
        }

        @PatchMapping("/{id}/fechar")
        public ResponseEntity<Void> fecharDuvida(@PathVariable Long id) {
                service.fechar(id);
                return ResponseEntity.noContent().build();
        }

        // --- Endpoints de Resposta ---

        @PostMapping("/{idDuvida}/respostas")
        public ResponseEntity<DadosListagemResposta> cadastrarResposta(
                @PathVariable Long idDuvida,
                @RequestBody @Valid DadosCadastroResposta dados,
                UriComponentsBuilder uriBuilder
        ) {
                DadosListagemResposta resposta = service.cadastrarResposta(idDuvida, dados);
                // A URI da resposta poderia ser /respostas/{id}
                URI uri = uriBuilder.path("/respostas/{id}").buildAndExpand(resposta.id()).toUri();
                return ResponseEntity.created(uri).body(resposta);
        }
}