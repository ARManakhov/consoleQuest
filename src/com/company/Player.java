package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Player extends Char {
    private final int DEFAULT_HP = 20;
    private final int DEFAULT_LVL = 0;
    private final int DEFAULT_MONEY = 0;
    private final int DEFAULT_DAMAGE = 5;
    private final int DEFAULT_SPEED  = 3;

    private final int DEFAULT_DAMAGE_RAISE = 2;
    private final int DEFAULT_HP_RISE = 2;

    private String name;
    private int hp;
    private int maxHP;
    private int lvl;
    private int money;
    private int damage;
    private int speed;
    private int screenXPos;
    private int screenYPos;
    private static int limX1;
    private static int limY1;
    private static int limX2;
    private static int limY2;
    private int realXPos;
    private int realYPos; //todo need inventory
    private static GraphicsContext gc = graphic.canvas.getGraphicsContext2D();
    private static Image playerTexture = new Image("resources/characters/player/spruce_sapling.png");

    public Player(String name){
        this.name = name;
        this.hp = DEFAULT_HP;
        this.lvl = DEFAULT_LVL;
        this.money = DEFAULT_MONEY;
        this.screenXPos = (int) graphic.theScene.getWidth()/2;
        this.screenYPos = (int) graphic.theScene.getHeight()/2;
        this.damage = DEFAULT_DAMAGE;
        this.speed = DEFAULT_SPEED;
    }

 // todo плавное движение с ускорением , и вообще двигать карту , иногда персонажа


    public void statUpdate(){
        this.lvl++;
        this.damage = DEFAULT_DAMAGE + this.lvl * DEFAULT_DAMAGE_RAISE;
        this.maxHP = DEFAULT_HP + this.lvl * DEFAULT_HP_RISE;

    }

    public void drawPlayer(long currentNanoTime) {

        limFinder();

        if(KeyManager.activeKeyHash.contains("LEFT")){

            if (screenXPos > limX1) {
                screenXPos-=speed;
            } else {
                Map.moveMapRight(speed);
            }

            realXPos-=speed;
        }


        if (KeyManager.activeKeyHash.contains("RIGHT")){
            if (screenXPos < limX2 ) {
                screenXPos+=speed;
            } else {
                Map.moveMapLeft(speed);
            }
            realXPos+=speed;

        }


        if(KeyManager.activeKeyHash.contains("UP")) {
            if (screenYPos > limY1 ) {
                screenYPos-=speed;
            } else {
                Map.moveMapDown(speed);
            }
            realYPos-=speed;

        }

        if(KeyManager.activeKeyHash.contains("DOWN")){
            if (screenYPos < limY2) {
                screenYPos+=speed;
            } else {
                Map.moveMapUp(speed);
            }
            realYPos+=speed;

        }
        System.out.println(realXPos+ " " + realYPos);


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
