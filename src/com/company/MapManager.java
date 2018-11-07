package com.company;

import javafx.animation.PathTransition;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.*;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

/**
 * класс который работает с картой
 */
public class MapManager {

    private static final int BLOCK_SIZE = 32;                                       //размер каждой текстуры

    private static GraphicsContext gc = graphic.mapLayer.getGraphicsContext2D();

                                                                                    //координаты расположения блока[0][0] на экране
    private static double currentXPos = 0;
    private static double currentYPos = 0;
    private static double currentXMove = 0;
    private static double currentYMove = 0;

    private static boolean firstCall = true;


    /**
     *
     * @param currentNanoTime текущее время в наносекундах
     */
    public static void drawMap(long currentNanoTime){

        if(firstCall){

            Translate tr = new Translate();
                tr.setY(-currentYMove);
                tr.setX(-currentXMove);
            graphic.mapLayer.getTransforms().addAll(tr);

            currentYMove = 0;
            currentXMove = 0;

            currentXPos = graphic.theScene.getWidth() / 2 - BLOCK_SIZE* Maps.worldMap[graphic.currentMapNumber].spawnPosX;
            currentYPos = graphic.theScene.getHeight() / 2 - BLOCK_SIZE* Maps.worldMap[graphic.currentMapNumber].spawnPosY;
            gc.clearRect(0, 0, graphic.theScene.getWidth(), graphic.theScene.getHeight());

            for (int i = 0; i < Maps.worldMap[graphic.currentMapNumber].groundMap.length ; i++) {
                for (int j = 0; j < Maps.worldMap[graphic.currentMapNumber].groundMap[i].length ; j++) {
                    gc.drawImage(Block.blockTexture[Maps.worldMap[graphic.currentMapNumber].groundMap[i][j]],j*BLOCK_SIZE+currentXPos,i*BLOCK_SIZE+currentYPos);
                }
            }

            firstCall = false;

        }
        System.out.println("x = "+ currentXMove + " y= " + currentYMove);

    }

    /**
     * метод который передвигает карту вверх
     * @param speed
     */
    public static void moveMap(double speed, String dir) {
        Translate tr = new Translate();
        if (dir == "V") {
            currentYPos -= speed;
            currentYMove -= speed;
            tr.setY(-speed);
        } else if (dir == "H") {
            currentXPos+=speed;
            currentXMove+=speed;
            tr.setX(speed);
        }
        graphic.mapLayer.getTransforms().addAll(tr);

    }

    public static void setAsFirstCall(){
        firstCall = true;
    }

}
