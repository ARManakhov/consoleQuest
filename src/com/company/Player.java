package com.company;



/**
 * класс который содержит параметры игрока, его передвижение и тд
 */
public class Player extends Character {
    private static final int DEFAULT_HP = 20;              // начальное колличество здровья
    private static final int DEFAULT_LVL = 0;              // начальный уровень
    private static final int DEFAULT_MONEY = 0;            // начальный уровень денег
    private static final int DEFAULT_DAMAGE = 5;           // начальный наносимый урон
    private static final int DEFAULT_EXP = 0;              // начальный уровень опыта
    private static final double DEFAULT_SPEED  = 2.5;      // начальная скорость

    // далее описаны константы изменений игрока при увеличении уровня
    private static final int DEFAULT_DAMAGE_RAISE = 2;     // увеличение урона
    private static final int DEFAULT_HP_RISE = 2;          // увеличение здоровья


                                                    //далее идут переменные статистик персонажа
    private  String name;
    private  int hp = DEFAULT_HP;
    private  int maxHP = DEFAULT_HP;
    private  int exp = DEFAULT_EXP;
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



    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getAddMaxHp() {
        return addMaxHp;
    }

    public void setAddMaxHp(int addMaxHp) {
        this.addMaxHp = addMaxHp;
    }

    /**
     *
     * @return
     */
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
    public int getHp() {
        return hp;
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

    /**
     *
     * @return скорость персонажа
     */
    public double getSpeed() {
        return speed;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHP() {
        return maxHP;
    }
}
