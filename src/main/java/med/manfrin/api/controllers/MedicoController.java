package med.manfrin.api.controllers;

import jakarta.validation.Valid;
import med.manfrin.api.domain.medico.Medico;
import med.manfrin.api.dtos.medico.DadosAtualizacaoMedico;
import med.manfrin.api.dtos.medico.DadosCadastroMedico;
import med.manfrin.api.dtos.medico.DadosDetalhamentoMedico;
import med.manfrin.api.dtos.medico.DadosListagemMedico;
import med.manfrin.api.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedico> cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder){
        var medico = new Medico(dados);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listarAtivos(@PageableDefault(size = 10,sort = {"nome"}) Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMedico> detalharMedico(@PathVariable Long id){
        var medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping("/todos")
    public ResponseEntity<Page<DadosDetalhamentoMedico>> listarTodos(@PageableDefault(size = 10,sort = {"nome"}) Pageable paginacao){
        var page = repository.findAll(paginacao).map(DadosDetalhamentoMedico::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedico> tornarAtivo(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.ativar();

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }
}
