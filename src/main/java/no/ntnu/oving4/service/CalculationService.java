package no.ntnu.oving4.service;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("/api")
public class CalculationService {

    public String getAnswer (String e){
        Expression expression = new ExpressionBuilder(e).build();
        return String.valueOf(expression.evaluate());
    }
}
