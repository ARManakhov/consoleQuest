package com.company;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class EnemyManager {
    private static final double PLAYER_SIZE_X = 16;
    private static final double PLAYER_SIZE_Y = 16;

    private Enemy enemy;

    private  double screenXPos;
    private  double screenYPos;
    private   double realXPos ;
    private   double realYPos ;
    private double speed;
    private static GraphicsContext gc = graphic.playerLayer.getGraphicsContext2D();
    private static Image zombieTexture = new Image("resources/characters/player/spruce_sapling.png");

    private static boolean firstCall = true;

    public EnemyManager(Enemy e){
        this.enemy = e;
        this.speed = enemy.getSpeed();
        //this.realXPos = enemy.getRealXPos();
        //this.realYPos = enemy.getRealYPos();
    }

    public void drawEnemy(long currentNanoTime, int mapNumber) {
        int radius = 260;
        int radiusOther = 32;
        Player pl = Player.getPlayer();
        realXPos = enemy.getRealXPos();
        realYPos = enemy.getRealYPos();
        double diametr = (realXPos - pl.getRealXPos())*(realXPos - pl.getRealXPos())+
                (realYPos - pl.getRealYPos())*(realYPos - pl.getRealYPos());

        if ( diametr < radius*radius & diametr > radiusOther*radiusOther){
                realXPos = realXPos + speed *((pl.getRealXPos() - realXPos) /
                        Math.sqrt((realXPos - pl.getRealXPos()) * (realXPos - pl.getRealXPos())+(realYPos - pl.getRealYPos()) * (realYPos - pl.getRealYPos())));
                realYPos = realYPos + speed * ((pl.getRealYPos()- realYPos) /
                        Math.sqrt((realXPos - pl.getRealXPos())*(realXPos - pl.getRealXPos())+(realYPos - pl.getRealYPos())*(realYPos - pl.getRealYPos())));
    }
        screenXPos = realXPos + MapManager.currentXPos;
        screenYPos = realYPos + MapManager.currentYPos;
         enemy.setRealXPos(realXPos);
         enemy.setRealYPos(realYPos);
        //System.out.println(enemy.getRealXPos()+", "+enemy.getRealYPos());
        gc.drawImage(zombieTexture, screenXPos, screenYPos);
    }



    public EnemyManager(Character enemy){
        realXPos = enemy.getRealXPos();
        realYPos = enemy.getRealYPos();
    }
}
