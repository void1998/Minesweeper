/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3.pkg1.GameTime;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import programming3.pkg1.Grid;

/**
 *
 * @author CEC
 */
public class GameTime implements Serializable{

    private final Date actualStartDate;
    private String startTime;
    private String endTime;
    private Date startDate;
    private Date endDate;
    private String duration;
    

    public GameTime() {
        actualStartDate=new Date();
    }

    public Date getActualStartDate() {
        return actualStartDate;
    }
    
    
    public String getStartTime() {
        return startTime;
    }

    public void initStartTime() {
        this.startTime = getCurrentTime();
    }

    public String getEndTime() {
        return endTime;
    }

    public void initEndTime() {
        this.endTime = getCurrentTime();
        calculateDuration();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void initStartDate() {
        this.startDate = new Date();
        initStartTime();
    }

    public Date getEndDate() {
        return endDate;
    }

    public void initEndDate() {
        this.endDate = new Date();
        initEndTime();
    }

    public String getDuration() {
        return duration;
    }

    public void calculateDuration() {
        this.duration = getGameTime(startTime, endTime);
    }
    
    

    public String getCurrentTime()
    {
        LocalDateTime time = LocalDateTime.now();
        String format = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        return format;
    }
    
    
    
    public String getGameTime(String startTime,String endTime)
    {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime startDateTime = LocalDateTime.parse(startTime, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endTime, formatter);
        ///////////////////////////////////////////////
        LocalDateTime durationTime= LocalDateTime.parse(this.duration, formatter);
        ///////////////////////////////////////////////
        
        long hours = ChronoUnit.HOURS.between(startDateTime, endDateTime) + durationTime.getHour();
        long minutes = ChronoUnit.MINUTES.between(startDateTime, endDateTime) + durationTime.getMinute();
        long seconds = ChronoUnit.SECONDS.between(startDateTime, endDateTime) + durationTime.getSecond();
        
        
        Calendar calendar=Calendar.getInstance();
        
        calendar.set(Calendar.HOUR_OF_DAY,(int)hours );
        calendar.set(Calendar.MINUTE, (int)minutes);
        calendar.set(Calendar.SECOND, (int)seconds);
      
       String formatedDuration=new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());

        return formatedDuration;
        
    }
    
 
    
    public List<Grid> sortGamesByStartDate(List<Grid> gamesList)
    {
        Collections.sort(gamesList, new Comparator<Grid>() {
        @Override
        public int compare(Grid one, Grid other) {
            
            Date first=one.getCurrentGame().getGameTime().getActualStartDate();
            Date second=other.getCurrentGame().getGameTime().getActualStartDate();
      
            return first.compareTo(second);
  }
});
        return gamesList;
    }
    
    
    public List<Grid> sortGamesByEndtDate(List<Grid> gamesList)
    {
        Collections.sort(gamesList, new Comparator<Grid>() {
        @Override
        public int compare(Grid one, Grid other) {
            
            Date first=one.getCurrentGame().getGameTime().getEndDate();
            Date second=other.getCurrentGame().getGameTime().getEndDate();
      
            return first.compareTo(second);
  }
}); 
        return gamesList;
    }
    
    
    public List<Grid> sortGamesByPlayerName(List<Grid> gamesList)
    {
        Collections.sort(gamesList, new Comparator<Grid>() {
        @Override
        public int compare(Grid one, Grid other) {
        
        String first=one.getCurrentGame().getCurrentPlayer().getName();
        String second=other.getCurrentGame().getCurrentPlayer().getName();
        
        return first.compareTo(second);
        }
      });
     return gamesList;
    }
}
