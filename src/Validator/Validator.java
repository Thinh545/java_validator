/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator;

import java.util.ArrayList;

import Validator.Results.*;
import Validator.Messages.*;

/**
 *
 * @author thinhnh
 */
public class Validator {

    // Constructor
    public Validator() {
        errors = new ArrayList<ValidationError>();
        _messageContainer = MessageFactory.create();
    }

    //////
    // Members
    private ValidationError _lastError = null;

    private MessageContainer _messageContainer;

    public MessageContainer getMessageContainer() {
        return _messageContainer;
    }

    public void setMessageContainer(MessageContainer messageContainer) {
        this._messageContainer = messageContainer;
    }

    public boolean isValid;

    // Validation errors
    public ArrayList<ValidationError> errors;

    public ArrayList<ValidationError> geterrors() {
        return errors;
    }

    public void seterrors(ArrayList<ValidationError> errors) {
        this.errors = errors;
    }

    public ArrayList<ValidationError> ErrorByName(String name) {
        // do something
        return new ArrayList<ValidationError>();
    }

    public Validator isNotNull(Object value) {
        return isNotNull("", value);
    }

    public Validator isNotNull(String name, Object value) {
        return isNotNull(name, value, String.format(_messageContainer.isNotMessage, name));
    }

    public Validator isNotNull(String name, Object value, String message) {
        if (value.isNotNull()) {
            return NoError();
        } else {
            return AddError(name, message);
        }
    }

    protected Validator NoError() {
        _lastError = null;
        return this;
    }

    public Validator AddError(String message) {
        return AddError("", message);
    }

    public Validator AddError(String name, String message) {
        ValidationError error = ValidationError.create(name, message);
        errors.add(error);
        _lastError = error;
        return this;
    }
}
