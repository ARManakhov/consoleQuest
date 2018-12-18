package com.company;


import java.util.List;

import static java.lang.Math.sqrt;

/**
 * класс который содержит параметры игрока, его передвижение и тд
 */
public class Player extends Character {

    boolean alive = true;
    private static final int DEFAULT_HP = 100;              // начальное колличество здровья
    private static final int DEFAULT_LVL = 0;              // начальный уровень
    private static final int DEFAULT_MONEY = 0;            // начальный уровень денег
    private static final int DEFAULT_DAMAGE = 25;           // начальный наносимый урон
    private static final int DEFAULT_EXP = 0;              // начальный уровень опыта
    private static final int DEFAULT_MAX_EXP = 10;              // начальный уровень опыта
    private static final double DEFAULT_SPEED  = 2.5;      // начальная скорость

    // далее описаны константы изменений игрока при увеличении уровня
    private static final int DEFAULT_DAMAGE_RAISE = 2;     // увеличение урона
    private static final int DEFAULT_HP_RISE = 2;          // увеличение здоровья


    private static int ATK_TIME_DELTA = 100000000;
    private long ATK_TIME = 0;

    private int PlayerAttackRange = 40;

    //далее идут переменные статистик персонажа
    private  String name;
    private  int hp = DEFAULT_HP;
    private  int maxHP = DEFAULT_HP;
    private  int exp = DEFAULT_EXP;
    private  int maxExp = DEFAULT_MAX_EXP;
    private  int lvl = DEFAULT_LVL;
    private  int money = DEFAULT_MONEY;
    private  int damage = DEFAULT_DAMAGE;
    private  double speed = DEFAULT_SPEED;
    private  static Player obj = null ;
    private  double realXPos;
    private  double realYPos;

    boolean removed = true;

    private int addMaxHp = 0;  //max HP
    private int addDmg = 0;    //DMG
    private int addSpeed = 0;  //Speed


    public int getAddDmg() {
        return addDmg;
    }

    public void setAddDmg(int addDmg) {
        this.addDmg = addDmg;
    }

    public int getAddSpeed() {
        return addSpeed;
    }

    public void setAddSpeed(int addSpeed) {
        this.addSpeed = addSpeed;
    }






    public int getAddMaxHp() {
        return addMaxHp;
    }

    public void setAddMaxHp(int addMaxHp) {
        this.addMaxHp = addMaxHp;
    }




    public static Player getPlayer(){
        if (obj == null){
            obj = new Player();
        }
        return obj;
    }

    private Player(){
        this.hp = DEFAULT_HP;
        this.maxHP = DEFAULT_HP;
        this.exp = DEFAULT_EXP;
        this.lvl = DEFAULT_LVL;
        this.money = DEFAULT_MONEY;
        this.damage = DEFAULT_DAMAGE;
        this.speed = DEFAULT_SPEED;                           //todo сделать инвентарь

    }
    /**
     * метод который обновляет параметры игрока при смене уровня.
     */
    public void statUpdate(){
        lvl++;
        damage = DEFAULT_DAMAGE + lvl * DEFAULT_DAMAGE_RAISE;
        maxHP = DEFAULT_HP + lvl * DEFAULT_HP_RISE;

    }

    public void  removeAddble(){
        if(!removed){
            maxHP -= addMaxHp;
            damage -= addDmg;
            speed -= addSpeed;

            removed = true;
        }
    }

    public void  addAddble(){
        if(removed){
            maxHP += addMaxHp;
            damage += addDmg;
            speed += addSpeed;
            removed = false;
        }
    }






    public  void attackArray(List<Enemy> chl, long curentNanoTime){
        for (Enemy ch : chl) {
            double x0 = (realXPos - ch.getRealXPos())*(realXPos - ch.getRealXPos());
            double y0 = (realYPos - ch.getRealYPos())*(realYPos - ch.getRealYPos());
            double attackRange = x0 + y0;

            if (isAlive() && (ch.isAlive()) && (attackRange < PlayerAttackRange*PlayerAttackRange )){
                double x = ch.getRealXPos();
                double y = ch.getRealYPos();
                double angleCos = (x - realXPos)/
                        sqrt((x-realXPos)*(x-realXPos) + (y-realYPos)*(y-realYPos));
                int dir = 0;
                if(y - realYPos > 0){
                    if (angleCos > 0.707 && angleCos <= 1){
                        dir = 0;
                    }
                    if (angleCos > -0.707 && angleCos <= 0.707){
                        dir = 1;
                    }
                    if (angleCos > -1 && angleCos <= -0.707){
                        dir = 2;
                    }
                } else {
                    if (angleCos > 0.707 && angleCos <= 1){
                        dir = 0;
                    }
                    if (angleCos > -0.707 && angleCos <= 0.707){
                        dir = 3;
                    }
                    if (angleCos > -1 && angleCos <= -0.707){
                        dir = 2;
                    }
                }
                if (dir == PlayerManager.getInstance().dir){
                    attack(ch, curentNanoTime);
                }
            }
        }
    }

    public void attack(Enemy ch, long currentNanoTime){
        if(currentNanoTime - ATK_TIME >= ATK_TIME_DELTA){
            ch.setHp(ch.getHp() - damage);
            ATK_TIME = currentNanoTime;
        }

    }

    public boolean canAttack(long currentNanoTime){
        return currentNanoTime - ATK_TIME >= ATK_TIME_DELTA;
    }

    public void makeAlive(){
        obj = new Player();

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