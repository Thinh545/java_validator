/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator.Rules;

/**
 *
 * @author thinhnh
 */
import Validator.Core.ParametrizedRules;
import Validator.Messages.MessageContainer;

public class StringRuleBuilder<F extends StringRuleBuilder<F>> extends ComparableRuleBuilder<String, F> {

    protected StringRuleBuilder(ParametrizedRules<String> parameterRule) {
        super(parameterRule);
    }

    public F empty() {
        return addRule(String::isEmpty, MessageContainer.empty);
    }

    public F notEmpty() {
        return addRule(t -> !t.isEmpty(), MessageContainer.notEmpty);
    }

    public F startsWith(String parameter) {
        String message = MessageContainer.startsWith + parameter;
        return addRule(t -> t.startsWith(parameter), message);
    }

    public F notStartsWith(String parameter) {
        String message = MessageContainer.notStartsWith + parameter;
        return addRule(t -> !t.startsWith(parameter), message);
    }

    public F endsWith(String parameter) {
        String message = MessageContainer.endsWith + parameter;
        return addRule(t -> t.endsWith(parameter), message);
    }

    public F notEnds(String parameter) {
        String message = MessageContainer.notEnds + parameter;
        return addRule(t -> !t.endsWith(parameter), message);
    }

    public F contains(CharSequence parameter) {
        String message = MessageContainer.contains + parameter.toString();
        return addRule(t -> t.contains(parameter), message);
    }

    public F notContains(CharSequence parameter) {
        String message = MessageContainer.notContains + parameter.toString();
        return addRule(t -> !t.contains(parameter), message);
    }

    public F matches(String parameter) {
        String message = MessageContainer.matches + parameter;
        return addRule(t -> t.matches(parameter), message);
    }

    public F notMatches(String parameter) {
        String message = MessageContainer.notMatches + parameter;
        return addRule(t -> !t.matches(parameter), message);
    }

    public F length(Integer parameter) {
        String message = MessageContainer.length + parameter.toString();
        return addRule(t -> t.length() == parameter, message);
    }

    public F minLength(Integer parameter) {
        String message = MessageContainer.minLength + parameter.toString();
        return addRule(t -> t.length() >= parameter, message);
    }

    public F maxLength(Integer parameter) {
        String message = MessageContainer.maxLength + parameter.toString();
        return addRule(t -> t.length() <= parameter, message);
    }

    public static final class StringRuleBuilderBase extends StringRuleBuilder<StringRuleBuilderBase> {

        private StringRuleBuilderBase(ParametrizedRules<String> parameterRule) {
            super(parameterRule);
        }
    }

    public static StringRuleBuilderBase stringRule(String name) {
        return new StringRuleBuilderBase(new ParametrizedRules<>(name, String.class));
    }
}
