package com.company;

import javafx.scene.canvas.GraphicsContext;

/**
 * класс который работает с картой
 */
public class MapManager {

    private static final int BLOCK_SIZE = 32;                                       //размер каждой текстуры

    private static GraphicsContext gc = graphic.canvas.getGraphicsContext2D();

                                                                                    //координаты расположения блока[0][0] на экране
    public static double currentXPos = 0;
    public static double currentYPos = 0;

    private static boolean firstCall = true;


    /**
     *
     * @param currentNanoTime текущее время в наносекундах
     */
    public static void drawMap(long currentNanoTime){
        if(firstCall){
            currentXPos = graphic.theScene.getWidth() / 2 - BLOCK_SIZE* Maps.worldMap[graphic.currentMapNumber].spawnPosX;
            currentYPos = graphic.theScene.getHeight() / 2 - BLOCK_SIZE* Maps.worldMap[graphic.currentMapNumber].spawnPosY;
            firstCall = false;
        }
        gc.clearRect(0, 0, graphic.theScene.getWidth(), graphic.theScene.getHeight());
        for (int i = 0; i < Maps.worldMap[graphic.currentMapNumber].groundMap.length ; i++) {
            for (int j = 0; j < Maps.worldMap[graphic.currentMapNumber].groundMap[i].length ; j++) {
                gc.drawImage(Block.blockTexture[Maps.worldMap[graphic.currentMapNumber].groundMap[i][j]],j*BLOCK_SIZE+currentXPos,i*BLOCK_SIZE+currentYPos);
            }
        }
    }

    /**
     * метод который передвигает карту вверх
     * @param speed
     */
    public static void moveMap(double speed, String dir) {
        if (dir == "V") {
            currentYPos -= speed;
        } else if (dir == "H") {
            currentXPos+=speed;
        }
    }

    public static void setAsFirstCall(){
        firstCall = true;
    }

}
