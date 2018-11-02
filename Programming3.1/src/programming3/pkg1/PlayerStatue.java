/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3.pkg1;

/**
 *
 * @author void
 */
public class PlayerStatue  {
    private String status;
    PlayerStatue()
    {
        this.status = Constants.PLAYING;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    public String getStatus()
    {
        return this.status;
    }
}
