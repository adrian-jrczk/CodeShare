package codeshare.code;

public class CodeValidator {

    public static final String REQUIREMENTS = """
            Name and string value field must not be blank
            """;

    public static boolean isValid(Code code) {
        return !(code.getName().isBlank() || code.getStringValue().isBlank());
    }
}
