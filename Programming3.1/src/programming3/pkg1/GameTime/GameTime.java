/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3.pkg1.GameTime;


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
public class GameTime {

    private String startTime;
    private String endTime;
    private Date startDate;
    private Date endDate;
    private String duration;
    

    public GameTime() {
    }

  /*  public GameTime(String startTime, String endTime, String startDate, String endDate, String duration) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
    }*/

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
        this.startDate = getCurrentDate();
    }

    public Date getEndDate() {
        return endDate;
    }

    public void initEndDate() {
        this.endDate = getCurrentDate();
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
    
    
       public Date getCurrentDate()
    {
        Date date=new Date();
        return date;
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
    
 
    
    public List<Grid> sortGamesByStartDate(List<Grid> gamesList)
    {
        Collections.sort(gamesList, new Comparator<Grid>() {
        @Override
        public int compare(Grid one, Grid other) {
            
            Date first=one.getCurrentGame().getGameTime().getStartDate();
            Date second=other.getCurrentGame().getGameTime().getStartDate();
      
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
