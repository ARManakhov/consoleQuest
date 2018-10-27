package com.company;


/**класс "персонаж"
 *
 */
abstract class Сharacter {
                                                // переменные статов персонажей
    private String name;
    private int hp;
    private int lvl;
    private int damage;

    /**
     * метод обнавляющий статы персонажа при смене уровня
     */
    abstract void statUpdate();

}
