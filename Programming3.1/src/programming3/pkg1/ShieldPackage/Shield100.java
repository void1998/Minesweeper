/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3.pkg1.ShieldPackage;

import static programming3.pkg1.ShieldPackage.Shield.ShieldsNumber;

/**
 *
 * @author ASUS
 */
public class Shield100 extends Shield
{
     protected int number;
    public Shield100()
    {
        this.number++;
        ShieldsNumber++;
    }
    
    @Override
    public int InteractWithScore(int scoreChange)
    {
        return 0;
    }
    
    @Override
    public void RemoveShield()
    {
        this.number--;
        ShieldsNumber--;
    }
}
