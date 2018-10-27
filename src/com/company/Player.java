package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

//todo отделить данные о игроке от отрисовки

/**
 * класс который содержит параметры игрока, его передвижение и тд
 */
public class Player extends Сharacter {
    private final int DEFAULT_HP = 20;              // начальное колличество здровья
    private final int DEFAULT_LVL = 0;              // начальный уровень
    private final int DEFAULT_MONEY = 0;            // начальный уровень денег
    private final int DEFAULT_DAMAGE = 5;           // начальный наносимый урон
    private final int DEFAULT_EXP = 0;              // начальный уровень опыта
    private final double DEFAULT_SPEED  = 2.5;      // начальная скорость

                                                    // далее описаны константы изменений игрока при увеличении уровня
    private final int DEFAULT_DAMAGE_RAISE = 2;     // увеличение урона
    private final int DEFAULT_HP_RISE = 2;          // увеличение здоровья

    private final double PLAYER_SIZE_X = 16;        // размер игрока по x
    private final double PLAYER_SIZE_Y = 16;        // размер игрока по y

                                                    //далее идут переменные статистик персонажа
    private String name;
    private int hp;
    private int exp;
    private int maxHP;
    private int lvl;
    private int money;
    private int damage;
    private double speed;                           //todo сделать инвентарь

                                                    //переменные необходимые для отрисовки персонажа
    private double screenXPos;
    private double screenYPos;
    private static double limX1;
    private static double limY1;
    private static double limX2;
    private static double limY2;
    private double realXPos;
    private double realYPos;

    private static GraphicsContext gc = graphic.canvas.getGraphicsContext2D();
    private static Image playerTexture = new Image("resources/characters/player/spruce_sapling.png");

    private static boolean firstCall = true;

    /**
     * конструктор класса player
     * @param name имя персонажа
     */
    public Player(String name){
        this.name = name;
        this.hp = DEFAULT_HP;
        this.lvl = DEFAULT_LVL;
        this.money = DEFAULT_MONEY;
        this.screenXPos =  graphic.theScene.getWidth()/2;
        this.screenYPos =  graphic.theScene.getHeight()/2;

        this.realXPos =  0;
        this.realYPos =  0;


        this.damage = DEFAULT_DAMAGE;
        this.speed = DEFAULT_SPEED;
    }


    /**
     * метод который обновляет параметры игрока при смене уровня.
     */
    public void statUpdate(){
        this.lvl++;
        this.damage = DEFAULT_DAMAGE + this.lvl * DEFAULT_DAMAGE_RAISE;
        this.maxHP = DEFAULT_HP + this.lvl * DEFAULT_HP_RISE;

    }

