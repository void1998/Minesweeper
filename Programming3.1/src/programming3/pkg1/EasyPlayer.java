/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3.pkg1;

import programming3.pkg1.UtilPackage.Constants;
import programming3.pkg1.AutoPlayer;
import programming3.pkg1.Score;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import programming3.pkg1.UtilPackage.GridInterface;
import programming3.pkg1.Movespackage.MoveType;
import programming3.pkg1.PlayerMove;
import programming3.pkg1.Timerhelperspackage.MoveTimer;

/**
 *
 * @author CEC
 */
public class EasyPlayer extends AutoPlayer{
    GridInterface gridInterface;
    Grid currentGrid=new Grid();
    List<Square> closedSquares=new ArrayList<>();

   /* public EasyPlayer() {
        currentGrid=gridInterface.getGrid();
    }*/
    
    EasyPlayer(Score score, PlayerStatue playerStatue, String name,GridInterface gridInterface, int playerNumber) {
        this.name = name;
        this.currentScore = score;
        this.currentStatue = playerStatue;
        this.gridInterface = gridInterface;
        this.playerNumber = playerNumber;
    }
    
    public EasyPlayer(GridInterface gridInterface, Grid currentGrid) {
        this.gridInterface = gridInterface;
        this.currentGrid = currentGrid;
    }
    
    @Override
    public PlayerMove GetPlayerMove()
    {
        fillUpList();
        PlayerMove playerMove=new PlayerMove();
        playerMove.setSquare(GenerateMove());
        MoveType currentMove=new MoveType();
        currentMove.setType(Constants.REVEAL);
        playerMove.setMove(currentMove);
        playerMove.setPlayer(this);
        return playerMove;
    }
    
    public void fillUpList()
    {
        currentGrid=gridInterface.getGrid();
        for(int i=1;i<=currentGrid.getHieght();i++)
        {
            for(int j=1;j<=currentGrid.getWidth();j++)
            {
                if(currentGrid.getSquares()[i][j].squareStatus.getStatus().equals(Constants.CLOSED))
                {
                    closedSquares.add(currentGrid.getSquares()[i][j]);
                }
            }
        }
    }
    public Square GenerateMove()
    { 
        Square randomSquare;
        Random random=new Random();
        
        int delay=random.nextInt(15);
        Timer timer=new Timer();
        TimerTask task=new MoveTimer();
        timer.schedule(task, delay);
        
        int index=random.nextInt(closedSquares.size()-1);
        randomSquare=closedSquares.get(index);
        return randomSquare;
    }
}
