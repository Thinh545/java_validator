/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator_app;

/**
 *
 * @author thinhnh
 */
import Validator.Validator;
import static Validator.ValidatorBuilder.*;
import Validator.Core.Selector;
import Validator.Core.ErrorManager;
import Validator.Messages.MessageFactory;
import static Validator.Rules.StringRuleBuilder.*;

public class Validator_App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here 
        MessageFactory.create();
        String name = "ste";
        Validator nameValidator = rules(stringRule("name").notEmpty().minLength(5).notStartsWith("st").notMatches("ste")).build();
        ErrorManager errors = nameValidator.validate_error_manager(name);
        System.out.println(errors.getResult());
    }

}
