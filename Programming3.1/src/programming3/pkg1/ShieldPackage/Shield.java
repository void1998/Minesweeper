/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3.pkg1.ShieldPackage;

import programming3.pkg1.Player;

/**
 *
 * @author ASUS
 */
public abstract class Shield 
{
    protected static int ShieldsNumber = 0;
    protected Player player;
    
    public static int getShieldsNumber() {
        return ShieldsNumber;
    }

    public static void setShieldsNumber(int ShieldsNumber) {
        Shield.ShieldsNumber = ShieldsNumber;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public abstract int InteractWithScore(int scoreChange);
    public abstract void RemoveShield ();
}
