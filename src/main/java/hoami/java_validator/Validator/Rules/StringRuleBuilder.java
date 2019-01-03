/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoami.java_validator.Validator.Rules;
import java.lang.reflect.Method;

import hoami.java_validator.Validator.Annotations.ReadConstraints;
/**
 *
 * @author thinhnh
 */
import hoami.java_validator.Validator.Core.ParametrizedRules;
import hoami.java_validator.Validator.Messages.MessageContainer;

public class StringRuleBuilder<F extends StringRuleBuilder<F>> extends ComparableRuleBuilder<String, F> {

    protected StringRuleBuilder(ParametrizedRules<String> parameterRule) {
        super(parameterRule);
    }
    
    @ReadConstraints(constraints = " use empty - ")
    public F empty() {
    	readConstraints("empty");
        return addRule(String::isEmpty, MessageContainer.empty);
    }

    @ReadConstraints(constraints = " use not empty - ")
    public F notEmpty() {
    	readConstraints("notEmpty");
        return addRule(t -> !t.isEmpty(), MessageContainer.notEmpty);
    }

    @ReadConstraints(constraints = " use start with - ")
    public F startsWith(String parameter) {
    	readConstraints("startsWith");
        String message = MessageContainer.startsWith + parameter;
        return addRule(t -> t.startsWith(parameter), message);
    }

    @ReadConstraints(constraints = " use not start with - ")
    public F notStartsWith(String parameter) {
    	readConstraints("notStartsWith");
        String message = MessageContainer.notStartsWith + parameter;
        return addRule(t -> !t.startsWith(parameter), message);
    }

    @ReadConstraints(constraints = " use ends with - ")
    public F endsWith(String parameter) {
    	readConstraints("endsWith");
        String message = MessageContainer.endsWith + parameter;
        return addRule(t -> t.endsWith(parameter), message);
    }

    @ReadConstraints(constraints = " use not end with - ")
    public F notEnds(String parameter) {
    	readConstraints("notEnds");
        String message = MessageContainer.notEnds + parameter;
        return addRule(t -> !t.endsWith(parameter), message);
    }

    @ReadConstraints(constraints = " use contains - ")
    public F contains(CharSequence parameter) {
    	readConstraints("contains");
        String message = MessageContainer.contains + parameter.toString();
        return addRule(t -> t.contains(parameter), message);
    }

    @ReadConstraints(constraints = " use not contains - ")
    public F notContains(CharSequence parameter) {
    	readConstraints("notContains");
        String message = MessageContainer.notContains + parameter.toString();
        return addRule(t -> !t.contains(parameter), message);
    }

    @ReadConstraints(constraints = " use matches - ")
    public F matches(String parameter) {
    	readConstraints("matches");
        String message = MessageContainer.matches + parameter;
        return addRule(t -> t.matches(parameter), message);
    }

    @ReadConstraints(constraints = " use not matches - ")
    public F notMatches(String parameter) {
    	readConstraints("notMatches");
        String message = MessageContainer.notMatches + parameter;
        return addRule(t -> !t.matches(parameter), message);
    }

    @ReadConstraints(constraints = " use length - ")
    public F length(Integer parameter) {
    	readConstraints("length");
        String message = MessageContainer.length + parameter.toString();
        return addRule(t -> t.length() == parameter, message);
    }

    @ReadConstraints(constraints = " use min length - ")
    public F minLength(Integer parameter) {
    	readConstraints("minLength");
        String message = MessageContainer.minLength + parameter.toString();
        return addRule(t -> t.length() >= parameter, message);
    }

    @ReadConstraints(constraints = " use max length - ")
    public F maxLength(Integer parameter) {
    	readConstraints("maxLength");
        String message = MessageContainer.maxLength + parameter.toString();
        return addRule(t -> t.length() <= parameter, message);
    }

    public static final class StringRuleBuilderBase extends StringRuleBuilder<StringRuleBuilderBase> {

        private StringRuleBuilderBase(ParametrizedRules<String> parameterRule) {
            super(parameterRule);
        }
    }

    @ReadConstraints(constraints = " use string rule - ")
    public static StringRuleBuilderBase stringRule(String name) {
    	readConstraints("stringRule");
    	
        return new StringRuleBuilderBase(new ParametrizedRules<>(name, String.class));
    }
    
    private static void readConstraints(String constraints){
    	@SuppressWarnings("rawtypes")
		Class<StringRuleBuilder> s = StringRuleBuilder.class;
		Method[] methods = s.getMethods(); 
        Method method = null; 
        for (Method m : methods) { 
            if (m.getName().equals(constraints)) {
            	 method = m; 
            	 break;
            }
        } 
        ReadConstraints a = method.getAnnotation(ReadConstraints.class);
        System.out.print(a.constraints());
    }
}
