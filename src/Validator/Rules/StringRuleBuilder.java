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

public class StringRuleBuilder<F extends StringRuleBuilder<F>> extends ComparableRuleBuilder<String, F> {

    protected StringRuleBuilder(ParametrizedRules<String> parameterRule) {
        super(parameterRule);
    }

    public F empty() {
        return addRule(String::isEmpty);
    }

    public F notEmpty() {
        return addRule(t -> !t.isEmpty());
    }

    public F startsWith(String parameter) {
        return addRule(t -> t.startsWith(parameter));
    }

    public F notStartsWith(String parameter) {
        return addRule(t -> !t.startsWith(parameter));
    }

    public F endsWith(String parameter) {
        return addRule(t -> t.endsWith(parameter));
    }

    public F notEnds(String parameter) {
        return addRule(t -> !t.endsWith(parameter));
    }

    public F contains(CharSequence parameter) {
        return addRule(t -> t.contains(parameter));
    }

    public F notContains(CharSequence parameter) {
        return addRule(t -> !t.contains(parameter));
    }

    public F matches(String parameter) {
        return addRule(t -> t.matches(parameter));
    }

    public F notMatches(String parameter) {
        return addRule(t -> !t.matches(parameter));
    }

    public F length(Integer parameter) {
        return addRule(t -> t.length() == parameter);
    }

    public F minLength(Integer parameter) {
        return addRule(t -> t.length() >= parameter);
    }

    public F maxLength(Integer parameter) {
        return addRule(t -> t.length() <= parameter);
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
