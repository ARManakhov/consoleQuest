package com.company;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

/**
 * класс в котором обрабатываются вводы пользователя (мышь, клава)
 */
public class KeyManager {

    private static HashSet<String> activeKeyHash;                    //hashset со всеми нажатыми в данный момент кнопками

    private static boolean gettedSavedButtonSettings = false;       //флаг который определяет , загружено ли последнее сохранения с параметрами кнопками


    private static final File BUTT_SETTING_FILE = new File("saves","keys"); // файл с сохранениями конопок

                                                                    // далее описаны кнопки которые будут применены если не удалось загрузить с файла
    private static final String DEFAULT_UP_BUTTON = "UP";
    private static final String DEFAULT_DOWN_BUTTON = "DOWN";
    private static final String DEFAULT_LEFT_BUTTON = "LEFT";
    private static final String DEFAULT_RIGHT_BUTTON = "RIGHT";
    private static final String DEFAULT_CHOSE_BUTTON = "ENTER";

                                                                    // переменные с информацией о выбраных кнопках ( на которые программа реагирует)
    private static String buttUP = DEFAULT_UP_BUTTON;
    private static String buttDOWN = DEFAULT_DOWN_BUTTON;
    private static String buttLEFT = DEFAULT_LEFT_BUTTON;
    private static String buttRIGHT = DEFAULT_RIGHT_BUTTON;
    private static String buttCHOSE = DEFAULT_CHOSE_BUTTON;


    /**
     * метод который загружает в activeKeyHash текущее состояние клавиатуры(какие кнопуи нааты)
     * @throws IOException если не нашелся файл параметров
     */
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

    /**
     * метод для получение данных об одной нажатой кнопке (первая в хэше)
     * @return возвращает нажатую кнопку
     */
    private static String getPressedKey(){
        String[] activeKeyArr = activeKeyHash.toArray(new String[activeKeyHash.size()]);
        if (activeKeyArr.length >0){
            return activeKeyArr[0];
        } else {
            return "wait";          //todo прикрутить в сохроняльщик кнопок ниже
        }
    }

    /**
     *
     * @param buttCode кнопка которую необходимо проверить
     * @return состояние кнопки (true если нажата)
     */
    public static boolean pressedButt(String buttCode){
        if (buttCode == "UP"){
            return activeKeyHash.contains(buttUP);
        }else
        if (buttCode == "DOWN"){
            return activeKeyHash.contains(buttDOWN);
        }else
        if (buttCode == "LEFT"){
            return activeKeyHash.contains(buttLEFT);
        }else
        if (buttCode == "RIGHT"){
            return activeKeyHash.contains(buttRIGHT);
        }else
        if (buttCode == "CHOSE"){
            return activeKeyHash.contains(buttCHOSE);
        } else return false;

    }

    /**
     * сохраняет текущие рабочие и измененные кнопки
     * @param buttName кнопка которую надо обновить ("all" если надо обновить все, не ждет нажатия кнопки)
     * @throws FileNotFoundException если не нашелся файл параметров
     */
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

    /**
     * метод который загружает с файла кнопки
     * @throws IOException  если файла нету выводить что не удалось загрузить параметры и создать стандартый файл с стандартными кнопками
     */
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


