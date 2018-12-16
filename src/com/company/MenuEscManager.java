package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MenuEscManager implements IMenu {
    private static  Image BUTTON_IMG_0 ;       //обычная
    private static  Image BUTTON_IMG_1 ;
    private static  Image BUTTON_IMG_2 ;
    private static  Image BUTTON_INPUT_IMG;

    private static Image LOGO_IMG ;               //логотип
    private static Image BACKGROUND_IMG ;   //задник меню

    static {
        try {
            BUTTON_IMG_0 = new Image( new FileInputStream(new File("./resources/menu/button_0.png")));
            BUTTON_IMG_1 = new Image( new FileInputStream(new File("./resources/menu/button_1.png")));
            BUTTON_IMG_2 = new Image( new FileInputStream(new File("./resources/menu/button_2.png")));

            BACKGROUND_IMG = new Image( new FileInputStream(new File("./resources/menu/background.png")));   //задник меню

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    // константы для всех надписей
    private static final String FONT_NAME = "Arial";
    private static final FontWeight FONT_WEIGHT = FontWeight.BOLD;

    // надписи кнопок
    private static final String A = "Продолжить игру";//надпись
    private static final int A_FONT_SIZE = 17;                            //размер текста

    private static final String B = "Настройки";                           //надпись
    private static final int B_FONT_SIZE = 17;                            //размер текста

    private static final String C = "В главное меню";                           //надпись
    private static final int C_FONT_SIZE = 17;                            //размер текста

    //размеры кнопок
    private static final int BUTT_HEIGHT = (int) BUTTON_IMG_0.getHeight();      //высота
    private static final int BUTT_WIDTH = (int) BUTTON_IMG_0.getWidth();        //ширина


    //размеры спрайтов задника
    private static final int BACKGROUND_HEIGHT = 544;                            //высота
    private static final int BACKGROUND_WIDTH = 544;                             //ширина

    //позиция первой кнопки (остальные подстраиваются под нее)
    private static int butt0PosX;
    private static int butt0PosY;

    //позиция рамки
    private static int framePosX;
    private static int framePosY;

    //расстояние между кнопками
    private static int buttMove = (int) BUTTON_IMG_0.getHeight();

    //выбранная кнопка в меню
    private static byte chosButt = 0;

    //прошлое время нажатия на кнопку (нужен для корректного переключения кнопок с клавиатуры)
    private static long prevTimeButt;

    // ...
    private static GraphicsContext gc = graphic.interfaceLayer.getGraphicsContext2D();

    /**
     * метод отрисовывающий меню НА ESC ВО ВРЕМЯ ИГРЫ
     *
     * @param currentNanoTime
     */

    @Override
    public void draw(long currentNanoTime) {

        gc.setFill(Color.web("#000000"));

        //задержка с помощью счетчика, чтобы кнопки переключались корректно (не с сумасшедшей скоростью)
        if (currentNanoTime - prevTimeButt >= 100000000) {
            gc.clearRect(0, 0, graphic.theScene.getWidth(), graphic.theScene.getHeight());

            for (int i = 0; i < graphic.theScene.getWidth(); i += BACKGROUND_WIDTH) {
                for (int j = 0; j < graphic.theScene.getHeight(); j += BACKGROUND_HEIGHT) {
                    gc.drawImage(BACKGROUND_IMG, i, j);
                }
            }

            // переключение между кнопками
            if (KeyManager.pressedButt("DOWN") && chosButt != 2) {
                chosButt++;
                prevTimeButt = currentNanoTime;
            } else if (KeyManager.pressedButt("DOWN") && chosButt == 2) {
                chosButt = 0;
                prevTimeButt = currentNanoTime;
            } else if (KeyManager.pressedButt("UP") && chosButt != 0) {
                chosButt--;
                prevTimeButt = currentNanoTime;
            } else if (KeyManager.pressedButt("UP") && chosButt == 0) {
                chosButt = 2;
                prevTimeButt = currentNanoTime;
            }



            // располагаем изначальную позицию кнопки
            butt0PosX = ((int) graphic.theScene.getWidth() - BUTT_WIDTH) / 2;
            butt0PosY = ((int) graphic.theScene.getHeight()) / 2;

            // рисуем кнопки
            if (chosButt == 0) {
                gc.drawImage(BUTTON_IMG_2, butt0PosX, butt0PosY);
            } else {
                gc.drawImage(BUTTON_IMG_0, butt0PosX, butt0PosY);
            }

            if (chosButt == 1) {
                gc.drawImage(BUTTON_IMG_2, butt0PosX, butt0PosY + BUTT_HEIGHT + buttMove);
            } else {
                gc.drawImage(BUTTON_IMG_0, butt0PosX, butt0PosY + BUTT_HEIGHT + buttMove);
            }

            if (chosButt == 2) {
                gc.drawImage(BUTTON_IMG_2, butt0PosX, butt0PosY + 2 * (BUTT_HEIGHT + buttMove));
            } else {
                gc.drawImage(BUTTON_IMG_0, butt0PosX, butt0PosY + 2 * (BUTT_HEIGHT + buttMove));
            }

            // для всех надписей константа
            Font theFont1 = Font.font(FONT_NAME, FONT_WEIGHT, A_FONT_SIZE);
            gc.setFont(theFont1);

            // пишем названия кнопок
            gc.fillText(A, butt0PosX - (BUTT_WIDTH - A_FONT_SIZE * A.length()) / 2, butt0PosY + A_FONT_SIZE - 1);
            gc.fillText(B, butt0PosX + 1.1 * (BUTT_WIDTH - B_FONT_SIZE * B.length()) / 2 + 25, butt0PosY + BUTT_HEIGHT + buttMove + A_FONT_SIZE - 1);
            gc.fillText(C, butt0PosX - (BUTT_WIDTH - C_FONT_SIZE * C.length()) / 2 + 20, butt0PosY + 2 * (BUTT_HEIGHT + buttMove) + A_FONT_SIZE - 1);

            // выбор кнопок
            if (KeyManager.pressedButt("CHOSE")) {
                if (chosButt == 0) graphic.mode = 1;
                if (chosButt == 1) {
                    SettingMenu.setReturnMode(1);
                    graphic.mode = 2;
                }
                if (chosButt == 2) graphic.mode = 0;
            }
        }
    }
}