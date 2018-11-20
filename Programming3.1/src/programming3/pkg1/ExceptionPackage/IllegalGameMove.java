package programming3.pkg1.ExceptionPackage;

public class IllegalGameMove extends GameException {

    private final String message;
    public IllegalGameMove(String string) {
        super(string);
        message=string;
    }

    
    @Override
    public String getException() {
        return message;
    }
    
}
