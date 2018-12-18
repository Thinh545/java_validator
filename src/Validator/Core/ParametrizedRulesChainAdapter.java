/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator.Core;

/**
 *
 * @author thinhnh
 */
import java.util.ArrayList;
import java.util.List;

public class ParametrizedRulesChainAdapter {

    private ParametrizedRulesChainAdapter _nextAdapter = null;
    private String _prefix;
    private String _removeFromName;
    private List<ParametrizedRules> _parametrizedRules;

    public ParametrizedRulesChainAdapter(String prefix, String removeFromName, List<ParametrizedRules> parametrizedRules) {
        this._prefix = prefix;
        this._removeFromName = removeFromName;
        this._parametrizedRules = new ArrayList<>(parametrizedRules);
    }

    public ParametrizedRulesChainAdapter(String prefix, String removeFromName, ParametrizedRulesChainAdapter nextAdapter) {
        this(prefix, removeFromName, nextAdapter._parametrizedRules);
        this._nextAdapter = nextAdapter;
    }

    public List<ParametrizedRules> getParametrizedRules() {
        return _parametrizedRules;
    }

    static String removePrefix(String name, String prefixToRemove) {
        String result = name;
        if (name != null) {
            if (name.equals(prefixToRemove)) {
                result = null;
            } else if (prefixToRemove != null && name.startsWith(prefixToRemove.concat(Constants.ATTRIBUTE_SEPARATOR))) {
                result = name.substring(prefixToRemove.length() + 1);
            }
        }
        return result;
    }

    static String convert(String prefix, String value, String removeFromName) {
        String name = removePrefix(value, removeFromName);
        String result = prefix;
        if (name != null) {
            if (prefix != null) {
                result = String.join(Constants.ATTRIBUTE_SEPARATOR, prefix, name);
            } else {
                result = name;
            }
        }
        return result;
    }

    public Selector getSelector(Selector selector) {
        Selector result = new SelectorAdapter(selector);
        if (_nextAdapter != null) {
            result = _nextAdapter.getSelector(result);
        }
        return result;
    }

    public ErrorManager getErrorManager(ErrorManager errorManager) {
        ErrorManager result = new ErrorManagerAdapter(errorManager);
        if (_nextAdapter != null) {
            result = _nextAdapter.getErrorManager(result);
        }
        return new ErrorManagerAdapter(result);
    }

    private class SelectorAdapter implements Selector {

        private Selector selector;

        public SelectorAdapter(Selector selector) {
            this.selector = selector;
        }

        @Override
        public <T> T select(String name, Class<T> type) {
            return selector.select(convert(_prefix, name, _removeFromName), type);
        }
    }

    private class ErrorManagerAdapter implements ErrorManager {

        private ErrorManager _errorManager;

        public ErrorManagerAdapter(ErrorManager errorManager) {
            this._errorManager = errorManager;
        }

        @Override
        public void addErrorMessage(String attributeName, String message) {
            _errorManager.addErrorMessage(convert(_prefix, attributeName, _removeFromName), message);
        }

        @Override
        public void check() {
            _errorManager.check();
        }
        
        @Override
        public String getResult() {
            return _errorManager.getResult();
        }
    }
}
