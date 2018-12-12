package programming3.pkg1;

import java.io.Serializable;
import programming3.pkg1.ShieldPackage.Shield;
import programming3.pkg1.ShieldPackage.Shield0;
import programming3.pkg1.UtilPackage.Constants;

public class SquareStatus implements Serializable{

    private String status;
    private int value;
    private boolean is_marked;
    private boolean is_closed;
    protected Shield shield;

    public SquareStatus() {
        this.status=Constants.CLOSED;
        this.value=0;
        is_closed=true;
        is_marked=false;
        shield = null;
    }
    public Shield getShield()
    {
        return shield;
    }
            

    
    
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isIs_marked() {
        return is_marked;
    }

    public void setIs_marked(boolean is_marked) {
        this.is_marked = is_marked;
    }
    
    public String makeStatus() {

        if(is_marked)
        {
            setStatus(Constants.MARKED);
        }
        else if (is_closed)
        {
            setStatus(Constants.CLOSED);
        }
        else {
            switch (value) {
                case 0:
                    setStatus(Constants.OPENED_EMPTY);
                    break;
                case 9:
                    setStatus(Constants.OPENED_MINE);
                    break;
                default:
                    setStatus(Constants.OPENED_NUMBER);
                    break;
            }
        }
              return getStatus();
    }
}
