package programming3.pkg1;

import java.util.List;

public class Square {

    private int x;
    private int y;
    SquareStatus squareStatus;

    public Square() {
        this.x=0;
        this.y=0;
        squareStatus=new SquareStatus();
    }
    
        public Square(int x, int y, SquareStatus squareStatus) {
        this.x = x;
        this.y = y;
        this.squareStatus = squareStatus;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public SquareStatus getSquareStatus() {
        return squareStatus;
    }

    public void setSquareStatus(SquareStatus squareStatus) {
        this.squareStatus = squareStatus;
    }
    

    public boolean IsMine() {
        return squareStatus.getValue()==9;
                
    }
}
