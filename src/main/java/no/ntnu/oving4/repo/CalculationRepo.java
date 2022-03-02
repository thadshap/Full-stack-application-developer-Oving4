package no.ntnu.oving4.repo;

import no.ntnu.oving4.model.Calculation;
import no.ntnu.oving4.service.CalculationService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationRepo extends JpaRepository<Calculation, Long> {
}
