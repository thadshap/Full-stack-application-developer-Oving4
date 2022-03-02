package no.ntnu.oving4.controller;
import no.ntnu.oving4.dto.CalculationDto;
import no.ntnu.oving4.model.Calculation;
import no.ntnu.oving4.repo.CalculationRepo;
import no.ntnu.oving4.service.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8081/")
public class CalculationController {
    @Autowired
    private CalculationRepo calculationRepo;
    @Autowired
    private CalculationService calculationService;

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

        calculationRepo.save(newExpression);
        return newExpression;
    }
}
