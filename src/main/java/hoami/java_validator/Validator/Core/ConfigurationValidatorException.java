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
public class ConfigurationValidatorException extends RuntimeException {

    public ConfigurationValidatorException(String message) {
        super(message);
    }

    public ConfigurationValidatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
