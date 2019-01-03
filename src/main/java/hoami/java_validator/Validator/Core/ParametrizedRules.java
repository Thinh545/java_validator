/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoami.java_validator.Validator.Core;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thinhnh
 */
public class ParametrizedRules<T> {

    public enum NullConstraint {
        NullAble,
        NotNull,
        Null
    }

    private String _name;
    private NullConstraint _nullConstraint;
    private List<Rule<T>> _rules = null;
    private Class<T> _type;

    public ParametrizedRules(String name, Class<T> type) {
        _name = name;
        _type = type;
    }

    public void addRule(Rule<T> rule) {
        if (_rules == null) {
            _rules = new ArrayList<>();
        }

        _rules.add(rule);
    }

    public void setNullConstraint(NullConstraint nullConstraint) {
        _nullConstraint = nullConstraint;
    }

    public NullConstraint getNullConstraint() {
        return _nullConstraint;
    }

    public String getName() {
        return _name;
    }

    public boolean isActive() {
        return _nullConstraint != NullConstraint.NullAble || _rules != null;
    }

    public void validate(ErrorManager errorManager, Selector selector) {
        T value = selector.select(_name, _type);
        if (value != null) {
            if (_nullConstraint == NullConstraint.Null) {
                errorManager.addErrorMessage(_name, "Object named '" + _name + "' must be 'null' but It was not.");
            } else if (_rules != null) {
                _rules.stream().forEach(rule -> rule.validate(value, _name, selector, errorManager));
            }
        } else if (_nullConstraint == NullConstraint.NotNull) {
            errorManager.addErrorMessage(_name, "Object named '" + _name + "' was 'null' and It must be not 'null'.");
        }
    }
}
