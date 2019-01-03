package hoami.java_validator.Validator;

import static hoami.java_validator.Validator.Rules.StringRuleBuilder.stringRule;
import static hoami.java_validator.Validator.ValidatorBuilder.rules;

import hoami.java_validator.Validator.Core.ErrorManager;
import hoami.java_validator.Validator.Messages.MessageFactory;

public class Test {
	public static void main(String[] args) {
		MessageFactory.create();
        String name = "ste";
        Validator nameValidator = rules(stringRule("name").notEmpty().minLength(5).notStartsWith("st").notMatches("ste")).build();
        ErrorManager errors = nameValidator.validate_error_manager(name);
        System.out.println(errors.getResult());
	}
}
