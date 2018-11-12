package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;



public class ZombieManager {
    private static final double Zombie_SIZE_X = 64.0D;
    private static final double Zombie_SIZE_Y = 64.0D;
    private static boolean firstCall = true;
    private static GraphicsContext gc;
    private static Image zombieTexture;
    private static double screenXPos;
    private static double screenYPos;
    private static double realXPos = 430;
    private static double realYPos = 448;
    public static void zombieMob(long currentNanoTime, int mapNumber) {
        int radius = 128;
        int radiusOther = 16;
        if ((realXPos - PlayerManager.realXPos)*(realXPos - PlayerManager.realXPos)
                + (realYPos - PlayerManager.realYPos)*(realYPos - PlayerManager.realYPos) < radius*radius){
            if ((realXPos - PlayerManager.realXPos) > radiusOther ){
                realXPos = realXPos - Zombie.speed;
            }
            else {
                realXPos = realXPos + Zombie.speed;
            }
            if ((realYPos - PlayerManager.realYPos) > radiusOther ){
                realYPos = realYPos - Zombie.speed;
            }
            else {
                realYPos = realYPos + Zombie.speed;
            }
        }
        screenXPos = realXPos + MapManager.currentXPos;
        screenYPos = realYPos + MapManager.currentYPos;
        gc.drawImage(zombieTexture, screenXPos, screenYPos);
    }

    static {
        gc = graphic.canvas.getGraphicsContext2D();
        zombieTexture = new Image("resources/characters/Zombie/Zombie.png");
    }
}
