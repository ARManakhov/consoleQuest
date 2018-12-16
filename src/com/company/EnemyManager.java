package com.company;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class EnemyManager {
    private static final double ENEMY_SIZE_X = 16;
    private static final double ENEMY_SIZE_Y = 16;

    private Enemy enemy;

    private double screenXPos;
    private double screenYPos;
    private double realXPos;
    private double realYPos;
    private double speed;
    private static GraphicsContext gc = graphic.playerLayer.getGraphicsContext2D();
    private static Image zombieTexture = new Image("resources/characters/enemy/spruce_sapling.png");
    private static Image lookLeft = new Image("resourses/characters/enemy/Enemy_Left");
    private static Image lookRight = new Image("resourses/characters/enemy/Enemy_Right");
    private static Image lookUp = new Image("resourses/characters/enemy/Enemy_Up");
    private static Image lookDown = new Image("resourses/characters/enemy/Enemy_Down");
    private long prevNanotime;
    private final long NANO_TIME_DELTA = 17000000;

    private static boolean firstCall = true;

    public EnemyManager(Enemy e){
        this.enemy = e;

    }

    public void drawEnemy(long currentNanoTime, int mapNumber) {
     if(firstCall){
         firstCall = true;
         prevNanotime = currentNanoTime - NANO_TIME_DELTA;
     }
        speed = enemy.getSpeed();
        speed = 1;
        speed = (currentNanoTime - prevNanotime) * speed / NANO_TIME_DELTA;
        prevNanotime = currentNanoTime;
        int radius = 260;
        int radiusOther = 32;
        Player pl = Player.getPlayer();
        realXPos = enemy.getRealXPos();
        realYPos = enemy.getRealYPos();
        double diametr = (realXPos - pl.getRealXPos())*(realXPos - pl.getRealXPos())+
                (realYPos - pl.getRealYPos())*(realYPos - pl.getRealYPos());
         double speedX = speed *((pl.getRealXPos() - realXPos) /
                Math.sqrt((realXPos - pl.getRealXPos()) * (realXPos - pl.getRealXPos())+(realYPos - pl.getRealYPos()) * (realYPos - pl.getRealYPos())));
double speedY = speed * ((pl.getRealYPos()- realYPos) /
        Math.sqrt((realXPos - pl.getRealXPos())*(realXPos - pl.getRealXPos())+(realYPos - pl.getRealYPos())*(realYPos - pl.getRealYPos())));
        if ( diametr < radius*radius & diametr > radiusOther*radiusOther){
            if ((pl.getRealXPos() - realXPos)  > 0) {
                if ((pl.getRealYPos() - realYPos) > 600){
                    System.out.println("Up");
                }

            }
            if (Map.maps[mapNumber].borderMap[(int)realYPos/32][(int)(realXPos + speedX)/32] != 1 &&
            Map.maps[mapNumber].borderMap[(int)(realYPos)/32][(int)(realXPos + ENEMY_SIZE_X + speedX)/32] != 1 &&
                    Map.maps[mapNumber].borderMap[(int)(realYPos + ENEMY_SIZE_Y)/32][(int)(realXPos + speedX)/32] != 1 &&
                    Map.maps[mapNumber].borderMap[(int)(realYPos + ENEMY_SIZE_Y)/32][(int)(realXPos + ENEMY_SIZE_X + speedX)/32] != 1
            ){
                realXPos = realXPos + speedX;
            }
            if(Map.maps[mapNumber].borderMap[(int)(realYPos + speedY)/32][(int)realXPos/32] != 1 &&
            Map.maps[mapNumber].borderMap[(int)(realYPos + speedY + ENEMY_SIZE_Y)/32][(int)(realXPos)/32] != 1 &&
                    Map.maps[mapNumber].borderMap[(int)(realYPos + speedY)/32][(int)(realXPos + ENEMY_SIZE_X) /32] != 1 &&
                    Map.maps[mapNumber].borderMap[(int)(realYPos + speedY + ENEMY_SIZE_Y)/32][(int)(realXPos + ENEMY_SIZE_X)/32] != 1){
                realYPos = realYPos + speedY;
            }
    }
        screenXPos = realXPos + MapManager.currentXPos;
        screenYPos = realYPos + MapManager.currentYPos;
         enemy.setRealXPos(realXPos);
         enemy.setRealYPos(realYPos);
        gc.drawImage(zombieTexture, screenXPos, screenYPos);
    }



    public EnemyManager(Character enemy){
        realXPos = enemy.getRealXPos();
        realYPos = enemy.getRealYPos();
    }
}
