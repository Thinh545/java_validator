package hoami.java_validator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import hoami.java_validator.Validator.Validator;
import hoami.java_validator.Validator.Annotations.ReadConstraints;

import static hoami.java_validator.Validator.ValidatorBuilder.*;

import java.lang.reflect.Method;

import hoami.java_validator.Validator.Core.Selector;
import hoami.java_validator.Validator.Core.ErrorManager;
import hoami.java_validator.Validator.Messages.MessageFactory;
import static hoami.java_validator.Validator.Rules.StringRuleBuilder.*;

@Controller
public class HelloWorldController {
 
    @RequestMapping("/hello")
    public String hello(Model model) {
        
        model.addAttribute("greeting", "Hello Spring MVC");
                
        MessageFactory.create();
        String name = "ste";
        Validator nameValidator = rules(stringRule("name").notEmpty().minLength(5).notStartsWith("st").notMatches("ste")).build();
        ErrorManager errors = nameValidator.validate_error_manager(name);
        System.out.println(errors.getResult());
        
        return "helloworld";
    }	
}