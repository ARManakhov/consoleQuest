package com.company;

import javafx.scene.canvas.GraphicsContext;

/**
 * класс который работает с картой
 */
public class MapManager {

    private static final int BLOCK_SIZE = 32;                                       //размер каждой текстуры

    private static GraphicsContext gc = graphic.canvas.getGraphicsContext2D();

                                                                                    //координаты расположения блока[0][0] на экране
    private static double currentXPos = graphic.theScene.getWidth() / 2 - BLOCK_SIZE*Map0.spawnPosX;
    private static double currentYPos = graphic.theScene.getHeight() / 2 - BLOCK_SIZE*Map0.spawnPosY;

    /**
     *
     * @param currentNanoTime текущее время в наносекундах
     */
    public static void drawMap(long currentNanoTime){
        gc.clearRect(0, 0, graphic.theScene.getWidth(), graphic.theScene.getHeight());
        for (int i = 0; i < Map0.groundMap.length ; i++) {
            for (int j = 0; j < Map0.groundMap[i].length ; j++) {
                gc.drawImage(Block.blockTexture[Map0.groundMap[i][j]],j*BLOCK_SIZE+currentXPos,i*BLOCK_SIZE+currentYPos);
            }
        }
    }

    /**
     * метод который передвигает карту вверх //todo объеденить следующие 4 класса
     * @param speed
     */
    public static void moveMapUp(double speed) { currentYPos-=speed; }

    /**
     * метод который передвигает карту вниз
     * @param speed
     */
    public static void moveMapDown(double speed) {
          currentYPos+=speed;
    }

    /**
     * метод который передвигает карту влево
     * @param speed
     */
    public static void moveMapLeft(double speed) { currentXPos-=speed; }

    /**
     * метод который передвигает карту вправо
     * @param speed
     */
    public static void moveMapRight(double speed){
            currentXPos+=speed;
    }


}
