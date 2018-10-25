package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Menu {// todo еще больше поменять !
    private static final Image BUTTON_IMG_0 = new Image( "/resources/menu/button_0.png");   //выбор картинок для кнопок
    private static final Image BUTTON_IMG_1 = new Image( "/resources/menu/button_1.png");
    private static final Image BUTTON_IMG_2 = new Image( "/resources/menu/button_2.png");

    private static final Image LOGO_IMG = new Image( "/resources/menu/logo.png");           //логотип

    private static final Image BACKGROUND_IMG = new Image( "/resources/menu/background.png");           //задник



    private static final String VERSION = "Ver: 0.3";                        //версия игры в main menu
    private static final String VERSION_FONT_NAME = "Arial";                //задаем константы для шрифта для надписи версия
    private static final int VERSION_FONT_SIZE = 25;
    private static final FontWeight FONT_WEIGHT = FontWeight.BOLD;

    private static final int BUTT_HEIGH = (int) BUTTON_IMG_0.getHeight();   //размеры кнопок
    private static final int BUTT_WIDTH = (int) BUTTON_IMG_0.getWidth();

    private static final int LOGO_HEIGH = (int) LOGO_IMG.getHeight();
    private static final int LOGO_WIDTH = (int) LOGO_IMG.getWidth();



    private static final int BACKGROUND_HEIGH = 16;
    private static final int BACKGROUND_WIDTH = 16;


    private static int butt0PosX;       //Левая верхнаяя позиция всех кнопок, адаптируется под размер окна
    private static int butt0PosY;

    private static int logoPosX;
    private static int logoPosY;

    private static int buttMove = (int) BUTTON_IMG_0.getHeight();

    private static byte chosenButt = 0;

    private static long prevTime;   //cчетчик для корректного переключения кнопок

    private static GraphicsContext gc = graphic.canvas.getGraphicsContext2D();


    public static void drawMenu(long currentNanoTime){

        if (currentNanoTime - prevTime >= 100000000) {      //задержка с помощью счетчика чтобы кнопки переключались корректно (не с сумашедшой скоростью)
            gc.clearRect(0, 0, graphic.theScene.getWidth(), graphic.theScene.getHeight());

            for (int i = 0; i < graphic.theScene.getWidth() ; i+=BACKGROUND_WIDTH) {
                for (int j = 0; j < graphic.theScene.getHeight() ; j+=BACKGROUND_HEIGH) {
                gc.drawImage(BACKGROUND_IMG, i,j);

                }
                
            }

            if (KeyManager.activeKeyHash.contains("DOWN") && chosenButt != 2) {
                chosenButt++;
                prevTime = currentNanoTime;
            } else if (KeyManager.activeKeyHash.contains("DOWN") && chosenButt == 2) {
                chosenButt = 0;
                prevTime = currentNanoTime;
            } else if (KeyManager.activeKeyHash.contains("UP") && chosenButt != 0) {
                chosenButt--;
                prevTime = currentNanoTime;
            } else if (KeyManager.activeKeyHash.contains("UP") && chosenButt == 0) {
                chosenButt = 2;
                prevTime = currentNanoTime;
            }

            logoPosX = ((int) graphic.theScene.getWidth() - LOGO_WIDTH) / 2;
            logoPosY = ((int) graphic.theScene.getHeight() - LOGO_HEIGH) / 2 - LOGO_HEIGH;

            gc.drawImage(LOGO_IMG, logoPosX, logoPosY);


            butt0PosX = ((int) graphic.theScene.getWidth() - BUTT_WIDTH) / 2;
            butt0PosY = ((int) graphic.theScene.getHeight() - BUTT_HEIGH) / 2;

            if (chosenButt == 0) {
                gc.drawImage(BUTTON_IMG_2, butt0PosX, butt0PosY);
            } else {
                gc.drawImage(BUTTON_IMG_0, butt0PosX, butt0PosY);
            }

            if (chosenButt == 1) {
                gc.drawImage(BUTTON_IMG_2, butt0PosX, butt0PosY + BUTT_HEIGH + buttMove);
            } else {
                gc.drawImage(BUTTON_IMG_0, butt0PosX, butt0PosY + BUTT_HEIGH + buttMove);
            }

            if (chosenButt == 2) {
                gc.drawImage(BUTTON_IMG_2, butt0PosX, butt0PosY + 2 * (BUTT_HEIGH + buttMove));
            } else {
                gc.drawImage(BUTTON_IMG_0, butt0PosX, butt0PosY + 2 * (BUTT_HEIGH + buttMove));
            }

            Font theFont = Font.font(VERSION_FONT_NAME, FONT_WEIGHT, VERSION_FONT_SIZE);
            gc.setFont(theFont);



            gc.fillText(VERSION, graphic.theScene.getWidth() - 0.5 * VERSION_FONT_SIZE * VERSION.length(), graphic.theScene.getHeight() - 0.5 * VERSION_FONT_SIZE);

            if(KeyManager.activeKeyHash.contains("ENTER")){
                if (chosenButt == 0) graphic.mode = 1;
                if (chosenButt == 1) graphic.mode = 2;
                if (chosenButt == 2) graphic.mode = 3;
            }
        }
    }
}
