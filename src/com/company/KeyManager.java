package com.company;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * класс в котором обрабатываются вводы пользователя (мышь, клава)
 */
public class KeyManager {

    private static HashSet<String> activeKeyHash;                   //hashset со всеми нажатыми в данный момент кнопками
    private static HashSet<String> activeMouseHash;                   //hashset со всеми нажатыми в данный момент кнопками


    private static boolean gettedSavedButtonSettings = false;       //флаг который определяет , загружено ли последнее сохранения с параметрами кнопками


    private static final File BUTT_SETTING_FILE = new File("saves","keys"); // файл с сохранениями конопок

                                                                    // далее описаны кнопки которые будут применены если не удалось загрузить с файла
    private static final String DEFAULT_UP_BUTTON = "W";
    private static final String DEFAULT_DOWN_BUTTON = "S";
    private static final String DEFAULT_LEFT_BUTTON = "A";
    private static final String DEFAULT_RIGHT_BUTTON = "D";
    private static final String DEFAULT_CHOSE_BUTTON = "ENTER";
    private static final String DEFAULT_ACTION_BUTTON = "E";

                                                                    // переменные с информацией о выбраных кнопках ( на которые программа реагирует)
    private static String buttUP = DEFAULT_UP_BUTTON;
    private static String buttDOWN = DEFAULT_DOWN_BUTTON;
    private static String buttLEFT = DEFAULT_LEFT_BUTTON;
    private static String buttRIGHT = DEFAULT_RIGHT_BUTTON;
    private static String buttCHOSE = DEFAULT_CHOSE_BUTTON;
    private static String buttAction = DEFAULT_ACTION_BUTTON;

    public static String getButtAction() {
        return buttAction;
    }

    private static double mouseXPos;
    private static double mouseYPos;

    /**
     *
     * @return озвращает хэш кнопок
     */
    public static HashSet<String> getActiveKeyHash() {
        return activeKeyHash;
    }

    /**
     * метод который загружает в activeKeyHash текущее состояние клавиатуры(какие кнопуи нааты)
     * @throws IOException если не нашелся файл параметров
     */
    public static void prepareActionHandlers() {


        activeKeyHash = new HashSet<String>();
        activeMouseHash = new HashSet<String>();

        //добавляем кнопки если нажаты и не были в хэше до этого
        graphic.theScene.setOnKeyPressed(event -> {

            if (!gettedSavedButtonSettings){
                loadButt();
                gettedSavedButtonSettings = true;
            }

            activeKeyHash.add(event.getCode().toString());
        });

        //убираем кнопку если она отжата
        graphic.theScene.setOnKeyReleased(event -> activeKeyHash.remove(event.getCode().toString()));

        graphic.theScene.setOnMouseMoved(
                event -> {
                    mouseXPos = event.getX();
                    mouseYPos = event.getY();
                });

        graphic.theScene.setOnMouseDragged(
                event -> {
                    mouseXPos = event.getX();
                    mouseYPos = event.getY();
                });



        graphic.theScene.setOnMousePressed(event -> activeMouseHash.add(event.getButton().toString()));

        graphic.theScene.setOnMouseReleased(event -> activeMouseHash.remove(event.getButton().toString()));





    }

    /**
     * метод для получение данных об одной нажатой кнопке (первая в хэше)
     * @return возвращает нажатую кнопку
     */
    public static String getPressedKey(){
        if(alredyPrstBut) {
            if (activeKeyHash.size() == 0) {
                alredyPrstBut = false;
            } else {
                return "wait";
            }
        }else{
            if (activeKeyHash.size() == 0 ){
                return "wait";
            } else {
                System.out.println("ok");
                alredyPrstBut = true;
                String[] prstButt = activeKeyHash.toArray(new String[activeKeyHash.size()]);
                return prstButt[0];
            }
        }
        return "wait";

    }

    static boolean alredyPrstBut = true;


    /**
     * сохраняет текущие рабочие и измененные кнопки
     * @param buttName кнопка которую надо обновить ("all" если надо обновить все, не ждет нажатия кнопки)
     */
    public static boolean saveButt(String buttName) {
        boolean savedCpaturedBut = false;
        if (!buttName.equals("all")) {
            String currentButt = getPressedKey();
            if (!currentButt.equals("wait")){
                savedCpaturedBut = true;
                if (buttName.equals("UP")) {
                    buttUP = currentButt;
                }
                if (buttName.equals("DOWN")) {
                    buttDOWN = currentButt;
                }
                if (buttName.equals("LEFT")) {
                    buttLEFT = currentButt;
                }
                if (buttName.equals("RIGHT")) {
                    buttRIGHT = currentButt;
                }
                if (buttName.equals("CHOSE")) {
                    buttCHOSE = currentButt;
                }
            } else{
                savedCpaturedBut = false;
            }

        }


        try {
            PrintWriter pw = new PrintWriter(BUTT_SETTING_FILE);

            pw.println(buttUP);
            pw.println(buttDOWN);
            pw.println(buttLEFT);
            pw.println(buttRIGHT);
            pw.println(buttCHOSE);

            pw.close();
            Logger.getLoger().addLogg("Параметры кнопок сохранены");
        } catch (FileNotFoundException e) {
            Logger.getLoger().addLogg("Параметры кнопок не сохранены");
        }
        return savedCpaturedBut;
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
        } else
        if (buttCode == "ACTION"){
            return activeKeyHash.contains(buttAction);
        } else
            return false;

    }


    /**
     * метод который загружает с файла кнопки
     */
    private static void loadButt(){

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
            Logger.getLoger().addLogg("параметры кнопок загружены");
        }
        catch (FileNotFoundException e) {
            Logger.getLoger().addLogg("параметры кнопок не были загружены, применяем стандартные настройки");
            saveButt("all");
        }

    }

    /**
     *
     * @return текущая позиция мыши по X
     */
    public static double getMouseXPos(){
        return mouseXPos;
    }


    /**
     *
     * @return текущая позиция мыши по Y
     */
    public static double getMouseYPos(){
        return mouseYPos;
    }

    /**
     *
     * @param buttCode код кнопки мыши ( PRIMARY, MIDDLE, SECONDARY)
     * @return если нажата то true иначе false
     */
    public static boolean getMousePresetButt(String buttCode){
        return activeMouseHash.contains(buttCode);
    }

}


