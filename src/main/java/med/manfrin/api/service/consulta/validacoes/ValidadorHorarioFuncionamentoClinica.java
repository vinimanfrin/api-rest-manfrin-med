package med.manfrin.api.service.consulta.validacoes;

import med.manfrin.api.dtos.consulta.DadosAgendamentoConsulta;
import med.manfrin.api.infra.exception.ValidacaoAgendamentoException;

import java.time.DayOfWeek;

public class ValidadorHorarioFuncionamentoClinica {

    public void validar (DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAbertudaDaClinica = dataConsulta.getHour() < 7;
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;
        if (domingo || antesDaAbertudaDaClinica || depoisDoEncerramentoDaClinica){
            throw new ValidacaoAgendamentoException("Consulta fora do horário de funcionamento da clínica");
        }
    }
}
