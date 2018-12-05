package com.company;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class EnemyManager {
    private static final double PLAYER_SIZE_X = 16;
    private static final double PLAYER_SIZE_Y = 16;

    private  double screenXPos;
    private  double screenYPos;
    private   double realXPos = 128;
    private   double realYPos = 128;

    private static GraphicsContext gc = graphic.playerLayer.getGraphicsContext2D();
    private static Image playerTexture = new Image("resources/characters/player/spruce_sapling.png");

    private static boolean firstCall = true;

    public void drawEnemy(long currentNanoTime, int mapNumber) {
        screenXPos = MapManager.currentXPos + realXPos;
        screenYPos  = MapManager.currentYPos + realYPos;
        gc.drawImage(playerTexture, screenXPos, screenYPos);
    }




    public EnemyManager(Character enemy){
        realXPos = enemy.getRealXPos();
        realYPos = enemy.getRealYPos();
    }
}
