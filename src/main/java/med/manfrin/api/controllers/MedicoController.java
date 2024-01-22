package med.manfrin.api.controllers;

import jakarta.validation.Valid;
import med.manfrin.api.domain.medico.Medico;
import med.manfrin.api.dtos.medico.DadosCadastroMedico;
import med.manfrin.api.dtos.medico.DadosListagemMedico;
import med.manfrin.api.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
    }

    @GetMapping
    public List<DadosListagemMedico> listarTodos(){
        return repository.findAll().stream().map(DadosListagemMedico::new).toList();
    }
}
