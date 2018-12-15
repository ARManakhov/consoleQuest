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
    private  static Character obj = null ;
    private  double realXPos;
    private  double realYPos;



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

    public static Character getPlayer(){
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

    /**
     *
     * @return скорость персонажа
     */
    public double getSpeed() {
        return speed;
    }
}
