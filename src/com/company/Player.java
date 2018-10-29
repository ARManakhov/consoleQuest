package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

//todo отделить данные о игроке от отрисовки

/**
 * класс который содержит параметры игрока, его передвижение и тд
 */
public class Player {
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
    private static String name;
    private static int hp = DEFAULT_HP;
    private static int maxHP = DEFAULT_HP;
    private static int exp = DEFAULT_EXP;
    private static int lvl = DEFAULT_LVL;
    private static int money = DEFAULT_MONEY;
    private static int damage = DEFAULT_DAMAGE;
    private static double speed = DEFAULT_SPEED;                           //todo сделать инвентарь

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
}
