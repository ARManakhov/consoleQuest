package com.company;

import javafx.scene.canvas.GraphicsContext;

public class MapManager {

    private static final int BLOCK_SIZE = 32;

    private static GraphicsContext gc = graphic.canvas.getGraphicsContext2D();

    private static double currentXPos = graphic.theScene.getWidth() / 2 - BLOCK_SIZE*Map0.spawnPosX;
    private static double currentYPos = graphic.theScene.getHeight() / 2 - BLOCK_SIZE*Map0.spawnPosY;


    /* переделать
    public boolean notWall(int y, int x){
        if ( groundMap[y][x] == 0 || groundMap[y][x] == 1){
            return false;
        } else{
            return true;
        }
    }*/

    public static void drawMap(long currentNanoTime){
        gc.clearRect(0, 0, graphic.theScene.getWidth(), graphic.theScene.getHeight());
        for (int i = 0; i < Map0.groundMap.length ; i++) {
            for (int j = 0; j < Map0.groundMap[i].length ; j++) {
                gc.drawImage(Block.blockTexture[Map0.groundMap[i][j]],j*BLOCK_SIZE+currentXPos,i*BLOCK_SIZE+currentYPos);
            }
        }
    }

    public static void moveMapUp(double speed) { currentYPos-=speed; }

    public static void moveMapDown(double speed) {
          currentYPos+=speed;
    }

    public static void moveMapLeft(double speed) { currentXPos-=speed; }

    public static void moveMapRight(double speed){
            currentXPos+=speed;
    }


}
