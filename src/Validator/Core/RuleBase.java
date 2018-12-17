/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator.Core;

import java.util.function.BiPredicate;

/**
 *
 * @author thinhnh
 */
public class RuleBase<T> implements Rule<T> {
    
    private BiPredicate<T, Selector> _predicate;
    
    public RuleBase(BiPredicate<T, Selector> predicate) {
        this._predicate = predicate;
    }
    
    @Override
    public void validate(T value, String name, Selector selector, ErrorManager errorManager) {
        if (!_predicate.test(value, selector)) {
            errorManager.addErrorMessage(name, name);
        }
    }
}
