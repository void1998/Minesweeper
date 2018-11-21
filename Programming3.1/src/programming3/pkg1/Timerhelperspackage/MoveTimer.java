/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3.pkg1.Timerhelperspackage;

import java.util.TimerTask;

/**
 *
 * @author CEC
 */
public class MoveTimer extends TimerTask{

    private static int count=0;
    @Override
    public void run() {
        if(count==1)
            cancel();
        count++;
    }
    
}
