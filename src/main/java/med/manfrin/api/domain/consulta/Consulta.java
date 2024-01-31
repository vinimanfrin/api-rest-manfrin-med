package med.manfrin.api.domain.consulta;

import jakarta.persistence.*;
import lombok.*;
import med.manfrin.api.domain.medico.Medico;
import med.manfrin.api.domain.paciente.Paciente;
import med.manfrin.api.dtos.consulta.DadosAgendamentoConsulta;

import java.time.LocalDateTime;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime data;

}
