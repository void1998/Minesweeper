/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3.pkg1.GameTime;

import static java.lang.Math.abs;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;


/**
 *
 * @author CEC
 */
public class GameTime {
 
    private LocalDateTime time;

    public GameTime(LocalDateTime time) {
        this.time = time;
    }

    public GameTime() {
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

           
    public String getCurrentTime()
    {
        time = LocalDateTime.now();
        String format = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        return format;
    }
    
    
    public String getGameTime(String startTime,String endTime)
    {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime startDateTime = LocalDateTime.parse(startTime, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endTime, formatter);
        
        long hours = ChronoUnit.HOURS.between(startDateTime, endDateTime);
        long minutes = ChronoUnit.MINUTES.between(startDateTime, endDateTime);
        long seconds = ChronoUnit.SECONDS.between(startDateTime, endDateTime);
        
        Calendar calendar=Calendar.getInstance();
        
        calendar.set(Calendar.HOUR_OF_DAY,(int)hours );
        calendar.set(Calendar.MINUTE, (int)minutes);
        calendar.set(Calendar.SECOND, (int)seconds);
      
       String formatedDuration=new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());

        return formatedDuration;
        
    }
    
}
