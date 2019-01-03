/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoami.java_validator.Validator.Rules;

import java.lang.reflect.Method;


/**
 *
 * @author thinhnh
 */
import hoami.java_validator.Validator.Annotations.ReadConstraints;
import hoami.java_validator.Validator.Core.ParametrizedRules;
import hoami.java_validator.Validator.Messages.MessageContainer;

public class ComparableRuleBuilder<T extends Comparable, F extends ComparableRuleBuilder<T, F>>
        extends ParametrizedRuleBuilderBase<T, F> {

    protected ComparableRuleBuilder(ParametrizedRules<T> attributeRule) {
        super(attributeRule);
    }

    @ReadConstraints(constraints = " use greater than - ")
    public F greatherThan(T parameter) {
    	readConstraints("greatherThan");
        String message = MessageContainer.greaterThan + parameter.toString();
        return addRule(t -> t.compareTo(parameter) > 0, message);
    }

    @ReadConstraints(constraints = " use less than - ")
    public F lessThan(T parameter) {
    	readConstraints("lessThan");
        String message = MessageContainer.lessThan + parameter.toString();
        return addRule(t -> t.compareTo(parameter) < 0, message);
    }

    @ReadConstraints(constraints = " use greater equals than - ")
    public F greatherEqualsThan(T parameter) {
    	readConstraints("greatherEqualsThan");
        String message = MessageContainer.greaterEqualsThan + parameter.toString();
        return addRule(t -> t.compareTo(parameter) >= 0, message);
    }

    @ReadConstraints(constraints = " use less equals than - ")
    public F lessEqualsThan(T parameter) {
    	readConstraints("lessEqualsThan");
        String message = MessageContainer.lessEqualsThan + parameter.toString();
        return addRule(t -> t.compareTo(parameter) <= 0, message);
    }

    @ReadConstraints(constraints = " use _range - ")
    public boolean _range(T target, T min, T max) {
    	readConstraints("_range");
        return min.compareTo(target) <= 0 && 0 < max.compareTo(target);
    }

    @ReadConstraints(constraints = " use range - ")
    public F range(T minValue, T maxValue) {
    	readConstraints("range");
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
    
    private static void readConstraints(String constraints){
    	@SuppressWarnings("rawtypes")
		Class<ComparableRuleBuilder> s = ComparableRuleBuilder.class;
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
