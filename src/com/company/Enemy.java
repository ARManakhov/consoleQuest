package com.company;


/**класс "персонаж"
 *
 */
public class Enemy extends Character {

    boolean alive = true;

    private static int ATK_TIME_DELTA = 1000000000;
    private long ATK_TIME = 0;
    private static final int ENEMY_DEFAULT_HP = 50;
    private static final int ENEMY_DEFAULT_DAMAGE = 2;
    private static final double ENEMY_DEFAULT_SPEED = 2.5;
    private static final int ENEMY_DEFAULT_ATTACK_BAR = 25;
    private static final double ENEMY_DEFAULT_ATTACK_SPEED = 0.5;
    private static final int ENEMY_DEFAULT_DAMAGE_RAISE = 2;
    private static final int ENEMY_DEFAULT_HP_RISE = 2;

    private String name;
    public int hp = ENEMY_DEFAULT_HP;
    private int maxHP = ENEMY_DEFAULT_HP;
    public int damage = ENEMY_DEFAULT_DAMAGE;
    private double speed = ENEMY_DEFAULT_SPEED;
    private int attackbarr = ENEMY_DEFAULT_ATTACK_BAR;
    public double attackspeed = ENEMY_DEFAULT_ATTACK_SPEED;
    private  double realXPos;
    private  double realYPos;


    public double EnemyAttackRange = 65;

    public Enemy(){
        this.hp = ENEMY_DEFAULT_HP;
        this.maxHP = ENEMY_DEFAULT_HP;
        this.damage = ENEMY_DEFAULT_DAMAGE;
        this.speed = ENEMY_DEFAULT_SPEED;                           //todo сделать инвентарь

        this.realXPos = 256;
        this.realYPos= 256;

    }

    public Enemy(int x,int y){
        this.hp = ENEMY_DEFAULT_HP;
        this.maxHP = ENEMY_DEFAULT_HP;
        this.damage = ENEMY_DEFAULT_DAMAGE;
        this.speed = ENEMY_DEFAULT_SPEED;

        this.realXPos = x;
        this.realYPos = y;
    }




    public void statUpdate() {
    }



    public void EnemyAttack(Character ch, long curentNanoTime){
        if(curentNanoTime - ATK_TIME >= ATK_TIME_DELTA){
            double x = (realXPos - ch.getRealXPos())*(realXPos - ch.getRealXPos());
            double y = (realYPos - ch.getRealYPos())*(realYPos - ch.getRealYPos());
            double attackRange = Math.sqrt(x + y);
            if (alive && ch.isAlive() && (attackRange < EnemyAttackRange )){ //todo сделать по красоте
                ch.setHp(ch.getHp() - damage);
            }
            ATK_TIME = curentNanoTime;
        }

    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getMaxExp() {
        return maxExp;
    }

    public void setMaxExp(int maxExp) {
        this.maxExp = maxExp;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
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

    boolean isAlive() {
        if(hp <= 0){
            alive = false;
        }
        return alive;
    }
}
