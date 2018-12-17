/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator.Core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author thinhnh
 */
public class ErrorManagerBase implements ErrorManager {

    private HashMap<String, ArrayList<String>> _errors;

    private HashMap<String, ArrayList<String>> _getErrors() {
        if (_errors == null) {
            _errors = new HashMap<>();
        }
        return _errors;
    }

    @Override
    public void addErrorMessage(String name, String message) {
        _getErrors().computeIfAbsent(name, k -> new ArrayList<>()).add(message);
    }

    @Override
    public void check() {
        if (_errors != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Errors:\n");
            for (Entry<String, ArrayList<String>> e : _errors.entrySet()) {
                sb.append("\t- attribute '");
                sb.append(e.getKey());
                sb.append("' (");
                sb.append(e.getValue().size());
                sb.append("):\n");
                for (String msg : e.getValue()) {
                    sb.append("\t\t+ ");
                    sb.append(msg);
                    sb.append("\n");
                }
                sb.append("\n");
            }

            throw new ValidatorException(sb.toString(), _errors);
        }
    }
}
