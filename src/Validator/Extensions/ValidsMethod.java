/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator.Extensions;

import com.sun.org.apache.xpath.internal.functions.FuncBoolean;
import java.util.function.Predicate;
        
/**
 *
 * @author thinhnh
 */
public final class ValidsMethod {

    /*
    ** String
     */
    public static boolean isNotNullOrEmpty(String value) {
        return !value.isEmpty();
    }

    public static boolean isNullOrEmpty(String value) {
        return (value == null || value.isEmpty());
    }
    
    public static boolean isEqual(String value, String target) {
        return value.equals(target);
    }

    /*
    ** Lengths
     */
    public static boolean isBetweenLength(String value, int min, int max) {
        if (value.isEmpty() && min == 0) {
            return true;
        } else if (value.isEmpty()) {
            return false;
        }

        return (value.length() >= min && value.length() <= max);
    }

    public static boolean isMaxLength(String value, int max) {
        if (value.isEmpty() && max > 0) {
            return false;
        }

        return value.length() <= max;
    }

    public static boolean isMinLength(String value, int min) {
        if (value.isEmpty() && min == 0) {
            return true;
        }
        return value.length() >= min;
    }
    
    /*
    ** Special
    */
    
    
    /*
    ** Object
    */
    public static boolean isNotNull(Object value) {
        return value != null;
    }
    
    public static boolean isNull(Object value) {
        return value == null;
    }
}
