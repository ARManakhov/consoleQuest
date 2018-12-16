package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Translate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static com.company.graphic.currentMapNumber;

/**
 * класс который работает с картой
 */
public class MapManager {

    private static final int BLOCK_SIZE = 32;                                       //размер каждой текстуры

    private static GraphicsContext gc = graphic.mapLayer.getGraphicsContext2D();


    public static int getBlockSize() {
        return BLOCK_SIZE;
    }

    //координаты расположения блока[0][0] на экране
    public static double currentXPos = 0;
    public static double currentYPos = 0;
    private static double currentXMove = 0;
    private static double currentYMove = 0;
    private static double currentXMoveBeforeRedraw = 0;
    private static double currentYMoveBeforeRedraw = 0;

    private static boolean drawFromAngle = false;

    private static boolean firstCall = true;

    private static boolean NeedRedraw = false;

    private static boolean ViewBlockMask = false;
    private static boolean ViewSpawnMask = false;


    private static Block block = Block.getInstance();

    public static boolean isViewBlockMask() {
        return ViewBlockMask;
    }

    public static void setViewBlockMask(boolean viewBlockMask) {
        ViewBlockMask = viewBlockMask;
    }

    public static boolean isViewSpawnMask() {
        return ViewSpawnMask;
    }

    public static void setViewSpawnMask(boolean viewSpawnMask) {
        ViewSpawnMask = viewSpawnMask;
    }

    /**
     *
     * @param currentNanoTime текущее время в наносекундах
     */
    public static void draw(long currentNanoTime, int currentMapNumber){

        if(firstCall){
            currentYMoveBeforeRedraw = 0;
            currentXMoveBeforeRedraw = 0;
            if(!drawFromAngle){
                currentXPos = graphic.theScene.getWidth() / 2 - BLOCK_SIZE* Map.maps[currentMapNumber].spawnPosX;
                currentYPos = graphic.theScene.getHeight() / 2 - BLOCK_SIZE* Map.maps[currentMapNumber].spawnPosY;
            }

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

    public static void serDrawFromAngle(boolean b){
        drawFromAngle = b;
    }

    /**
     * метод заново отрисовывает текстуры //todo не рисовать то что за экраном
     */
    private static void redrawBlocks(){

        int drawLeftLim = 0;
        int drawRightLim = Map.maps[currentMapNumber].maxWidth;
        int drawUpLimit = 0;
        int drawDownLimit = Map.maps[currentMapNumber].groundMap.length;

        gc.clearRect(-graphic.theScene.getWidth(), -graphic.theScene.getHeight(), 4 * graphic.theScene.getWidth(), 4 * graphic.theScene.getHeight()); // для начанала очищаем
        gc.fillRect(-graphic.theScene.getWidth(), -graphic.theScene.getHeight(), 4 * graphic.theScene.getWidth(), 4 * graphic.theScene.getHeight()); // для начанала очищаем

        Translate tr = new Translate();
        tr.setY(-currentYMove);
        tr.setX(-currentXMove);
        graphic.mapLayer.getTransforms().addAll(tr);


        currentYMove = 0;
        currentXMove = 0;

        for (int i = drawUpLimit; i < drawDownLimit ; i++) {
            for (int j = drawLeftLim; j < Map.maps[currentMapNumber].groundMap[i].length ; j++) {
                gc.drawImage(
                        block.getBlock(Map.maps[currentMapNumber].groundMap[i][j]),
                        graphic.theScene.getWidth()  + j * BLOCK_SIZE + currentXPos,
                        graphic.theScene.getHeight() + i * BLOCK_SIZE + currentYPos
                );
                try{
                        gc.drawImage(
                            block.getFurniture(Map.maps[currentMapNumber].furnitureMap[i][j]),
                            graphic.theScene.getWidth()  + j * BLOCK_SIZE + currentXPos,
                            graphic.theScene.getHeight() + i * BLOCK_SIZE + currentYPos
                        );

                    if(ViewBlockMask){
                        if(Map.maps[currentMapNumber].borderMap[i][j] == 1)
                                gc.drawImage(new Image(new FileInputStream( new File("./resources/editor/maskB.png"))),
                                graphic.theScene.getWidth()  + j * BLOCK_SIZE + currentXPos,
                                graphic.theScene.getHeight() + i * BLOCK_SIZE + currentYPos);
                    }

                    if(ViewSpawnMask){
                        if(Map.maps[currentMapNumber].enemyMap[i][j] == 1)
                            gc.drawImage(new Image(new FileInputStream( new File("./resources/editor/maskS.png"))),
                                graphic.theScene.getWidth()  + j * BLOCK_SIZE + currentXPos,
                                graphic.theScene.getHeight() + i * BLOCK_SIZE + currentYPos);
                    }
                }catch (ArrayIndexOutOfBoundsException e){


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
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

