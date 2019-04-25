package it.sevenbits.taskmanager.core.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * this is class to work with time
 */
public class TimeService {


    /**
     * this function return current date-time
     *
     * @return String value of current time-date
     */
    public static String getCurrentTime() {
        SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        pattern.setTimeZone(TimeZone.getTimeZone("GMT"));
        return pattern.format(new Date());
    }

}
