/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator;

/**
 *
 * @author thinhnh
 */
import Validator.Core.Selector;

@FunctionalInterface
public interface Validator {

    public Selector validate(Object... elements);
}
