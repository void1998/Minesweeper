package programming3.pkg1.Movespackage;

import java.io.Serializable;

public class MoveType implements Serializable{

    private String type;

    public MoveType() {
    }

    public MoveType(String type) {
        this.type = type;
    }

    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
