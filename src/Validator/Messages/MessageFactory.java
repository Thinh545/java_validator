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
    private MessageFactory(){
        
    }
    
    public static void create(){
        create(LanguageCodes.ENG);
    }
    
    public static void create(LanguageCodes code){
        switch(code){
            case ENG:
                MessageContainer.notNull = "x cannot be null.";
                MessageContainer.nullable = "x is nullable";
                MessageContainer.mustBeNull = "x must be null";
                MessageContainer.in = "x must be in ...";
                MessageContainer.notIn = "x must not be in ...";
                MessageContainer.equalsTo = "x equals to y";
                MessageContainer.notEqualsTo = "x is not equal to y";
            break;
            case VN:
                MessageContainer.notNull = "x không được null.";
                MessageContainer.nullable = "x có thể null";
                MessageContainer.mustBeNull = "x phải bằng null";
                MessageContainer.in = "x phải nằm trong ...";
                MessageContainer.notIn = "x không được nằm trong ...";
                MessageContainer.equalsTo = "x bằng y";
                MessageContainer.notEqualsTo = "x không bằng y";
            break;
        }
    }
}
