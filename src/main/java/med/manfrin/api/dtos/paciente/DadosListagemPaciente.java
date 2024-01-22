package med.manfrin.api.dtos.paciente;

import med.manfrin.api.domain.paciente.Paciente;

public record DadosListagemPaciente(
        Long id,
        String nome,
        String email,
        String cpf
) {
    public DadosListagemPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
