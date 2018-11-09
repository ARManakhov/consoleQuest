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

    private static boolean NeedRedraw = false;


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

            firstCall = false;

            redrawBlocks();
        }

        if (Math.abs(currentXMove) > graphic.theScene.getWidth() || Math.abs(currentYMove) > graphic.theScene.getHeight() || NeedRedraw){
            redrawBlocks();
            NeedRedraw = false;
        }
        //System.out.println(" 1 " + currentYMove + " 2 " +  graphic.theScene.getHeight());


    }

    /**
     * метод заново отрисовывает текстуры //todo не рисовать то что за экраном
     */
    private static void redrawBlocks(){
        gc.clearRect(-graphic.theScene.getWidth(), -graphic.theScene.getHeight(), 3*graphic.theScene.getWidth(), 3*graphic.theScene.getHeight()); // для начанала очищаем


        int drawLeftLim = 0;
        int drawRightLim = Maps.worldMap[graphic.currentMapNumber].groundMap[0].length;
        int drawUpLimit = 0;
        int drawDownLimit = Maps.worldMap[graphic.currentMapNumber].groundMap.length;
        for (int i = drawUpLimit; i < drawDownLimit ; i++) {
            for (int j = drawLeftLim; j < drawRightLim ; j++) {
                gc.drawImage(Block.blockTexture[Maps.worldMap[graphic.currentMapNumber].groundMap[i][j]],j*BLOCK_SIZE+currentXPos+graphic.theScene.getWidth(),i*BLOCK_SIZE+currentYPos+graphic.theScene.getHeight());
            }
        }
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
    public static void setNeedRedraw(){ NeedRedraw = true;}

}
