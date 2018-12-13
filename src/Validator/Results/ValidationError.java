/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator.Results;

/**
 *
 * @author thinhnh
 */
public class ValidationError {

    private ValidationError(String name, String message) {
        _name = name;
        _message = message;
    }

    private String _name;

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    private String _message;

    public String getMessage() {
        return _message;
    }

    public void setMessage(String message) {
        this._message = message;
    }
    
    public static ValidationError create(String message) {
        return new ValidationError("", message);
    }

    public static ValidationError create(String name, String message) {
        return new ValidationError(name, message);
    }
}
