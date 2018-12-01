package com.company;

import javafx.animation.PathTransition;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.*;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import static com.company.graphic.canvasSizeUpdate;
import static com.company.graphic.currentMapNumber;

/**
 * класс который работает с картой
 */
public class MapManager {

    private static final int BLOCK_SIZE = 32;                                       //размер каждой текстуры

    private static GraphicsContext gc = graphic.mapLayer.getGraphicsContext2D();

                                                                                    //координаты расположения блока[0][0] на экране
    public static double currentXPos = 0;
    public static double currentYPos = 0;
    private static double currentXMove = 0;
    private static double currentYMove = 0;
    private static double currentXMoveBeforeRedraw = 0;
    private static double currentYMoveBeforeRedraw = 0;


    private static boolean firstCall = true;

    private static boolean NeedRedraw = false;


    /**
     *
     * @param currentNanoTime текущее время в наносекундах
     */
    public static void drawMap(long currentNanoTime, int currentMapNumber){

        if(firstCall){
            currentYMoveBeforeRedraw = 0;
            currentXMoveBeforeRedraw = 0;

            currentXPos = graphic.theScene.getWidth() / 2 - BLOCK_SIZE* Maps.worldMap[currentMapNumber].spawnPosX;
            currentYPos = graphic.theScene.getHeight() / 2 - BLOCK_SIZE* Maps.worldMap[currentMapNumber].spawnPosY;

            firstCall = false;
            NeedRedraw = true;
        }

        if (Math.abs(currentXMoveBeforeRedraw) > graphic.theScene.getWidth() || Math.abs(currentYMoveBeforeRedraw) > graphic.theScene.getHeight() || NeedRedraw){
            redrawBlocks();

            currentXMoveBeforeRedraw = 0;
            currentYMoveBeforeRedraw = 0;
            NeedRedraw = false;
        }
        //System.out.println(" 1 " + currentYMove + " 2 " +  graphic.theScene.getHeight());


    }

    /**
     * метод заново отрисовывает текстуры //todo не рисовать то что за экраном
     */
    private static void redrawBlocks(){

        int drawLeftLim = 0;
        int drawRightLim = Maps.worldMap[currentMapNumber].maxWidth;
        int drawUpLimit = 0;
        int drawDownLimit = Maps.worldMap[currentMapNumber].maxHeight;

        gc.clearRect(-graphic.theScene.getWidth(), -graphic.theScene.getHeight(), 4 * graphic.theScene.getWidth(), 4 * graphic.theScene.getHeight()); // для начанала очищаем

        Translate tr = new Translate();
        tr.setY(-currentYMove);
        tr.setX(-currentXMove);
        graphic.mapLayer.getTransforms().addAll(tr);


        currentYMove = 0;
        currentXMove = 0;

        for (int i = drawUpLimit; i < drawDownLimit ; i++) {
            for (int j = drawLeftLim; j < Maps.worldMap[currentMapNumber].groundMap[i].length ; j++) {
                gc.drawImage(
                        Block.blockTexture[Maps.worldMap[currentMapNumber].groundMap[i][j]],
                        graphic.theScene.getWidth()  + j * BLOCK_SIZE + currentXPos,
                        graphic.theScene.getHeight() + i * BLOCK_SIZE + currentYPos

                );
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
            currentYMoveBeforeRedraw -= speed;
            tr.setY(-speed);
        } else if (dir == "H") {
            currentXPos+=speed;
            currentXMove+=speed;
            currentXMoveBeforeRedraw += speed;
            tr.setX(speed);
        }
        graphic.mapLayer.getTransforms().addAll(tr);

    }

    public static void setAsFirstCall(){
        firstCall = true;
    }
    public static void setNeedRedraw(){ NeedRedraw = true;}

}
