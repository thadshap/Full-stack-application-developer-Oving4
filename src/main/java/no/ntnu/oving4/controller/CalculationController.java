package no.ntnu.oving4.controller;
import no.ntnu.oving4.dto.CalculationDto;
import no.ntnu.oving4.model.Calculation;
import no.ntnu.oving4.repo.CalculationRepo;
import no.ntnu.oving4.service.CalculationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8082/")
public class CalculationController {
    @Autowired
    private CalculationRepo calculationRepo;
    @Autowired
    private CalculationService calculationService;

    Logger logger = LoggerFactory.getLogger(CalculationController.class);

    @GetMapping("/calculations")
    public List<Calculation> fetchCalculation(){
        return calculationRepo.findAll();
    }

    @GetMapping("/calculation/{id}")
    public Calculation fetchElementById(@PathVariable("id") long id){
        System.out.println("Id: " + id);

        return calculationRepo.getById(id);
    }

    @PostMapping(path = "/calculations",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Calculation postExpression(@RequestBody CalculationDto mathExpression) {
        String answer = calculationService.getAnswer(mathExpression.getEquation());

        Calculation newExpression = new Calculation();

        newExpression.setEquation(mathExpression.getEquation());
        newExpression.setAnswer(Double.parseDouble(answer));
        logger.info("The answer has been calculated and saved in the attribute 'answer'=" + newExpression.getAnswer());

        calculationRepo.save(newExpression);
        logger.info("An expression has been saved in the mock database");
        return newExpression;
    }
}
