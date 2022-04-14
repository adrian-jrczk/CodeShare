package codeshare.user;

public class UserValidator {

    public static final String REQUIREMENTS = """
            Name must be 3-40 characters long and password must have 8-100 characters
            """;

    public static boolean isValid(User user) {
        String name = user.getName().trim();
        String password = user.getPassword().trim();
        return name.length() > 2 &&
                name.length() < 40 &&
                password.length() > 7 &&
                password.length() < 100;
    }
}
