package programming3.pkg1;

public abstract class GameException extends Exception{

    public GameException(String string) {
        super(string);
    }
    public abstract String getException();
}
