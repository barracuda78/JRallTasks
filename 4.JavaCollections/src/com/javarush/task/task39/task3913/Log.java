package com.javarush.task.task39.task3913;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Log {
    private List<String> logLines = new ArrayList<>();
    private String ip;
    private String user;
    private Date date;
    private String event;
    private String status;

    private Number task;

    public Log(String logs) {
        logLines.addAll(Arrays.asList(logs.split("\t")));

        this.ip = setIp();
//        System.out.println("ip: "+ ip);
        this.user = setUser();
//        System.out.println("user: " + user);
        this.date = setDate();
//        System.out.println("date: " + date.toString() + " date: " + date);
        this.event = setEvent();
//        System.out.println("event: " + event);
//        System.out.println("task: " + task);
        this.status = setStatus();
//        System.out.println("status: " + status);
//        System.out.println("--------------------------------------------------------------------------");
    }

    private String setIp() {
        return logLines.get(0);
    }

    private String setUser() {
        return logLines.get(1);
    }

    private Date setDate() {
        Date date = null;
        DateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
        try {
            date = simpleDateFormat.parse(logLines.get(2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private String setEvent() {
        for (Event e: Event.values()) {
            if (logLines.get(3).compareTo(e.toString()) == 0) {
                return logLines.get(3);
            }
        }
        String e = logLines.get(3).substring(0, logLines.get(3).indexOf(' '));
        setTask(e);
        return e;
    }

    private void setTask(String str) {
        this.task = Integer.parseInt(logLines.get(3).substring(logLines.get(3).indexOf(' ')).substring(1));
    }

    private String setStatus() {
        return logLines.get(4);
    }

    public String getIp() {
        return ip;
    }

    public String getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }

    public String getEvent() {
        return event;
    }

    public String getStatus() {
        return status;
    }

    public Number getTask() {
        return task;
    }
}
