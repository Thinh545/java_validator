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

public class ComparableRuleBuilder<T extends Comparable, F extends ComparableRuleBuilder<T, F>>
        extends ParametrizedRuleBuilderBase<T, F> {

    protected ComparableRuleBuilder(ParametrizedRules<T> attributeRule) {
        super(attributeRule);
    }

    public F greatherThan(T parameter) {
        String message = MessageContainer.greatherThan + parameter.toString();
        return addRule(t -> t.compareTo(parameter) > 0, message);
    }

    public F lessThan(T parameter) {
        String message = MessageContainer.lessThan + parameter.toString();
        return addRule(t -> t.compareTo(parameter) < 0, message);
    }

    public F greatherEqualsThan(T parameter) {
        String message = MessageContainer.greatherEqualsThan + parameter.toString();
        return addRule(t -> t.compareTo(parameter) >= 0, message);
    }

    public F lessEqualsThan(T parameter) {
        String message = MessageContainer.lessEqualsThan + parameter.toString();
        return addRule(t -> t.compareTo(parameter) <= 0, message);
    }

    private boolean _range(T target, T min, T max) {
        return min.compareTo(target) <= 0 && 0 < max.compareTo(target);
    }

    public F range(T minValue, T maxValue) {
        return addRule(t -> _range(t, minValue, maxValue));
    }

    public static final class ComparableRuleBuilderBase
            extends ComparableRuleBuilder<Comparable, ComparableRuleBuilderBase> {

        private ComparableRuleBuilderBase(ParametrizedRules<Comparable> parameterRule) {
            super(parameterRule);
        }
    }

    public static ComparableRuleBuilderBase cmpRule(String name) {
        return new ComparableRuleBuilderBase(new ParametrizedRules<>(name, Comparable.class));
    }
}
