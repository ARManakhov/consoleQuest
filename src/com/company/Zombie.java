package com.company;

public class Zombie {
    private static final int DEFAULT_HP = 10;
    private static final int DEFAULT_DAMAGE = 5;
    private static final double DEFAULT_SPEED = 2.5D;
    private static String name;
    private static int hp = 10;
    private static int maxHP = 10;
    private static int damage = 5;
    public static double speed = 2.5D;

    public Zombie() {
    }

    public static double getSpeed() {
        return speed;
    }
}
