package med.manfrin.api.controllers;

import jakarta.validation.Valid;
import med.manfrin.api.dtos.consulta.DadosAgendamentoConsulta;
import med.manfrin.api.service.consulta.AgendamentoConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private AgendamentoConsultaService agendamentoConsultaService;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados){
        agendamentoConsultaService.agendar(dados);
        return ResponseEntity.ok(dados);
    }
}
