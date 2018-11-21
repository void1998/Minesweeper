/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3.pkg1.Timerhelperspackage;

import java.util.TimerTask;
import programming3.pkg1.Grid;
import programming3.pkg1.PlayerMove;
import programming3.pkg1.UtilPackage.GridInterface;

/**
 *
 * @author CEC
 */
public class TimerHelper extends TimerTask{

    GridInterface gridInterface;
    Grid myGrid =new Grid();
    PlayerMove temp=new PlayerMove();

    public TimerHelper(GridInterface gi) {
        gridInterface=gi;
    }
    
    
    @Override
    public void run() {
        myGrid=gridInterface.getGrid();
               myGrid.getCurrentGame().setCurrentPlayer(myGrid.getCurrentGame().getCurrentRules().
                    DecideNextPlayer(myGrid.getCurrentGame().getCurrentPlayer()));
        temp =  myGrid.getCurrentGame().getCurrentPlayer().GetPlayerMove();
        if(temp!=null)
        { 
            myGrid.AcceptMove(temp);
            cancel();
        }
    }
    
}
