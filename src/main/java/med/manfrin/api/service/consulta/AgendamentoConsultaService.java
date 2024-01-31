package med.manfrin.api.service.consulta;

import med.manfrin.api.domain.consulta.Consulta;
import med.manfrin.api.domain.medico.Medico;
import med.manfrin.api.dtos.consulta.DadosAgendamentoConsulta;
import med.manfrin.api.infra.exception.ValidacaoAgendamentoException;
import med.manfrin.api.repositories.ConsultaRepository;
import med.manfrin.api.repositories.MedicoRepository;
import med.manfrin.api.repositories.PacienteRepository;
import med.manfrin.api.service.consulta.validacoes.ValidadorAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoConsulta> validadores;


    public void agendar(DadosAgendamentoConsulta dados){
        if (!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoAgendamentoException("Id do paciente informado não existe");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoAgendamentoException("Id do médico informado não existe");
        }

        validadores.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        var consulta = new Consulta(null,medico,paciente,dados.data());
        consultaRepository.save(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico()!= null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null){
            throw new ValidacaoAgendamentoException("Especialidade é obrigatória quando o médico nao for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(),dados.data());

    }
}
