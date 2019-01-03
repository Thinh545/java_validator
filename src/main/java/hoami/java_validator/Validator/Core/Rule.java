/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoami.java_validator.Validator.Core;

/**
 *
 * @author thinhnh
 */
@FunctionalInterface
public interface Rule<T> {

    public void validate(T value, String name, Selector selector, ErrorManager errorManager);
}
