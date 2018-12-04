package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Logger {
    private ArrayList<String> logList = new ArrayList<>();
    private File logFile;
    private static Logger logger;
    private boolean loggerFileSetted = false;

    public static Logger getLoger(){
        if(logger == null){
            logger = new Logger();
        }
        return logger;
    }

    private Logger(){

    }


    public void saveLogFile(){
        if (loggerFileSetted){
            try {
                PrintWriter pw = new PrintWriter(logFile);
                for (String log :logList) {
                    pw.println(log);
                }
                pw.close();
            } catch (FileNotFoundException e) {
                System.out.println("лог не сохранен !");
            }
        } else {
            System.out.println("лог файл не назначен, програмист бака");
        }
    }

    public void setLogFile(File logFile){
        this.logFile = logFile;
        loggerFileSetted = true;
    }

    public void addLogg(String log){
        log = LocalDateTime.now().toLocalDate() + " " + LocalDateTime.now().toLocalTime() + " : " + log ;
        logList.add(log);
    }
}
