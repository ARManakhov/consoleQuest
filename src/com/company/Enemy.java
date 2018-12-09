package com.company;


import javafx.scene.image.Image;

import javax.xml.bind.annotation.XmlType;

/**класс "персонаж"
 *
 */
public class Enemy extends Character {
    private static final int ENEMYDEFAULT_HP = 50;
    private static final int ENEMYDEFAULT_DAMAGE = 5;
    private static final double ENEMYDEFAULT_SPEED  = 2.5;
    private static final int ENEMYDEFAULT_ATTACKBARR = 25;
    private static final double ENEMYDEFAULT_ATTACKSPEED = 0.5;
    private static final int ENEMYDEFAULT_DAMAGE_RAISE = 2;
    private static final int ENEMYDEFAULT_HP_RISE = 2;

    private String name;
    public int hp = ENEMYDEFAULT_HP;
    private int maxHP = ENEMYDEFAULT_HP;
    public int damage = ENEMYDEFAULT_DAMAGE;
    private double speed = ENEMYDEFAULT_SPEED;
    private int attackbarr = ENEMYDEFAULT_ATTACKBARR;
    public double attackspeed = ENEMYDEFAULT_ATTACKSPEED;
    private  double realXPos = 128;
    private  double realYPos = 128;

    public double EnemyAttackRange = 100;

    public Enemy(){
        this.hp = ENEMYDEFAULT_HP;
        this.maxHP = ENEMYDEFAULT_HP;
        this.damage = ENEMYDEFAULT_DAMAGE;
        this.speed = ENEMYDEFAULT_SPEED;                           //todo сделать инвентарь

    }

    public Enemy(int x,int y){
        this.hp = ENEMYDEFAULT_HP;
        this.maxHP = ENEMYDEFAULT_HP;
        this.damage = ENEMYDEFAULT_DAMAGE;
        this.speed = ENEMYDEFAULT_SPEED;

        this.realXPos = x;
        this.realYPos = y;
    }

    public double getRealXPos() {
        return realXPos;
    }

    public void setRealXPos(double realXPos) {
        this.realXPos = realXPos;
    }

    public double getRealYPos() {
        return realYPos;
    }

    public void setRealYPos(double realYPos) {
        this.realYPos = realYPos;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    public void statUpdate() {
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    public void EnemyAttack(Character ch){
        double x = (realXPos - ch.getRealXPos())*(realXPos - ch.getRealXPos());
        double y = (realYPos - ch.getRealYPos())*(realYPos - ch.getRealYPos());
        double attackrange = Math.sqrt(x + y);
        if (/*(Enemy.ENEMYDEFAULT_HP != this.hp) && */ (this.hp > 0) && (ch.getHp() > 0) && (attackrange < EnemyAttackRange )){ //todo сделать по красоте
            ch.setHp(ch.getHp() - damage);
        }
    }
}
