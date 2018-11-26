package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * операции и отрисовки в главном меню
 */
public class MainMenu {// todo еще больше поменять !
                                                                                                    //спрайты кнопок
    private static final Image BUTTON_IMG_0 = new Image( "/resources/menu/button_0.png");       //обычная
    private static final Image BUTTON_IMG_1 = new Image( "/resources/menu/button_1.png");       //выбранная
    private static final Image BUTTON_IMG_2 = new Image( "/resources/menu/button_2.png");       //нажатая

    private static final Image LOGO_IMG = new Image( "/resources/menu/logo.png");               //логотип
    private static final Image BACKGROUND_IMG = new Image( "/resources/menu/background.png");   //задник меню

    private static final String VERSION = "Ver: 0.3";                           //версия игры в main menu
    private static final String VERSION_FONT_NAME = "Arial";                    //задаем константы для шрифта для надписи версия
    private static final int VERSION_FONT_SIZE = 25;                            //размер текста
    private static final FontWeight FONT_WEIGHT = FontWeight.BOLD;              //название шрифта

                                                                                //размеры кнопок
    private static final int BUTT_HEIGHT = (int) BUTTON_IMG_0.getHeight();      //выстоа
    private static final int BUTT_WIDTH = (int) BUTTON_IMG_0.getWidth();        //ширина

                                                                                //размер логотипа
    private static final int LOGO_HEIGHT = (int) LOGO_IMG.getHeight();          //высота
    private static final int LOGO_WIDTH = (int) LOGO_IMG.getWidth();            //ширина

                                                                                //размеры спрайтов задника
    private static final int BACKGROUND_HEIGHT = 16;                            //высота
    private static final int BACKGROUND_WIDTH = 16;                             //ширина

                                                                                //позиция первно кнопки (остальные подстраиваются под нее)
    private static int butt0PosX;
    private static int butt0PosY;
                                                                                //позиция логотива
    private static int logoPosX;
    private static int logoPosY;

    private static int buttMove = (int) BUTTON_IMG_0.getHeight();               //расстояние между кнопками

    public static byte chosenButt = 0;                                         //выбранная кнопка в меню

    private static long prevTime;                                               //прошлое время нажатие на кнопку (нужен корректного переключения кнопок с клавиатуры)

    private static GraphicsContext gc = graphic.interfaceLayer.getGraphicsContext2D();


    /**
     * метод отрисовывающий главное меню
     * @param currentNanoTime
     */
    public static void drawMenu(long currentNanoTime){
        if (currentNanoTime - prevTime >= 100000000) {      //задержка с помощью счетчика чтобы кнопки переключались корректно (не с сумашедшой скоростью)
            gc.clearRect(0, 0, graphic.theScene.getWidth(), graphic.theScene.getHeight());

            for (int i = 0; i < graphic.theScene.getWidth() ; i+=BACKGROUND_WIDTH) {
                for (int j = 0; j < graphic.theScene.getHeight() ; j+= BACKGROUND_HEIGHT) {
                gc.drawImage(BACKGROUND_IMG, i,j);

                }
                
            }

            if (KeyManager.pressedButt("DOWN") && chosenButt != 2) {
                chosenButt++;
                prevTime = currentNanoTime;
            } else if (KeyManager.pressedButt("DOWN") && chosenButt == 2) {
                chosenButt = 0;
                prevTime = currentNanoTime;
            } else if (KeyManager.pressedButt("UP") && chosenButt != 0) {
                chosenButt--;
                prevTime = currentNanoTime;
            } else if (KeyManager.pressedButt("UP") && chosenButt == 0) {
                chosenButt = 2;
                prevTime = currentNanoTime;
            }

            logoPosX = ((int) graphic.theScene.getWidth() - LOGO_WIDTH) / 2;
            logoPosY = ((int) graphic.theScene.getHeight() - LOGO_HEIGHT) / 2 - LOGO_HEIGHT;

            gc.drawImage(LOGO_IMG, logoPosX, logoPosY);


            butt0PosX = ((int) graphic.theScene.getWidth() - BUTT_WIDTH) / 2;
            butt0PosY = ((int) graphic.theScene.getHeight() - BUTT_HEIGHT) / 2;

            if (chosenButt == 0) {
                gc.drawImage(BUTTON_IMG_2, butt0PosX, butt0PosY);
            } else {
                gc.drawImage(BUTTON_IMG_0, butt0PosX, butt0PosY);
            }

            if (chosenButt == 1) {
                gc.drawImage(BUTTON_IMG_2, butt0PosX, butt0PosY + BUTT_HEIGHT + buttMove);
            } else {
                gc.drawImage(BUTTON_IMG_0, butt0PosX, butt0PosY + BUTT_HEIGHT + buttMove);
            }

            if (chosenButt == 2) {
                gc.drawImage(BUTTON_IMG_2, butt0PosX, butt0PosY + 2 * (BUTT_HEIGHT + buttMove));
            } else {
                gc.drawImage(BUTTON_IMG_0, butt0PosX, butt0PosY + 2 * (BUTT_HEIGHT + buttMove));
            }

            Font theFont = Font.font(VERSION_FONT_NAME, FONT_WEIGHT, VERSION_FONT_SIZE);
            gc.setFont(theFont);




            gc.fillText(VERSION, graphic.theScene.getWidth() - 0.5 * VERSION_FONT_SIZE * VERSION.length(), graphic.theScene.getHeight() - 0.5 * VERSION_FONT_SIZE);

            if(KeyManager.pressedButt("CHOSE")){
                if (chosenButt == 0) graphic.mode = 1;
                if (chosenButt == 1) graphic.mode = 2;
                if (chosenButt == 2) graphic.mode = 3;
                graphic.interfaceLayer.getGraphicsContext2D().clearRect(0,0,graphic.theScene.getWidth(),graphic.theScene.getHeight());
            }
        }
    }
}
