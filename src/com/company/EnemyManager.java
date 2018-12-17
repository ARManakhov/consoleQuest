package com.company;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.util.Random;

public class EnemyManager {
    private static final double ENEMY_SIZE_X = 30;
    private static final double ENEMY_SIZE_Y = 30;

    private Enemy enemy;
    int dir;
    private double screenXPos;
    private double screenYPos;
    private double realXPos;
    private double realYPos;
    private double speed;
    private static GraphicsContext gc = graphic.playerLayer.getGraphicsContext2D();
    private Image[] enemyTexture;
    /*private static Image lookLeft = new Image("resourses/characters/enemy/Enemy_Left");
    private static Image lookRight = new Image("resourses/characters/enemy/Enemy_Right");
    private static Image lookUp = new Image("resourses/characters/enemy/Enemy_Up");
    private static Image lookDown = new Image("resourses/characters/enemy/Enemy_Down");
    */
    private final byte ANUMATION_FRAMES_COUNT = 3;
    private byte curentFrameNum = 1; //текущий кадр для анимации
    private long prevNanotime;
    private final long NANO_TIME_DELTA = 17000000;
    private long prevAnimNanoTime;
    private final long ANIMTION_TIME_DELTA = 100000000;

    public Enemy getEnemy() {
        return enemy;
    }

    private Random rand = new Random();

    private static boolean firstCall = true;

    public EnemyManager(Enemy e){
        this.enemy = e;
        dir = rand.nextInt(4);
        TextureGet tg = null;
        tg = new TextureGet(new File("./resources/characters/enemy/" + rand.nextInt(5)));
        enemyTexture = tg.getTexture();
    }

    public void drawEnemy(long currentNanoTime, int mapNumber) {
     if(firstCall){
         firstCall = true;
         prevNanotime = currentNanoTime - NANO_TIME_DELTA;
     }
     if(enemy.isAlive()) {
         speed = enemy.getSpeed();
         speed = 1;
         speed = (currentNanoTime - prevNanotime) * speed / NANO_TIME_DELTA;
         prevNanotime = currentNanoTime;
         int radius = 260;
         int radiusOther = 32;
         Player pl = Player.getPlayer();
         realXPos = enemy.getRealXPos();
         realYPos = enemy.getRealYPos();
         double distance = Math.sqrt((realXPos - pl.getRealXPos()) * (realXPos - pl.getRealXPos()) + (realYPos - pl.getRealYPos()) * (realYPos - pl.getRealYPos()));
         double angleCos = (pl.getRealXPos() - realXPos) / distance;
         double speedX = speed * angleCos;
         double speedY = speed * (pl.getRealYPos() - realYPos) / distance;
         if (distance < radius && distance > radiusOther) {

             if (Map.maps[mapNumber].borderMap[(int) realYPos / 32][(int) (realXPos + speedX) / 32] != 1 &&
                     Map.maps[mapNumber].borderMap[(int) (realYPos) / 32][(int) (realXPos + ENEMY_SIZE_X + speedX) / 32] != 1 &&
                     Map.maps[mapNumber].borderMap[(int) (realYPos + ENEMY_SIZE_Y) / 32][(int) (realXPos + speedX) / 32] != 1 &&
                     Map.maps[mapNumber].borderMap[(int) (realYPos + ENEMY_SIZE_Y) / 32][(int) (realXPos + ENEMY_SIZE_X + speedX) / 32] != 1
             ) {
                 realXPos = realXPos + speedX;
             }
             if (Map.maps[mapNumber].borderMap[(int) (realYPos + speedY) / 32][(int) realXPos / 32] != 1 &&
                     Map.maps[mapNumber].borderMap[(int) (realYPos + speedY + ENEMY_SIZE_Y) / 32][(int) (realXPos) / 32] != 1 &&
                     Map.maps[mapNumber].borderMap[(int) (realYPos + speedY) / 32][(int) (realXPos + ENEMY_SIZE_X) / 32] != 1 &&
                     Map.maps[mapNumber].borderMap[(int) (realYPos + speedY + ENEMY_SIZE_Y) / 32][(int) (realXPos + ENEMY_SIZE_X) / 32] != 1) {
                 realYPos = realYPos + speedY;
             }
             if (currentNanoTime - prevAnimNanoTime > ANIMTION_TIME_DELTA) {
                 prevAnimNanoTime = currentNanoTime;
                 if (curentFrameNum != ANUMATION_FRAMES_COUNT - 1) {
                     curentFrameNum++;
                 } else {
                     curentFrameNum = 0;
                 }
             }

             if (pl.getRealYPos() - screenYPos > 0) {
                 if (angleCos > 0.707 && angleCos <= 1) {
                     dir = 0;
                 }
                 if (angleCos > -0.707 && angleCos <= 0.707) {
                     dir = 1;
                 }
                 if (angleCos > -1 && angleCos <= -0.707) {
                     dir = 2;
                 }
             } else {
                 if (angleCos > 0.707 && angleCos <= 1) {
                     dir = 0;
                 }
                 if (angleCos > -0.707 && angleCos <= 0.707) {
                     dir = 3;
                 }
                 if (angleCos > -1 && angleCos <= -0.707) {
                     dir = 2;
                 }
             }
         } else {
             curentFrameNum = 1;
         }

     }
        screenXPos = realXPos + MapManager.getInstance().currentXPos;
        screenYPos = realYPos + MapManager.getInstance().currentYPos;
         enemy.setRealXPos(realXPos);
         enemy.setRealYPos(realYPos);
         if (enemy.isAlive()){
             gc.drawImage(enemyTexture[dir * ANUMATION_FRAMES_COUNT + curentFrameNum], screenXPos, screenYPos);
         } else{
             gc.drawImage(enemyTexture[12], screenXPos, screenYPos);
         }

    }


    public EnemyManager(Character enemy){
        realXPos = enemy.getRealXPos();
        realYPos = enemy.getRealYPos();
    }
}
