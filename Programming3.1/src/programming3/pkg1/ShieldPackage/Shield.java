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
    protected int x;
    protected int y;
    protected int type;
    
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
    
    public int getType()
    {
        return type;
    }
    
    public abstract int InteractWithScore(int scoreChange);
    public abstract void RemoveShield ();
}
