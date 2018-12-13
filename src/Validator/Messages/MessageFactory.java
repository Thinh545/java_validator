/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator.Messages;

/**
 *
 * @author thinhnh
 */
enum LanguageCodes {
    EN,
    VN;
}

public class MessageFactory {

    private MessageFactory() {

    }

    public static MessageContainer create() {
        return create(LanguageCodes.EN);
    }

    public static MessageContainer create(LanguageCodes language) {
        MessageContainer ret = new MessageContainer();
        switch (language) {
            case EN:
                ret.isNotNullMessage = "cannot be null.";
                ret.isNotNullOrEmptyMessage = "cannot be null or empty.";
                ret.isNotNullOrWhiteSpaceMessage = "cannot be null or whitespace only.";
                ret.isNotZeroMessage = "cannot be zero.";
                ret.isPasswordMessage = "is not a valid password. Passwords must be 8 to 30 characters, at least on 1 uppercase letter, at least 1 lowercase letter and at least one number.";
                ret.isMinLengthMessage = "must be a at least {1} characters.";
                ret.isMaxLengthMessage = "must be {1} characters or less.";
                ret.isExactLengthMessage = "must be exactly {1} characters.";
                ret.isBetweenLengthMessage = "must be at least {1} and at most {2} characters.";
                ret.isMessage = "does not match the specified criteria.";
                ret.isNotMessage = "does not match the specified criteria.";
                ret.isEmailMessage = "is not a valid email address.";
                ret.isRegexMessage = "does not match the provided regular expression.";
                ret.isMatchMessage = "did not match the specified criteria.";
                ret.isDateMessage = "is not a valid date.";
                ret.isRuleMessage = "failed the provided business rule provided.";
                break;
        }

        return ret;
    }
}
