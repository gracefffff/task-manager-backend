package it.sevenbits.taskmanager.core.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeService {

    TimeService() {}

 public static String getCurrentTime() {

        SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
       return pattern.format(new Date());
    }

}
