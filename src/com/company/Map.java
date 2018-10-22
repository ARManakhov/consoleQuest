package com.company;

//class with lvl 0 map

public class Map {

    private byte[][] groundMap = { //map of world
            {0,0,0,0},
            {0,1,1,0},
            {0,1,1,0},
            {0,0,0,0}
    };

    // arr with map
    // 0 - borders of world
    // 1 - wall
    // 2 - ground
    // 3 - sand

    private byte[][] enemyMap = { //map of enemy
            {0,0,0,0},
            {0,0,0,0},
            {0,0,0,0},
            {0,0,0,0}
    };

    // arr with enemy location
    // 0 - no enemy
    // 1 - random enemy

    public boolean notWall(int x, int y){
        if ( groundMap[x][y] == 0 || groundMap[x][y] == 1){
            return false;
        } else{
            return true;
        }
    }


}