    /**
     * метод для отрисовки игрока, и его движения
     * @param currentNanoTime
     */
    public void drawPlayer(long currentNanoTime, int mapNumber) {

        limFinder();
        if(firstCall){
            this.realXPos =  32 * Maps.worldMap[mapNumber].spawnPosX;
            this.realYPos =  32 * Maps.worldMap[mapNumber].spawnPosY;
            firstCall = false;
        }


        //todo наискасок скорость через корень

        if(KeyManager.pressedButt("LEFT")){

            if ((Maps.worldMap[mapNumber].borderMap[(int) (realYPos)/32][(int) (realXPos - speed)/32] != 1)
                    && (Maps.worldMap[mapNumber].borderMap[(int) (realYPos + PLAYER_SIZE_Y)/32][(int) (realXPos - speed)/32] != 1)){

                if (screenXPos > limX1) {
                    screenXPos-=speed;
                } else {
                    MapManager.moveMap(speed,"H");

                }

                realXPos-=speed;

            } else if((Maps.worldMap[mapNumber].borderMap[(int) (realYPos)/32][(int) (realXPos)/32] != 1)
                    && (Maps.worldMap[mapNumber].borderMap[(int) (realYPos + PLAYER_SIZE_Y)/32][(int) (realXPos )/32] != 1)){

                double move = realXPos % 32;

                if (screenXPos > limX1) {
                    screenXPos-=move;
                } else {
                    MapManager.moveMap(move,"H");
                }

                realXPos-=move;

            }
        }


        if (KeyManager.pressedButt("RIGHT")){
            if((Maps.worldMap[mapNumber].borderMap[(int) (realYPos)/32][(int) (realXPos + speed + PLAYER_SIZE_X)/32] != 1 )
                    && (Maps.worldMap[mapNumber].borderMap[(int) (realYPos + PLAYER_SIZE_Y)/32][(int) (realXPos + speed + PLAYER_SIZE_X)/32] != 1 )  ){
                if (screenXPos < limX2 ) {
                    screenXPos+=speed;
                } else {
                    MapManager.moveMap(-speed,"H");
                }
                realXPos+=speed;

            } else if((Maps.worldMap[mapNumber].borderMap[(int) (realYPos)/32][(int) (realXPos + PLAYER_SIZE_X)/32] != 1 )
                    && (Maps.worldMap[mapNumber].borderMap[(int) (realYPos + PLAYER_SIZE_Y)/32][(int) (realXPos  + PLAYER_SIZE_X)/32] != 1 )  ){

                double move = PLAYER_SIZE_X - realXPos % 32 - 1;

                if (screenXPos < limX2 ) {
                    screenXPos+=move;
                } else {
                    MapManager.moveMap(-move,"H");
                }
                realXPos+=move;

            }
        }


        if(KeyManager.pressedButt("UP")){
            double move = speed;

            if((Maps.worldMap[mapNumber].borderMap[(int) (realYPos - speed)/32][(int) realXPos/32] != 1 )
                    && (Maps.worldMap[mapNumber].borderMap[(int) (realYPos - speed)/32][(int) (realXPos+PLAYER_SIZE_X)/32] != 1 )) {
                if (screenYPos > limY1 ) {
                    screenYPos-=move;
                } else {
                    MapManager.moveMap(-move,"V");
                }
                realYPos-=move;

            }else if((Maps.worldMap[mapNumber].borderMap[(int) (realYPos)/32][(int) realXPos/32] != 1 )
                    && (Maps.worldMap[mapNumber].borderMap[(int) (realYPos)/32][(int) (realXPos+PLAYER_SIZE_X)/32] != 1 )) {

                move = realYPos % 32;

                if (screenYPos > limY1 ) {
                    screenYPos-=move;
                } else {
                    MapManager.moveMap(-move,"V");
                }
                realYPos-=move;

            }

        }

        if(KeyManager.pressedButt("DOWN")){
            if((Maps.worldMap[mapNumber].borderMap[(int) (realYPos + speed + PLAYER_SIZE_Y)/32][(int) realXPos/32] != 1 )
                    && (Maps.worldMap[mapNumber].borderMap[(int) (realYPos + speed + PLAYER_SIZE_Y)/32][(int) (realXPos+PLAYER_SIZE_X)/32] != 1 )){
                if (screenYPos < limY2) {
                    screenYPos+=speed;
                } else {
                    MapManager.moveMap(speed,"V");
                }
                realYPos+=speed;

            }else if((Maps.worldMap[mapNumber].borderMap[(int) (realYPos + PLAYER_SIZE_Y)/32][(int) realXPos/32] != 1 )
                    && (Maps.worldMap[mapNumber].borderMap[(int) (realYPos + PLAYER_SIZE_Y)/32][(int) (realXPos+PLAYER_SIZE_X)/32] != 1 )){

                double move = PLAYER_SIZE_Y - realYPos % 32 - 1;

                if (screenYPos < limY2) {
                    screenYPos+=move;
                } else {
                    MapManager.moveMap(move,"V");
                }
                realYPos+=move;

            }
        }


        gc.drawImage(playerTexture, this.screenXPos, this.screenYPos);

    }

    /**
     * метод который находит граници экрана где начинает двигатся карта а не персонаж по экрану
     */
    private static void limFinder(){
        int xSlice = (int) graphic.theScene.getWidth()/4;
        limX1 = xSlice;
        limX2 = xSlice * 3;

        int ySlice = (int) graphic.theScene.getHeight()/4;
        limY1 = ySlice;
        limY2 = ySlice * 3;
    }


}
