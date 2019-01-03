/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoami.java_validator.Validator.Rules;

/**
 *
 * @author thinhnh
 */
import hoami.java_validator.Validator.Core.Rule;
import hoami.java_validator.Validator.Core.RuleBase;
import hoami.java_validator.Validator.Core.Selector;
import hoami.java_validator.Validator.Core.ParametrizedRules;
import hoami.java_validator.Validator.Core.ParametrizedRules.NullConstraint;
import hoami.java_validator.Validator.Annotations.ReadConstraints;
import hoami.java_validator.Validator.Core.ParametrizedRuleBuilder;
import hoami.java_validator.Validator.Messages.MessageContainer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class ParametrizedRuleBuilderBase<T, F extends ParametrizedRuleBuilderBase<T, F>>
        implements ParametrizedRuleBuilder<T> {

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

    public final F addRule(BiPredicate<T, Selector> predicate, String message) {
    	
        return addRule(new RuleBase<>(predicate, message));
    }

    public final F addRule(Predicate<T> predicate, String message) {    	
        return addRule(new RuleBase<>((target, s) -> predicate.test(target), message));
    }

    private F setNullConstraint(NullConstraint constraint) {
    	
        _parameterRules.setNullConstraint(constraint);
        return (F) this;
    }

    @ReadConstraints(constraints = " use not null - ")
    public F notNull() {
    	readConstraints("notNull");
        return setNullConstraint(NullConstraint.NotNull);
    }

    @ReadConstraints(constraints = " use nullable - ")
    public F nullable() {
    	readConstraints("nullable");
        return setNullConstraint(NullConstraint.NullAble);
    }

    @ReadConstraints(constraints = " use must be null - ")
    public ParametrizedRuleBuilder<T> mustBeNull() {
    	readConstraints("mustBeNull");
        return setNullConstraint(NullConstraint.Null);
    }

    @ReadConstraints(constraints = " use in - ")
    public F in(T... parameters) {
    	readConstraints("in");
        return addRule(Arrays.asList(parameters)::contains);
    }

    @ReadConstraints(constraints = " use in with collection - ")
    public F in(Collection<T> parameter) {
    	readConstraints("in");
        return addRule(parameter::contains);
    }

    @ReadConstraints(constraints = " use not in - ")
    public F notIn(T... parameters) {
    	readConstraints("notIn");
        return addRule(t -> !Arrays.asList(parameters).contains(t));
    }

    @ReadConstraints(constraints = " use not in with collection - ")
    public F notIn(Collection<T> parameter) {
    	readConstraints("notIn");
        return addRule(t -> !parameter.contains(t));
    }

    @ReadConstraints(constraints = " use equals to - ")
    public F equalsTo(T parameter) {
    	readConstraints("equalsTo");
        String message = MessageContainer.equalsTo + parameter.toString();
        return addRule(t -> t.equals(parameter), message);
    }

    @ReadConstraints(constraints = " use not equals to - ")
    public F notEqualsTo(T parameter) {
    	readConstraints("notEqualsTo");
        String message = MessageContainer.notEqualsTo + parameter.toString();
        return addRule(t -> !t.equals(parameter), message);
    }

    @Override
    public ParametrizedRules<T> build() {
        if (_parameterRules.getNullConstraint() == null) {
            nullable();
        }
        return _parameterRules;
    }

    public static <F extends ParametrizedRuleBuilderBase<Object, F>> ParametrizedRuleBuilderBase<Object, F> objectRule(
            String name) {
        return new ParametrizedRuleBuilderBase<>(new ParametrizedRules<>(name, Object.class));
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
