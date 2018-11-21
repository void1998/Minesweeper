/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3.pkg1.ShieldPackage;

/**
 *
 * @author ASUS
 */
public class Shield50 extends Shield
{
    protected int number;
    public Shield50()
    {
        this.number++;
        ShieldsNumber++;
        type=50;
        x=0;
        y=0;
    }
    
    @Override
    public int InteractWithScore(int scoreChange)
    {
        
        return scoreChange/2;
    }
    
    @Override
    public void RemoveShield()
    {
        this.number--;
        ShieldsNumber--;
    }
}
