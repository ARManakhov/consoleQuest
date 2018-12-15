package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FurnManager {

    private static GraphicsContext gc = graphic.interfaceLayer.getGraphicsContext2D();

    private static final String VERSION_FONT_NAME = "Arial";                    //задаем константы для шрифта для надписи версия
    private static final int FONT_SIZE = 25;                            //размер текста
    private static final FontWeight FONT_WEIGHT = FontWeight.BOLD;              //название шрифта


    public  static void draw (long curentNanoTime, int curentMap){
        int PlayerX = (int)( Player.getPlayer().getRealXPos() + MapManager.getBlockSize() / 2) / 32;
        int PlayerY = (int)( Player.getPlayer().getRealYPos() + MapManager.getBlockSize() / 2) / 32;
        int dir = (int) PlayerManager.getInstance().dir;
        Furniture furn;


        if(dir == 0){ //вправо
            PlayerX ++;
        }
        if(dir == 1){ //вниз
            PlayerY ++;
        }
        if(dir == 2){ //влево
            PlayerX --;
        }
        if(dir == 3){ //вверх
            PlayerY --;
        }
        if(Map.maps[curentMap].furnitureMap[PlayerY][PlayerX] != 0){

            furn = Furniture.getFurnitureOnPos(PlayerY,PlayerX,curentMap);
            if(furn != null){
                String[] text = furn.text;
                if(text.length > 0 ){
                    System.out.println(text[0] + "," + text[0].length());
                    System.out.println("OK");
                    Font theFont = Font.font(VERSION_FONT_NAME, FONT_WEIGHT, FONT_SIZE);
                    gc.setFont(theFont);
                    gc.fillText(text[0], graphic.theScene.getWidth() - 0.5 * FONT_SIZE * text[0].length(), graphic.theScene.getHeight() - 0.5 * FONT_SIZE);}
            }
        }


    }
}
