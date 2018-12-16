package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SettingMenu implements IMenu {
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
            BUTTON_INPUT_IMG = new Image( new FileInputStream(new File("./resources/menu/button_input.png ")));

            LOGO_IMG = new Image( new FileInputStream(new File("./resources/menu/logo.png")));               //логотип
            BACKGROUND_IMG = new Image( new FileInputStream(new File("./resources/menu/background.png")));   //задник меню

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // заголовок и некоторые константы для всех надписей
    private static final String SETTING = "Setting";                           //надпись
    private static final String SETTING_FONT_NAME = "Arial";                    //задаем константы для шрифта для надписи
    private static final int SETTING_FONT_SIZE = 60;                            //размер текста
    private static final FontWeight FONT_WEIGHT = FontWeight.BOLD;              //название шрифта

    // надписи кнопок
    private static final String A = "UP";                                //надпись
    private static final int A_FONT_SIZE = 17;                            //размер текста

    private static final String B = "DOWN";                           //надпись
    private static final int B_FONT_SIZE = 17;                            //размер текста

    private static final String C = "RIGHT";                           //надпись
    private static final int C_FONT_SIZE = 17;                            //размер текста

    private static final String D = "LEFT";                           //надпись
    private static final int D_FONT_SIZE = 17;                            //размер текста

    //параметры для выбора клавиши
    private static int mode = 2;
    private static int returnMode = 0;

    //размеры кнопок
    private static final int BUTT_HEIGHT = (int) BUTTON_IMG_0.getHeight();      //выстоа
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
    private static byte choseButt = 0;

    //прошлое время нажатия на кнопку (нужен для корректного переключения кнопок с клавиатуры)
    private static long prevTimeButt;
    private static long prevTimeChose;

    // создание объекта
    private static GraphicsContext gc = graphic.interfaceLayer.getGraphicsContext2D();

    // атрибут для задержки
    private static boolean firstCall = true;

    // метод для отправки параметра вывода (нужен, чтобы, нажав ESC, из настройки вылетало либо в главное меню, либо в доп. меню
    public static void setReturnMode(int returnMode) {
        SettingMenu.returnMode = returnMode;
    }

    /**
     * метод отрисовывающий настройки
     *
     * @param currentNanoTime
     */

    @Override
    public void draw(long currentNanoTime) {

        // для задержки, чтобы при переключении на настройки сразу не нажималась кнопка выбора
        if (firstCall) {
            prevTimeChose = currentNanoTime;
            firstCall = false;
            mode = 2;
        }

        gc.setFill(Color.web("#000000"));


        if (mode == 2) {

            //задержка с помощью счетчика, чтобы кнопки переключались корректно (не с сумасашедшей скоростью)
            if (currentNanoTime - prevTimeButt >= 100000000) {
                gc.clearRect(0, 0, graphic.theScene.getWidth(), graphic.theScene.getHeight());

                for (int i = 0; i < graphic.theScene.getWidth(); i += BACKGROUND_WIDTH) {
                    for (int j = 0; j < graphic.theScene.getHeight(); j += BACKGROUND_HEIGHT) {
                        gc.drawImage(BACKGROUND_IMG, i, j);

                    }
                }

                // переключение между кнопками
                if (KeyManager.pressedButt("DOWN") && choseButt != 4) {
                    choseButt++;
                    prevTimeButt = currentNanoTime;
                } else if (KeyManager.pressedButt("DOWN") && choseButt == 4) {
                    choseButt = 0;
                    prevTimeButt = currentNanoTime;
                } else if (KeyManager.pressedButt("UP") && choseButt != 0) {
                    choseButt--;
                    prevTimeButt = currentNanoTime;
                } else if (KeyManager.pressedButt("UP") && choseButt == 0) {
                    choseButt = 4;
                    prevTimeButt = currentNanoTime;
                }




                // располагаем изначальную позицию кнопки
                butt0PosX = ((int) graphic.theScene.getWidth() - BUTT_WIDTH) / 2;
                butt0PosY = ((int) graphic.theScene.getHeight() / 2 - 80);

                // рисуем кнопки
                if (choseButt == 0) {
                    gc.drawImage(BUTTON_IMG_2, butt0PosX, butt0PosY);
                } else {
                    gc.drawImage(BUTTON_IMG_0, butt0PosX, butt0PosY);
                }

                if (choseButt == 1) {
                    gc.drawImage(BUTTON_IMG_2, butt0PosX, butt0PosY + BUTT_HEIGHT + buttMove);
                } else {
                    gc.drawImage(BUTTON_IMG_0, butt0PosX, butt0PosY + BUTT_HEIGHT + buttMove);
                }

                if (choseButt == 2) {
                    gc.drawImage(BUTTON_IMG_2, butt0PosX, butt0PosY + 2 * (BUTT_HEIGHT + buttMove));
                } else {
                    gc.drawImage(BUTTON_IMG_0, butt0PosX, butt0PosY + 2 * (BUTT_HEIGHT + buttMove));
                }

                if (choseButt == 3) {
                    gc.drawImage(BUTTON_IMG_2, butt0PosX, butt0PosY + 3 * (BUTT_HEIGHT + buttMove));
                } else {
                    gc.drawImage(BUTTON_IMG_0, butt0PosX, butt0PosY + 3 * (BUTT_HEIGHT + buttMove));
                }

                // константа для заголовка
                Font theFont = Font.font(SETTING_FONT_NAME, FONT_WEIGHT, SETTING_FONT_SIZE);
                gc.setFont(theFont);

                // пишем заголовок
                gc.fillText(SETTING, butt0PosX + (BUTT_WIDTH - SETTING_FONT_SIZE * A.length()) / 2 - 40, butt0PosY + SETTING_FONT_SIZE - 90);

                // для всех надписей кнопок константа
                Font theFont1 = Font.font(SETTING_FONT_NAME, FONT_WEIGHT, A_FONT_SIZE);
                gc.setFont(theFont1);

                // пишем названия кнопок
                gc.fillText(A, butt0PosX + 0.95 * (BUTT_WIDTH - A_FONT_SIZE * A.length()) / 2, butt0PosY + A_FONT_SIZE - 1);
                gc.fillText(B, butt0PosX + 1.1 * (BUTT_WIDTH - B_FONT_SIZE * B.length()) / 2, butt0PosY + BUTT_HEIGHT + buttMove + A_FONT_SIZE - 1);
                gc.fillText(C, butt0PosX + 1.1 * (BUTT_WIDTH - C_FONT_SIZE * C.length()) / 2 + 10, butt0PosY + 2 * (BUTT_HEIGHT + buttMove) + A_FONT_SIZE - 1);
                gc.fillText(D, butt0PosX + 0.95 * BUTT_WIDTH / 2 - D_FONT_SIZE * D.length() / 2 + 14, butt0PosY + 3 * (BUTT_HEIGHT + buttMove) + A_FONT_SIZE - 1);
            }

            // выход в главное меню на ESC
            if (KeyManager.pressedButt("ESCAPE")) {
                graphic.mode = (byte) returnMode;
            }
            // выбор кнопок с задержкой и отображение надписи о праве выбора
            if (KeyManager.pressedButt("CHOSE") && prevTimeChose + 100000000 < currentNanoTime) {
                if (choseButt == 0) {
                    gc.clearRect(butt0PosX, butt0PosY, BUTT_WIDTH, BUTT_HEIGHT);
                    gc.drawImage(BUTTON_INPUT_IMG, butt0PosX, butt0PosY);
                    mode = 5;
                }
                if (choseButt == 1) {
                    gc.clearRect(butt0PosX, butt0PosY + BUTT_HEIGHT + buttMove, BUTT_WIDTH, BUTT_HEIGHT);
                    gc.drawImage(BUTTON_INPUT_IMG, butt0PosX, butt0PosY + BUTT_HEIGHT + buttMove);
                    mode = 6;
                }
                if (choseButt == 2) {
                    gc.clearRect(butt0PosX, butt0PosY + 2 * (BUTT_HEIGHT + buttMove), BUTT_WIDTH, BUTT_HEIGHT);
                    gc.drawImage(BUTTON_INPUT_IMG, butt0PosX, butt0PosY + 2 * (BUTT_HEIGHT + buttMove));
                    mode = 7;
                }
                if (choseButt == 3) {
                    gc.clearRect(butt0PosX, butt0PosY + 3 * (BUTT_HEIGHT + buttMove), BUTT_WIDTH, BUTT_HEIGHT);
                    gc.drawImage(BUTTON_INPUT_IMG, butt0PosX, butt0PosY + 3 * (BUTT_HEIGHT + buttMove));
                    mode = 8;
                }
            }
        }
        //Сохранение кнопок
        if (mode == 5) {
            if (KeyManager.saveButt("UP")) {
                mode = 2;
            }
        }
        if (mode == 6) {
            if (KeyManager.saveButt("DOWN")) {
                mode = 2;
            }
        }
        if (mode == 7) {
            if (KeyManager.saveButt("RIGHT")) {
                mode = 2;
            }
        }
        if (mode == 8) {
            if (KeyManager.saveButt("LEFT")) {
                mode = 2;
            }
        }
    }
}







