package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class EnemyGenerator {
    private        List<Enemy> enemy = new ArrayList<>();
    private       List<EnemyManager> enemyManager = new ArrayList<>();
    private Random rand = new Random();
    private static EnemyGenerator instance;
    private final int SPAWN_K = 40;

    public void generateMobs(int curent){

        for (int i = 0; i < Map.maps[curent].borderMap.length; i++) {
            for (int j = 0; j < Map.maps[curent].borderMap[i].length; j++) {
                if((Map.maps[curent].borderMap[i][j] == 0) && ( rand.nextInt(SPAWN_K) == 0)){
                    Enemy e = new Enemy(j*32,i*32);
                    enemy.add(e);
                    enemyManager.add(new EnemyManager(e));
                }
            }
        }
    }

    public void renderMobs(long time, int map ){
        for (EnemyManager em: enemyManager) {
            em.drawEnemy(time,map);
        }
    }

    public void attackMobs(long time, int map ){
        for (Enemy e: enemy) {
            e.EnemyAttack(Player.getPlayer());
        }
    }

    private EnemyGenerator(){

    }

    public static EnemyGenerator getInstance(){
        if(instance == null){
            instance = new EnemyGenerator();
        }
        return instance;
    }

}
