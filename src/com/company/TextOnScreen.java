package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class TextOnScreen {

    private static final String VERSION_FONT_NAME = "Arial";                    //задаем константы для шрифта для надписи версия
    private static final int FONT_SIZE = 25;                            //размер текста
    private static final FontWeight FONT_WEIGHT = FontWeight.BOLD;              //название шрифта

    static void drawText(String text,GraphicsContext gc){
        Font theFont = Font.font(VERSION_FONT_NAME, FONT_WEIGHT, FONT_SIZE);
        gc.setFont(theFont);

        double textW = 0.6*FONT_SIZE*text.length();
        double textH = 1.5* FONT_SIZE;
        double textPosX = 0.5 *(graphic.theScene.getWidth() - textW);
        double boxPosY = graphic.theScene.getHeight() - 2 * FONT_SIZE;
        double textPosY = graphic.theScene.getHeight() - 1 * FONT_SIZE;

        gc.setFill(Color.web("#FFFFFF"));
        gc.fillRect(textPosX - 10, boxPosY - 5, textW + 20 , textH + 10);
        gc.setFill(Color.web("#000000"));
        gc.fillRect(textPosX - 5, boxPosY, textW + 10 , textH );
        gc.setFill(Color.web("#FFFFFF"));
        gc.fillText(text, textPosX, textPosY);
    }


    static void drawText(String[] text,GraphicsContext gc){
        if(text.length == 1){
            drawText(text[0],gc);
        } else{


        Font theFont = Font.font(VERSION_FONT_NAME, FONT_WEIGHT, FONT_SIZE);
        gc.setFont(theFont);
        int textCount = text.length;
        int maxLength = 0;
        for (int i = 0; i < textCount; i++) {
            if(text[i].length() > maxLength){
                maxLength = text[i].length();
            }
        }

        double textW = 0.6*FONT_SIZE*maxLength;
        double textH = 1.5* FONT_SIZE;
        double boxH = 1.5* FONT_SIZE * textCount;
        double textPosX = 0.5 *(graphic.theScene.getWidth() - textW);
        double boxPosY = graphic.theScene.getHeight() -  FONT_SIZE *(textCount + 2);
        double textPosY = graphic.theScene.getHeight() - 1 * FONT_SIZE;

        gc.setFill(Color.web("#FFFFFF"));
        gc.fillRect(textPosX - 10, boxPosY - 5, textW + 20 , boxH + 10);
        gc.setFill(Color.web("#000000"));
        gc.fillRect(textPosX - 5, boxPosY, textW + 10 , boxH );
        gc.setFill(Color.web("#FFFFFF"));
        for (int i = 0; i < textCount; i++) {
            gc.fillText(text[i], textPosX, textPosY - textH*(textCount - i -1));
        }
    }
    }

}
