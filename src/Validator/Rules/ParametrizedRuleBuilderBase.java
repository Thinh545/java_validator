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
import Validator.Core.Rule;
import Validator.Core.RuleBase;
import Validator.Core.Selector;
import Validator.Core.ParametrizedRules;
import Validator.Core.ParametrizedRules.NullConstraint;
import Validator.Core.ParametrizedRuleBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class ParametrizedRuleBuilderBase<T, F extends ParametrizedRuleBuilderBase<T, F>> implements ParametrizedRuleBuilder<T> {

    private ParametrizedRules<T> _parameterRules;

    public ParametrizedRuleBuilderBase(ParametrizedRules<T> parameterRules) {
        this._parameterRules = parameterRules;
    }

    public final F addRule(Rule<T> rule) {
        _parameterRules.addRule(rule);
        return (F) this;
    }

    public final F addRule(BiPredicate<T, Selector> predicate) {
        return addRule(new RuleBase<>(predicate));
    }

    public final F addRule(Predicate<T> predicate) {
        return addRule(new RuleBase<>((target, s) -> predicate.test(target)));
    }

    private F setNullConstraint(NullConstraint constraint) {
        _parameterRules.setNullConstraint(constraint);
        return (F) this;
    }

    public F notNull() {
        return setNullConstraint(NullConstraint.NotNull);
    }

    public F nullable() {
        return setNullConstraint(NullConstraint.NullAble);
    }

    public ParametrizedRuleBuilder<T> mustBeNull() {
        return setNullConstraint(NullConstraint.Null);
    }

    public F in(T... parameters) {
        return addRule(Arrays.asList(parameters)::contains);
    }

    public F in(Collection<T> parameter) {
        return addRule(parameter::contains);
    }

    public F notIn(T... parameters) {
        return addRule(t -> !Arrays.asList(parameters).contains(t));
    }

    public F notIn(Collection<T> parameter) {
        return addRule(t -> !parameter.contains(t));
    }

    public F equalsTo(T parameter) {
        return addRule(t -> t.equals(parameter));
    }

    public F notEqualsTo(T parameter) {
        return addRule(t -> !t.equals(parameter));
    }

    @Override
    public ParametrizedRules<T> build() {
        if (_parameterRules.getNullConstraint() == null) {
            nullable();
        }
        return _parameterRules;
    }

    public static <F extends ParametrizedRuleBuilderBase<Object, F>> ParametrizedRuleBuilderBase<Object, F> objectRule(String name) {
        return new ParametrizedRuleBuilderBase<>(new ParametrizedRules<>(name, Object.class));
    }
}
