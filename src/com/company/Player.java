package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

//todo отделить данные о игроке от отрисовки

public class Player extends Char {
    private final int DEFAULT_HP = 20;
    private final int DEFAULT_LVL = 0;
    private final int DEFAULT_MONEY = 0;
    private final int DEFAULT_DAMAGE = 5;
    private final double DEFAULT_SPEED  = 2.5;

    private final int DEFAULT_DAMAGE_RAISE = 2;
    private final int DEFAULT_HP_RISE = 2;

    private final double PLAYER_SIZE_X = 16;
    private final double PLAYER_SIZE_Y = 16;

    private String name;
    private int hp;
    private int maxHP;
    private int lvl;
    private int money;
    private int damage;
    private double speed;
    private double screenXPos;
    private double screenYPos;
    private static double limX1;
    private static double limY1;
    private static double limX2;
    private static double limY2;
    private double realXPos;
    private double realYPos;
    //todo need inventory
    private static GraphicsContext gc = graphic.canvas.getGraphicsContext2D();
    private static Image playerTexture = new Image("resources/characters/player/spruce_sapling.png");

    public Player(String name){
        this.name = name;
        this.hp = DEFAULT_HP;
        this.lvl = DEFAULT_LVL;
        this.money = DEFAULT_MONEY;
        this.screenXPos =  graphic.theScene.getWidth()/2;
        this.screenYPos =  graphic.theScene.getHeight()/2;

        this.realXPos =  32 * Map0.spawnPosX;
        this.realYPos =  32 * Map0.spawnPosY;


        this.damage = DEFAULT_DAMAGE;
        this.speed = DEFAULT_SPEED;
    }




    public void statUpdate(){
        this.lvl++;
        this.damage = DEFAULT_DAMAGE + this.lvl * DEFAULT_DAMAGE_RAISE;
        this.maxHP = DEFAULT_HP + this.lvl * DEFAULT_HP_RISE;

    }

    public void drawPlayer(long currentNanoTime) {

        limFinder();

        //todo наискасок скорость через корень

        if(KeyManager.pressedLEFT()){

            if ((Map0.borderMap[(int) (realYPos)/32][(int) (realXPos - speed)/32] != 1)
                    && (Map0.borderMap[(int) (realYPos + PLAYER_SIZE_Y)/32][(int) (realXPos - speed)/32] != 1)){

                if (screenXPos > limX1) {
                    screenXPos-=speed;
                } else {
                    MapManager.moveMapRight(speed);

                }

                realXPos-=speed;

            } else if((Map0.borderMap[(int) (realYPos)/32][(int) (realXPos)/32] != 1)
                    && (Map0.borderMap[(int) (realYPos + PLAYER_SIZE_Y)/32][(int) (realXPos )/32] != 1)){

                double move = realXPos % 32;

                if (screenXPos > limX1) {
                    screenXPos-=move;
                } else {
                    MapManager.moveMapRight(move);
                }

                realXPos-=move;

            }
        }


        if (KeyManager.pressedRIGHT()){
            if((Map0.borderMap[(int) (realYPos)/32][(int) (realXPos + speed + PLAYER_SIZE_X)/32] != 1 )
                    && (Map0.borderMap[(int) (realYPos + PLAYER_SIZE_Y)/32][(int) (realXPos + speed + PLAYER_SIZE_X)/32] != 1 )  ){
                if (screenXPos < limX2 ) {
                    screenXPos+=speed;
                } else {
                    MapManager.moveMapLeft(speed);
                }
                realXPos+=speed;

            } else if((Map0.borderMap[(int) (realYPos)/32][(int) (realXPos + PLAYER_SIZE_X)/32] != 1 )
                    && (Map0.borderMap[(int) (realYPos + PLAYER_SIZE_Y)/32][(int) (realXPos  + PLAYER_SIZE_X)/32] != 1 )  ){

                double move = PLAYER_SIZE_X - realXPos % 32 - 1;

                if (screenXPos < limX2 ) {
                    screenXPos+=move;
                } else {
                    MapManager.moveMapLeft(move);
                }
                realXPos+=move;

            }
        }


        if(KeyManager.pressedUP()){
            if((Map0.borderMap[(int) (realYPos - speed)/32][(int) realXPos/32] != 1 )
                    && (Map0.borderMap[(int) (realYPos - speed)/32][(int) (realXPos+PLAYER_SIZE_X)/32] != 1 )) {
                if (screenYPos > limY1 ) {
                    screenYPos-=speed;
                } else {
                    MapManager.moveMapDown(speed);
                }
                realYPos-=speed;

            } else if((Map0.borderMap[(int) (realYPos)/32][(int) realXPos/32] != 1 )
                    && (Map0.borderMap[(int) (realYPos)/32][(int) (realXPos+PLAYER_SIZE_X)/32] != 1 )) {

                double move = realYPos % 32;

                if (screenYPos > limY1 ) {
                    screenYPos-=move;
                } else {
                    MapManager.moveMapDown(move);
                }
                realYPos-=move;

            }
        }

        if(KeyManager.pressedDOWN()){
            if((Map0.borderMap[(int) (realYPos + speed + PLAYER_SIZE_Y)/32][(int) realXPos/32] != 1 )
                    && (Map0.borderMap[(int) (realYPos + speed + PLAYER_SIZE_Y)/32][(int) (realXPos+PLAYER_SIZE_X)/32] != 1 )){
                if (screenYPos < limY2) {
                    screenYPos+=speed;
                } else {
                    MapManager.moveMapUp(speed);
                }
                realYPos+=speed;

            }else if((Map0.borderMap[(int) (realYPos + PLAYER_SIZE_Y)/32][(int) realXPos/32] != 1 )
                    && (Map0.borderMap[(int) (realYPos + PLAYER_SIZE_Y)/32][(int) (realXPos+PLAYER_SIZE_X)/32] != 1 )){

                double move = PLAYER_SIZE_Y - realYPos % 32 - 1;

                if (screenYPos < limY2) {
                    screenYPos+=move;
                } else {
                    MapManager.moveMapUp(move);
                }
                realYPos+=move;

            }
        }


        gc.drawImage(playerTexture, this.screenXPos, this.screenYPos);

    }

    private static void limFinder(){
        int xSlice = (int) graphic.theScene.getWidth()/4;
        limX1 = xSlice;
        limX2 = xSlice * 3;

        int ySlice = (int) graphic.theScene.getHeight()/4;
        limY1 = ySlice;
        limY2 = ySlice * 3;
    }
}
