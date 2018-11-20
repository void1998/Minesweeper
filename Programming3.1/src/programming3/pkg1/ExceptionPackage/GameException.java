package programming3.pkg1.ExceptionPackage;


public abstract class GameException extends Exception{

    public GameException(String string) {
        super(string);
    }
    
    public abstract String getException();
}
