package com.company;


/**класс "персонаж"
 *
 */
abstract class Enemy {
                                                // переменные статов персонажей
    private String name;
    private int hp;
    private int lvl;
    private int damage;
    private int heal;

    /**
     * метод обнавляющий статы персонажа при смене уровня
     */
    abstract void statUpdate();

}
