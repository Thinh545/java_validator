/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoami.java_validator.Validator;

/**
 *
 * @author thinhnh
 */
import hoami.java_validator.Validator.Core.*;
import static hoami.java_validator.Validator.Util.Utilities.checkNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ValidatorBuilder {

    private List<ParametrizedRules> _rules;
    private List<ParametrizedRulesChainAdapter> _rulesAdapter = new ArrayList<>();
    private Map<String, Integer> _index;
    private int _parameterIndex;

    private ValidatorBuilder(List<ParametrizedRules> rules, Map<String, Integer> index, int parameterIndex) {
        this._rules = rules;
        this._index = index;
        this._parameterIndex = parameterIndex;
    }

    public static ValidatorBuilder rules(ParametrizedRuleBuilder... ruleFactories) {
        checkNotNull(ruleFactories);
        List<ParametrizedRules> rules = new ArrayList<>(ruleFactories.length);
        Map<String, Integer> index = new HashMap<>();
        int position = 0;
        for (ParametrizedRuleBuilder<?> builders : ruleFactories) {
            ParametrizedRules<?> rule = builders.build();
            String parameter = rule.getName().split(Constants.ATTRIBUTE_SEPARATOR_PATTERN)[0];
            if (!index.containsKey(parameter)) {
                index.put(parameter, position++);
            }
            if (rule.isActive()) {
                rules.add(rule);
            }
        }
        return new ValidatorBuilder(rules, index, position);
    }

    public ValidatorBuilder include(Validator v) {
        return doInclude(v, null, null);
    }

    public ValidatorBuilder include(Validator v, String prefix) {
        return doInclude(v, prefix, null);
    }

    public ValidatorBuilder include(String validatorName) {
        return doInclude(ValidatorRegistry.getValidator(validatorName), null, null);
    }

    public ValidatorBuilder include(String validatorName, String prefix) {
        return doInclude(ValidatorRegistry.getValidator(validatorName), prefix, null);
    }

    public ValidatorBuilder include(String validatorName, String prefix, String removeFromPreviousName) {
        return doInclude(ValidatorRegistry.getValidator(validatorName), prefix, removeFromPreviousName);
    }

    public ValidatorBuilder include(Validator validator, String prefix, String removeFromPreviousName) {
        return doInclude(validator, prefix, removeFromPreviousName);
    }

    private ValidatorBuilder doInclude(Validator validator, String prefix, String removeFromPreviousName) {
        checkNotNull(validator);
        if (validator instanceof ValidatorBase) {
            ValidatorBase v = (ValidatorBase) validator;
            if (!v._rules.isEmpty()) {
                _rulesAdapter.add(new ParametrizedRulesChainAdapter(prefix, removeFromPreviousName, new ArrayList<>(v._rules)));
            }
            v._rulesAdapter.stream().forEach(adapter -> _rulesAdapter.add(new ParametrizedRulesChainAdapter(prefix, removeFromPreviousName, adapter)));
            if (prefix == null) {
                v._index.entrySet().stream().sorted((e0, e1) -> Integer.compare(e0.getValue(), e1.getValue())).filter(e -> !_index.containsKey(e.getKey())).forEach(e -> _index.put(e.getKey(), _parameterIndex++));
            }
        }
        return this;
    }

    public Validator build() {
        return new ValidatorBase(_rules, _rulesAdapter, _index);
    }

    private static class ValidatorBase implements Validator {

        private List<ParametrizedRules> _rules;
        private List<ParametrizedRulesChainAdapter> _rulesAdapter;
        private Map<String, Integer> _index;

        public ValidatorBase(List<ParametrizedRules> rules, List<ParametrizedRulesChainAdapter> rulesAdapter, Map<String, Integer> index) {
            this._rules = rules;
            this._rulesAdapter = rulesAdapter;
            this._index = index;
        }

        @Override
        public Selector validate_selector(Object... elements) {
            ErrorManager errorManager = new ErrorManagerBase();
            Selector selector = new SelectorBase(_index, elements);
            _rules.stream().forEach(rule -> rule.validate(errorManager, selector));
            for (ParametrizedRulesChainAdapter adapter : _rulesAdapter) {
                Selector adaptedSelector = adapter.getSelector(selector);
                ErrorManager adaptedErrorManager = adapter.getErrorManager(errorManager);
                adapter.getParametrizedRules().stream().forEach(rule -> rule.validate(adaptedErrorManager, adaptedSelector));
            }
            errorManager.check();
            return selector;
        }
        
        @Override
        public ErrorManager validate_error_manager(Object... elements) {
            ErrorManager errorManager = new ErrorManagerBase();
            Selector selector = new SelectorBase(_index, elements);
            _rules.stream().forEach(
            	rule -> rule
            	.validate(errorManager, selector)
            );
            for (ParametrizedRulesChainAdapter adapter : _rulesAdapter) {
                Selector adaptedSelector = adapter.getSelector(selector);
                ErrorManager adaptedErrorManager = adapter.getErrorManager(errorManager);
                adapter.getParametrizedRules().stream().forEach(rule -> rule.validate(adaptedErrorManager, adaptedSelector));
            }

            return errorManager;
        }
    }
}
