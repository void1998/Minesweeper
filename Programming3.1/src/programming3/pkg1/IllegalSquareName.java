package programming3.pkg1;

public class IllegalSquareName extends GameException {

    private final String message;
    public IllegalSquareName(String string) {
        super(string);
        message=string;
    }

    @Override
    public String getException() {
        return message;
    }
}
