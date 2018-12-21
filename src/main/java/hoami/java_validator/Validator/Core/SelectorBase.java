/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoami.java_validator.Validator.Core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import javax.smartcardio.ATR;

import hoami.java_validator.Validator.Annotations.ReadConstraints;

/**
 *
 * @author thinhnh
 */
public class SelectorBase implements Selector {

    private static final String GET_PREFIX = "get";
    private Map<String, Integer> _indexes;
    private Object[] _objects;

    public SelectorBase(Map<String, Integer> indexes, Object[] objects) {
        this._indexes = indexes;
        this._objects = objects;
    }

    @Override
    public <T> T select(String name, Class<T> type) {
        String[] attributePath = name.split("\\.");
        Integer index = _indexes.get(attributePath[0]);
        if (index == null) {
            throw new ConfigurationValidatorException("Unknown bean identified by '" + name + "'");
        } else if (index < 0 || index >= _objects.length) {
            throw new ConfigurationValidatorException("Unknown bean identified by '" + name + "', perhaps wrong order in the call to check beans.");
        } else {
            return (T) get(attributePath, index, _objects[index]);
        }
    }

    private Object get(String[] keys, int index, Object o) {
        int i = index + 1;
        try {
            Object temp = o;
            while (temp != null && i < keys.length) {
                String methodName = _getMethodName(keys[i++]);
                Method method = temp.getClass().getMethod(methodName);
                temp = method.invoke(temp);
            }
            return temp;
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new ConfigurationValidatorException("Error trying to get a property, '" + keys[i] + "' was not found in the object named: '" + keys[i - 1] + "' ", ex);
        }
    }

    private String _getMethodName(String attributeName) {
        if (attributeName != null && attributeName.length() > 0) {
            StringBuilder sb = new StringBuilder(GET_PREFIX.length() + attributeName.length());
            sb.append(GET_PREFIX);
            sb.append(Character.toUpperCase(attributeName.charAt(0)));
            sb.append(attributeName.substring(1));
            return sb.toString();
        } else {
            return null;
        }
    }
}
