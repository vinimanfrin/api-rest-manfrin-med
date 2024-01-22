package med.manfrin.api.repositories;

import med.manfrin.api.domain.medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico,Long> {
}
