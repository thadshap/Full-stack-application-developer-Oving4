package no.ntnu.oving4;

import no.ntnu.oving4.model.Calculation;
import no.ntnu.oving4.repo.CalculationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Oving4Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Oving4Application.class, args);
	}

	@Autowired
	private CalculationRepo calculationRepo;

	@Autowired
	private CalculationRepo repository;
	@Override
	public void run(String... args) throws Exception {

			// Creating 3 mock-expressions for testing, as well as to have some data in db automatically
			Calculation expression1 = Calculation.builder()
					.equation("2+2")
					.answer(4.0)
					.build();

			Calculation expression2 = Calculation.builder()
					.equation("22-7")
					.answer(15.0)
					.build();

			Calculation expression3 = Calculation.builder()
					.equation("1-7")
					.answer(-6.0)
					.build();

			// Calling save method from Jpa in order to send the expressions to the db (POST)
			repository.save(expression1);
			repository.save(expression2);
			repository.save(expression3);

	}
}
