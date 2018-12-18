package com.company;

public class Character {
    int hp;
      int maxHP;
      int exp;
      int maxExp;
      int lvl;
      int money;
      int damage;
      double speed;
      double realXPos;
      double realYPos;
     boolean alive = true;

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

    /**
     * метод который обновляет параметры персонажа при смене уровня.
     */
    public void statUpdate(){}

}
