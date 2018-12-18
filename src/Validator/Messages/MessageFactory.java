/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator.Messages;

/**
 *
 * @author HP
 */
public class MessageFactory {

    private MessageFactory() {
    }

    public static void create() {
        create(LanguageCodes.ENG);
    }

    public static void create(LanguageCodes code) {

        switch (code) {
            case ENG:
                MessageContainer.notNull = "cannot be null";
                MessageContainer.nullable = "is nullable";
                MessageContainer.mustBeNull = "must be null";
                MessageContainer.in = "must be in ...";
                MessageContainer.notIn = "must not be in ...";
                MessageContainer.equalsTo = "equals to ";
                MessageContainer.notEqualsTo = "is not equal to ";

                MessageContainer.greaterThan = "greater than ";
                MessageContainer.lessThan = "less than ";
                MessageContainer.greaterEqualsThan = " greater equals than ";
                MessageContainer.lessEqualsThan = "less equals than";

                MessageContainer.empty = "must be empty";
                MessageContainer.notEmpty = "cannot be empty";
                MessageContainer.startsWith = "must start with ";
                MessageContainer.notStartsWith = "cannot start with ";
                MessageContainer.endsWith = "must end with ";
                MessageContainer.notEnds = "cannot end with ";
                MessageContainer.contains = "must contain ";
                MessageContainer.notContains = "cannot contain ";
                MessageContainer.matches = "must match ";
                MessageContainer.notMatches = "cannot match ";
                MessageContainer.length = "length = ";
                MessageContainer.minLength = "min length = ";
                MessageContainer.maxLength = "max length = ";

                break;
            case VN:
                MessageContainer.notNull = "x không được null.";
                MessageContainer.nullable = "x có thể null";
                MessageContainer.mustBeNull = "x phải bằng null";
                MessageContainer.in = "x phải nằm trong ...";
                MessageContainer.notIn = "x không được nằm trong ...";
                MessageContainer.equalsTo = "x bằng y";
                MessageContainer.notEqualsTo = "x không bằng y";
                
                MessageContainer.greaterThan = "lớn hơn ";
                MessageContainer.lessThan = "bé hơn ";
                MessageContainer.greaterEqualsThan = " lớn hơn hoặc bằng ";
                MessageContainer.lessEqualsThan = "bé hơn hoặc bằng ";

                MessageContainer.empty = "phải là rỗng";
                MessageContainer.notEmpty = "không thể rỗng";
                MessageContainer.startsWith = "phải bắt đầu bằng ";
                MessageContainer.notStartsWith = "không thể bằng đầu bằng ";
                MessageContainer.endsWith = "phải kết thúc bằng ";
                MessageContainer.notEnds = "không thể kết thúc bằng ";
                MessageContainer.contains = "phải chứa ";
                MessageContainer.notContains = "không thể chứa ";
                MessageContainer.matches = "phải trùng với ";
                MessageContainer.notMatches = "không thể trùng với ";
                MessageContainer.length = "độ dài bằng ";
                MessageContainer.minLength = "độ dài bé nhất bằng ";
                MessageContainer.maxLength = "độ dài lớn nhất bằng ";
                break;
        }
    }
}
