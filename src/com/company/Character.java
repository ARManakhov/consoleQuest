package com.company;

abstract class Character {
    /**
     *
     * @return возвращает текущую позицию по оси X
     */
    abstract double getRealXPos();

    /**
     *
     * @param realXPos установить текущую позицию по оси X
     */
    abstract void setRealXPos(double realXPos);

    /**
     *
     * @return возвращает текущую позицию по оси Y
     */
    abstract double getRealYPos();

    /**
     *
     * @param realYPos установить текущую позицию по оси Y
     */
    abstract void setRealYPos(double realYPos);

    /**
     *
     * @return скорость персонажа
     */
    abstract double getSpeed();


    /**
     * метод который обновляет параметры персонажа при смене уровня.
     */
    abstract void statUpdate();
    abstract int getHp();
    abstract void setHp(int hp);
}

