package med.manfrin.api.repositories;

import med.manfrin.api.domain.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta,Long> {
}
