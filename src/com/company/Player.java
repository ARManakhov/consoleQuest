package com.company;

public class Player {
    private final int DEFAULT_HP = 20;
    private final int DEFAULT_LVL = 0;
    private final int DEFAULT_MONEY = 0;
    private final int DEFAULT_X_POS = 1;
    private final int DEFAULT_Y_POS = 1;


    private String name;
    private int hp;
    private int lvl;
    private int money;
    private int xPos;
    private int yPos; //todo need inventory


    public Player(String name){
        this.name = name;
        this.hp = DEFAULT_HP;
        this.lvl = DEFAULT_LVL;
        this.money = DEFAULT_MONEY;
        this.xPos = DEFAULT_X_POS;
        this.yPos = DEFAULT_Y_POS;
    }

    public void moveL(){
        if (new Map().notWall(yPos,xPos+1)){
            xPos++;
        } //todo err msg L
    }


    public void moveR(){
        if (new Map().notWall(yPos,xPos-1)){
            xPos--;
        }//todo err msg R
    }


    public void moveU(){
        if (new Map().notWall(yPos+1,xPos)){
            yPos++;
        }//todo err msg U
    }


    public void moveD(){
        if (new Map().notWall(yPos-1,xPos)){
            yPos--;
        }//todo err msg D
    }
}
