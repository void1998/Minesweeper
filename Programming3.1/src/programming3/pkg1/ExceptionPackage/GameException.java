package programming3.pkg1.ExceptionPackage;

import java.io.Serializable;


public abstract class GameException extends Exception implements Serializable{

    public GameException(String string) {
        super(string);
    }
    
    public abstract String getException();
}
