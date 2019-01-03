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
import hoami.java_validator.Validator.Core.Selector;
import hoami.java_validator.Validator.Core.ErrorManager;
 
public interface Validator {

    public Selector validate_selector(Object... elements);

    public ErrorManager validate_error_manager(Object... elements);
}
