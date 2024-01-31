package med.manfrin.api.service.consulta.validacoes;

import med.manfrin.api.dtos.consulta.DadosAgendamentoConsulta;
import med.manfrin.api.infra.exception.ValidacaoAgendamentoException;
import med.manfrin.api.repositories.ConsultaRepository;

public class ValidadorMedicoOcupado {

    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var medicoOcupado = repository.existsByMedicoIdAndData(dados.idMedico(),dados.data());
        if (medicoOcupado){
            throw new ValidacaoAgendamentoException("Médico já possui outra consulta agendada nesse mesmo horário");
        }
    }
}
