/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoami.java_validator.Validator.Messages;

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
                MessageContainer.notNull = "x khÃ´ng Ä‘Æ°Æ¡Ì£c null.";
                MessageContainer.nullable = "x coÌ� thÃªÌ‰ null";
                MessageContainer.mustBeNull = "x phaÌ‰i bÄƒÌ€ng null";
                MessageContainer.in = "x phaÌ‰i nÄƒÌ€m trong ...";
                MessageContainer.notIn = "x khÃ´ng Ä‘Æ°Æ¡Ì£c nÄƒÌ€m trong ...";
                MessageContainer.equalsTo = "x bÄƒÌ€ng y";
                MessageContainer.notEqualsTo = "x khÃ´ng bÄƒÌ€ng y";
                
                MessageContainer.greaterThan = "lÆ¡Ì�n hÆ¡n ";
                MessageContainer.lessThan = "beÌ� hÆ¡n ";
                MessageContainer.greaterEqualsThan = " lÆ¡Ì�n hÆ¡n hoÄƒÌ£c bÄƒÌ€ng ";
                MessageContainer.lessEqualsThan = "beÌ� hÆ¡n hoÄƒÌ£c bÄƒÌ€ng ";

                MessageContainer.empty = "phaÌ‰i laÌ€ rÃ´Ìƒng";
                MessageContainer.notEmpty = "khÃ´ng thÃªÌ‰ rÃ´Ìƒng";
                MessageContainer.startsWith = "phaÌ‰i bÄƒÌ�t Ä‘Ã¢Ì€u bÄƒÌ€ng ";
                MessageContainer.notStartsWith = "khÃ´ng thÃªÌ‰ bÄƒÌ€ng Ä‘Ã¢Ì€u bÄƒÌ€ng ";
                MessageContainer.endsWith = "phaÌ‰i kÃªÌ�t thuÌ�c bÄƒÌ€ng ";
                MessageContainer.notEnds = "khÃ´ng thÃªÌ‰ kÃªÌ�t thuÌ�c bÄƒÌ€ng ";
                MessageContainer.contains = "phaÌ‰i chÆ°Ì�a ";
                MessageContainer.notContains = "khÃ´ng thÃªÌ‰ chÆ°Ì�a ";
                MessageContainer.matches = "phaÌ‰i truÌ€ng vÆ¡Ì�i ";
                MessageContainer.notMatches = "khÃ´ng thÃªÌ‰ truÌ€ng vÆ¡Ì�i ";
                MessageContainer.length = "Ä‘Ã´Ì£ daÌ€i bÄƒÌ€ng ";
                MessageContainer.minLength = "Ä‘Ã´Ì£ daÌ€i beÌ� nhÃ¢Ì�t bÄƒÌ€ng ";
                MessageContainer.maxLength = "Ä‘Ã´Ì£ daÌ€i lÆ¡Ì�n nhÃ¢Ì�t bÄƒÌ€ng ";
                break;
        }
    }
}
