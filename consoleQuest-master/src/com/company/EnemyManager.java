package com.company;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class EnemyManager {
    private static final double PLAYER_SIZE_X = 16;
    private static final double PLAYER_SIZE_Y = 16;

    private static double screenXPos;
    private static double screenYPos;
    private static double limX1;
    private static double limY1;
    private static double limX2;
    private static double limY2;
    public static double realXPos = 128;
    public static double realYPos = 128;

    private static GraphicsContext gc = graphic.canvas.getGraphicsContext2D();
    private static Image playerTexture = new Image("resources/characters/player/spruce_sapling.png");

    private static boolean firstCall = true;

    public static void drawEnemy(long currentNanoTime, int mapNumber) {
        gc.drawImage(playerTexture, screenXPos, screenYPos);
    }

    public static void ScreenPosE(){
        screenXPos = MapManager.currentXPos + EnemyManager.realXPos;
        screenYPos  = MapManager.currentYPos + EnemyManager.realYPos;
    }
}
