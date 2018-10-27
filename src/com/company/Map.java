package com.company;

public class Map {

    public int spawnPosX = 0;
    public int spawnPosY = 0 ;

    public byte[][] groundMap = {{}};

    public byte[][] borderMap = {{}};

    public byte[][] enemyMap = {{}};

    Map(int spawnPosX, int spawnPosY,byte[][] groundMap, byte[][] borderMap, byte[][] enemyMap){
        this.spawnPosX = spawnPosX;
        this.spawnPosY = spawnPosY;
        this.groundMap = groundMap;
        this.borderMap = borderMap;
        this.enemyMap = enemyMap;

    }

}
