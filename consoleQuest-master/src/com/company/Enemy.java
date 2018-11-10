package com.company;


import javax.xml.bind.annotation.XmlType;

/**класс "персонаж"
 *
 */
public class Enemy {
    private static final int ENEMYDEFAULT_HP = 100;
    private static final int ENEMYDEFAULT_DAMAGE = 5;
    private static final double ENEMYDEFAULT_SPEED  = 2.5;
    private static final int ENEMYDEFAULT_ATTACKBARR = 25;
    private static final double ENEMYDEFAULT_ATTACKSPEED = 0.5;
    private static final int ENEMYDEFAULT_DAMAGE_RAISE = 2;
    private static final int ENEMYDEFAULT_HP_RISE = 2;

    private static String name;
    public static int hp = ENEMYDEFAULT_HP;
    private static int maxHP = ENEMYDEFAULT_HP;
    private static int lvl = lvl = Player.lvl;;
    public static int damage = ENEMYDEFAULT_DAMAGE;
    private static double speed = ENEMYDEFAULT_SPEED;
    private static int attackbarr = ENEMYDEFAULT_ATTACKBARR;
    public static double attackspeed = ENEMYDEFAULT_ATTACKSPEED;

    public static double EnemyAttackRange = 100;


    public void statGenerate(){
        lvl = Player.lvl;
        damage = ENEMYDEFAULT_DAMAGE + lvl * ENEMYDEFAULT_DAMAGE_RAISE;
        maxHP = ENEMYDEFAULT_HP + lvl * ENEMYDEFAULT_HP_RISE;
    }

    public static void EnemyAttack(){
        double x = (EnemyManager.RealXPos - PlayerManager.realXPos)*(EnemyManager.RealXPos - PlayerManager.realXPos);
        double y = (EnemyManager.RealYPos - PlayerManager.realYPos)*(EnemyManager.RealYPos - PlayerManager.realYPos);
        double attackrange = Math.sqrt(x + y);
        if (( KeyManager.getMousePresetButt("PRIMARY") == true) && (Enemy.ENEMYDEFAULT_HP != Enemy.hp) && (Enemy.hp > 0) && (Player.hp > 0) && (attackrange < EnemyAttackRange )){
            Player.hp = Player.hp - Enemy.damage;
        }
    }
}
