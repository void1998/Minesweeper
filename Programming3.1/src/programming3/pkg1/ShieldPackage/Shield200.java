/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3.pkg1.ShieldPackage;

import static java.lang.Math.abs;

/**
 *
 * @author ASUS
 */
public class Shield200 extends Shield
{
     protected int number;
    Shield200()
    {
        this.number++;
        ShieldsNumber++;
    }
    
    @Override
    public int InteractWithScore(int scoreChange)
    {
        return abs(scoreChange)*2;
    }
}