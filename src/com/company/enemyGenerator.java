package com.company;

import java.util.ArrayList;
import java.util.List;


public class enemyGenerator {
    private        List<Enemy> enemy = new ArrayList<>();
    private       List<EnemyManager> enemyManager = new ArrayList<>();

    private static enemyGenerator instance;

    public void Random_Generate_on_map(int curent){

        for (int i = 0; i < Maps.worldMap[curent].borderMap.length; i++) {
            for (int j = 0; j < Maps.worldMap[curent].borderMap[i].length; j++) {
                if(Maps.worldMap[curent].borderMap[i][j]==0 ){
                    Enemy e = new Enemy(i*32,j*32);
                    enemy.add(e);
                    enemyManager.add(new EnemyManager(e));
                }
            }
        }

    }

    public void render(long time, int map ){
        for (EnemyManager em: enemyManager) {
            em.drawEnemy(time,map);
        }
    }
    private void spawn(){
        int low = 50;
        int max = 250;

    }

    private enemyGenerator(){

    }

    public static enemyGenerator getInstance(){
        if(instance == null){
            instance = new enemyGenerator();
        }
        return instance;
    }

}
