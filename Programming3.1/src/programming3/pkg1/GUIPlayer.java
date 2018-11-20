package programming3.pkg1;
//WARNING: Not Finished

import java.util.ArrayList;
import programming3.pkg1.UtilPackage.Constants;
import programming3.pkg1.Viewpackage.GUIView;
import programming3.pkg1.Score;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import programming3.pkg1.Movespackage.MoveType;
import programming3.pkg1.PlayerMove;

public class GUIPlayer extends Player {
        static Button buttons[][] =  GUIView.initGrid(19,19);
        GUIPlayer(Score score, PlayerStatue playerStatue,String name,int playerNumber) {
        this.currentScore = score;
        this.currentStatue = playerStatue;
        this.name = name;
        this.playerNumber = playerNumber;
        shields = new ArrayList<>();
    }
    @Override
    public PlayerMove GetPlayerMove()
    {
        PlayerMove currentMove = new PlayerMove();
        Square square = new Square();
        MoveType currentType = new MoveType();
        
        
        for(int i=0;i<10; i++)
            for(int j=0;j<19; j++)
            {
                final int x;
                x = i;
                final int y;
                y = j;
                buttons[i][j].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                       
                        if(event.getButton() == MouseButton.PRIMARY)
                        {
                            currentType.setType(Constants.REVEAL);
                            square.setX(x);
                            square.setY(y);
                        }
                        else
                        if(event.getButton() == MouseButton.SECONDARY)
                        {
                                currentType.setType(Constants.MARK);
                                square.setX(x);
                                square.setY(y);
                        }
                        
                        
                    }
                });
                currentMove.setSquare(square);
                currentMove.setMove(currentType);
                currentMove.setPlayer(this);
                
            }
        
        return currentMove;
    }
}
