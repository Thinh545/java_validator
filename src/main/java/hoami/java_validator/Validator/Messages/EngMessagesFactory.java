package hoami.java_validator.Validator.Messages;

public class EngMessagesFactory implements MessageFactory{

	private EngMessagesFactory() {
		
	}
	
    public static void create() {
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
    }
}
