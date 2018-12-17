/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator.Core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author thinhnh
 */
public class ValidatorException extends RuntimeException {

    private final HashMap<String, ArrayList<String>> _errors;

    public ValidatorException(String message, HashMap<String, ArrayList<String>> errors) {
        super(message);
        this._errors = new HashMap<>(errors);
    }

    public Set<Entry<String, ArrayList<String>>> getErrors() {
        return _errors.entrySet();
    }
}
