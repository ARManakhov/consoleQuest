package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

//todo отделить данные о игроке от отрисовки

/**
 * класс который содержит параметры игрока, его передвижение и тд
 */
public class Player {
    private static final int DEFAULT_HP = 100;              // начальное колличество здровья
    private static final int DEFAULT_LVL = 0;              // начальный уровень
    private static final int DEFAULT_MONEY = 0;            // начальный уровень денег
    private static final int DEFAULT_DAMAGE = 5;           // начальный наносимый урон
    private static final int DEFAULT_EXP = 0;              // начальный уровень опыта
    private static final double DEFAULT_SPEED  = 2.5;      // начальная скорость
    private static final int DEFAULT_ATTACKBARR = 100;     // начальный атак бар
    private static final double DEFAULT_ATTACKSPEED = 1;      // начальная скорость атаки

                                                    // далее описаны константы изменений игрока при увеличении уровня
    private static final int DEFAULT_DAMAGE_RAISE = 2;     // увеличение урона
    private static final int DEFAULT_HP_RISE = 2;          // увеличение здоровья

    public static double PlayerAttackRange = 25;

                                                    //далее идут переменные статистик персонажа
    private static String name;
    public static int hp = DEFAULT_HP;
    private static int maxHP = DEFAULT_HP;
    private static int exp = DEFAULT_EXP;
    public static int lvl = DEFAULT_LVL;
    private static int money = DEFAULT_MONEY;
    public static int damage = DEFAULT_DAMAGE;
    private static double speed = DEFAULT_SPEED;
    private static int attackbarr = DEFAULT_ATTACKBARR;
    public static double attackspeed = DEFAULT_ATTACKSPEED;
    //todo сделать инвентарь

    /**
     * метод который обновляет параметры игрока при смене уровня.
     */
    public static void statUpdate(){
        lvl++;
        damage = DEFAULT_DAMAGE + lvl * DEFAULT_DAMAGE_RAISE;
        maxHP = DEFAULT_HP + lvl * DEFAULT_HP_RISE;

    }


        /**
         *
         * @return скорость персонажа
         */
        public static double getSpeed() {
            return speed;
        }
    public static void PlayerAttack() {
        if ((KeyManager.getMousePresetButt("PRIMARY") == true) && (Player.hp > 0) && (Enemy.hp > 0) && (Enemy.attackrange < PlayerAttackRange)) {
            Enemy.hp = Enemy.hp - Player.damage;
        }
    }
}
