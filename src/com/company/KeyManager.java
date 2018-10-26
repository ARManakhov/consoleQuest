package com.company;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

public class KeyManager {

    public static HashSet<String> activeKeyHash;    //hashset со всеми нажатыми в данный момент кнопками

    private static boolean gettedSavedButtonSettings = false;


    private static final File BUTT_SETTING_FILE = new File("saves","keys");

    private static final String DEFAULT_UP_BUTTON = "UP";
    private static final String DEFAULT_DOWN_BUTTON = "DOWN";
    private static final String DEFAULT_LEFT_BUTTON = "LEFT";
    private static final String DEFAULT_RIGHT_BUTTON = "RIGHT";
    private static final String DEFAULT_CHOSE_BUTTON = "ENTER";



    private static String buttUP = DEFAULT_UP_BUTTON;
    private static String buttDOWN = DEFAULT_DOWN_BUTTON;
    private static String buttLEFT = DEFAULT_LEFT_BUTTON;
    private static String buttRIGHT = DEFAULT_RIGHT_BUTTON;
    private static String buttCHOSE = DEFAULT_CHOSE_BUTTON;



    public static void prepareActionHandlers() throws IOException {


        activeKeyHash = new HashSet<String>();
        graphic.theScene.setOnKeyPressed(new EventHandler<KeyEvent>()   //добавляем кнопки если нажаты и не были в хэше до этого
        {
            @Override
            public void handle(KeyEvent event)
            {

                if (!gettedSavedButtonSettings){
                    try {
                        loadButt();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    gettedSavedButtonSettings = true;
                }
                activeKeyHash.add(event.getCode().toString());
            }
        });

        graphic.theScene.setOnKeyReleased(new EventHandler<KeyEvent>()  //убираем кнопку если она отжата
        {
            @Override
            public void handle(KeyEvent event)
            {
                activeKeyHash.remove(event.getCode().toString());
            }
        });

    }

    private static String getPressedKey(){
        String[] activeKeyArr = activeKeyHash.toArray(new String[activeKeyHash.size()]);
        return activeKeyArr[0];

    }

    public static boolean pressedUP(){
        return activeKeyHash.contains(buttUP);
    }


    public static boolean pressedDOWN(){
        return activeKeyHash.contains(buttDOWN);
    }


    public static boolean pressedLEFT(){
        return activeKeyHash.contains(buttLEFT);
    }


    public static boolean pressedRIGHT(){
        return activeKeyHash.contains(buttRIGHT);
    }


    public static boolean pressedCHOSE(){
        return activeKeyHash.contains(buttCHOSE);
    }

    private static void saveButt(String buttName) throws FileNotFoundException {

        PrintWriter pw = new PrintWriter(BUTT_SETTING_FILE);
        if(buttName != "all"){
            if (buttName == "UP"){
                buttUP = getPressedKey();
            }
            if (buttName == "DOWN"){
                buttDOWN = getPressedKey();
            }
            if (buttName == "LEFT"){
                buttLEFT = getPressedKey();
            }
            if (buttName == "RIGHT"){
                buttRIGHT = getPressedKey();
            }
            if (buttName == "CHOSE"){
                buttCHOSE = getPressedKey();
            }
        }


        pw.println(buttUP);
        pw.println(buttDOWN);
        pw.println(buttLEFT);
        pw.println(buttRIGHT);
        pw.println(buttCHOSE);

        pw.close();

        gettedSavedButtonSettings = false;

    }

    private static void loadButt() throws IOException{
        try {

            Scanner sc = new Scanner(BUTT_SETTING_FILE);

            if (sc.hasNextLine()){
                buttUP = sc.nextLine();
            }


            if (sc.hasNextLine()){
                buttDOWN = sc.nextLine();
            }


            if (sc.hasNextLine()){
                buttLEFT = sc.nextLine();
            }


            if (sc.hasNextLine()){
                buttRIGHT = sc.nextLine();
            }


            if (sc.hasNextLine()){
                buttCHOSE = sc.nextLine();
            }

            gettedSavedButtonSettings = true;

            sc.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("создаю новый файл параметров кнопок");
            saveButt("all");
        }

    }

}


