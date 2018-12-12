package com.company;

public class Map {
    static Map[] maps;


    public int spawnPosX;
    public int spawnPosY;

    public byte[][] groundMap;

    public byte[][] borderMap;

    public byte[][] enemyMap;

    public byte[][] teleportMap;

    public byte[][] furnitureMap;

    public int maxWidth;
    public int maxHeight;


    Map(int spawnPosX, int spawnPosY,byte[][] groundMap, byte[][] borderMap, byte[][] enemyMap,byte[][] teleportMap,byte[][] furnitureMap){
        this.spawnPosX = spawnPosX;
        this.spawnPosY = spawnPosY;
        this.groundMap = groundMap;
        this.borderMap = borderMap;
        this.enemyMap = enemyMap;
        this.teleportMap = teleportMap;
        this.furnitureMap = furnitureMap;
        this.maxHeight = groundMap.length;
        for (int i = 0; i < this.maxHeight ; i++) {
            if (this.maxWidth < this.groundMap[i].length){
                this.maxWidth = this.groundMap[i].length;
            }
        }
    }
}




