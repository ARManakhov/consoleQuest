package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import static java.lang.Math.sqrt;

public class PlayerManager {
    // размер спрайта
    private final double PLAYER_SIZE_X = 16;        // размер игрока по x
    private final double PLAYER_SIZE_Y = 16;        // размер игрока по y

    //переменные необходимые для отрисовки персонажа
    private double screenXPos;
    private double screenYPos;
    private double limX1;
    private double limY1;
    private double limX2;
    private double limY2;

    private double realXPos;
    private double realYPos;

    private long prevNanoTime;
    private final long NANO_TIME_DELTA = 17000000;

    private GraphicsContext gc = graphic.playerLayer.getGraphicsContext2D();
    private Image[] playerTexture = {
            new Image("resources/characters/player/0.png"),
            new Image("resources/characters/player/1.png"),
            new Image("resources/characters/player/2.png"),
            new Image("resources/characters/player/3.png"),
            new Image("resources/characters/player/4.png"),
            new Image("resources/characters/player/5.png"),
            new Image("resources/characters/player/6.png"),
            new Image("resources/characters/player/7.png")};

    private boolean firstCall = true;

    private static PlayerManager instance = null;
    public static PlayerManager getInstance(){
        if(instance == null){

            instance = new PlayerManager();
        }
        return instance;
    }

    private PlayerManager(){}

    /**
     * метод для отрисовки игрока, и его движения
     * @param currentNanoTime текущее время (для анимации)
     * @param mapNumber номер карты для отрисовки
     */
    public void draw(long currentNanoTime, int mapNumber) {
        limFinder();
        moveKeyManage(currentNanoTime, mapNumber);
        playerDrawManage();

    }

    /**
     * метод который отвечает за сторону в которую смотрит персонаж а так же за его отрисовку
     */
    private void playerDrawManage(){
        double mouseX = KeyManager.getMouseXPos() - PLAYER_SIZE_X / 2;
        double mouseY = KeyManager.getMouseYPos() - PLAYER_SIZE_Y / 2;

        double angleCos = (mouseX - screenXPos)/
                sqrt((mouseX-screenXPos)*(mouseX-screenXPos) +
                        (mouseY-screenYPos)*(mouseY-screenYPos));
        int dir=0;

        if(mouseY - screenYPos > 0){
            if (angleCos > 0.92 && angleCos <= 1){
                dir = 0;
            }
            if (angleCos > 0.38 && angleCos <= 0.92){
                dir = 1;
            }
            if (angleCos > -0.38 && angleCos <= 0.38){
                dir = 2;
            }
            if (angleCos > -0.92 && angleCos <= -0.38){
                dir = 3;
            }
            if (angleCos > -1 && angleCos <= -0.92){
                dir = 4;
            }
        } else {
            if (angleCos > 0.92 && angleCos <= 1){
                dir = 0;
            }
            if (angleCos > 0.38 && angleCos <= 0.92){
                dir = 7;
            }
            if (angleCos > -0.38 && angleCos <= 0.38){
                dir = 6;
            }
            if (angleCos > -0.92 && angleCos <= -0.38){
                dir = 5;
            }
            if (angleCos > -1 && angleCos <= -0.92){
                dir = 4;
            }
        }

        gc.clearRect(0, 0, graphic.theScene.getWidth(), graphic.theScene.getHeight());
        gc.drawImage(playerTexture[dir], screenXPos, screenYPos);
    }

    /**
     * метод занимающийся обработкой ввода с клавы, а так же передвижением персонажа
     * @param mapNumber номер текущей карты
     */
    private void moveKeyManage(long currentNanoTime, int mapNumber){
        Character player = Player.getPlayer();
        realXPos = player.getRealXPos();
        realYPos = player.getRealYPos();
        double speed = player.getSpeed();

        if(firstCall){
            prevNanoTime = currentNanoTime - NANO_TIME_DELTA;

            realXPos =  32 * Maps.worldMap[mapNumber].spawnPosX;
            realYPos =  32 * Maps.worldMap[mapNumber].spawnPosY;

            screenXPos =  graphic.theScene.getWidth()/2;
            screenYPos =  graphic.theScene.getHeight()/2;

            firstCall = false;
        }

        speed = speed * (currentNanoTime - prevNanoTime) / NANO_TIME_DELTA;
        prevNanoTime = currentNanoTime;

        boolean canMoveLeftPrim = (Maps.worldMap[mapNumber].borderMap[(int) (realYPos)/32][(int) (realXPos - speed)/32] != 1)
                && (Maps.worldMap[mapNumber].borderMap[(int) (realYPos + PLAYER_SIZE_Y)/32][(int) (realXPos - speed)/32] != 1);
        boolean canMoveRightPrim = (Maps.worldMap[mapNumber].borderMap[(int) (realYPos)/32][(int) (realXPos + speed + PLAYER_SIZE_X)/32] != 1 )
                && (Maps.worldMap[mapNumber].borderMap[(int) (realYPos + PLAYER_SIZE_Y)/32][(int) (realXPos + speed + PLAYER_SIZE_X)/32] != 1 );
        boolean canMoveUpPrim = (Maps.worldMap[mapNumber].borderMap[(int) (realYPos - speed)/32][(int) realXPos/32] != 1 )
                && (Maps.worldMap[mapNumber].borderMap[(int) (realYPos - speed)/32][(int) (realXPos+PLAYER_SIZE_X)/32] != 1 );
        boolean canMoveDownPrim = (Maps.worldMap[mapNumber].borderMap[(int) (realYPos + speed + PLAYER_SIZE_Y)/32][(int) realXPos/32] != 1 )
                && (Maps.worldMap[mapNumber].borderMap[(int) (realYPos + speed + PLAYER_SIZE_Y)/32][(int) (realXPos+PLAYER_SIZE_X)/32] != 1 );


        boolean canMoveLeftSec = ((Maps.worldMap[mapNumber].borderMap[(int) (realYPos)/32][(int) (realXPos)/32] != 1)
                && (Maps.worldMap[mapNumber].borderMap[(int) (realYPos + PLAYER_SIZE_Y)/32][(int) (realXPos )/32] != 1));
        boolean canMoveRightSec = ((Maps.worldMap[mapNumber].borderMap[(int) (realYPos)/32][(int) (realXPos + PLAYER_SIZE_X)/32] != 1 )
                && (Maps.worldMap[mapNumber].borderMap[(int) (realYPos + PLAYER_SIZE_Y)/32][(int) (realXPos  + PLAYER_SIZE_X)/32] != 1 ));
        boolean canMoveUpSec = ((Maps.worldMap[mapNumber].borderMap[(int) (realYPos)/32][(int) realXPos/32] != 1 )
                && (Maps.worldMap[mapNumber].borderMap[(int) (realYPos)/32][(int) (realXPos+PLAYER_SIZE_X)/32] != 1 ));
        boolean canMoveDownSec = ((Maps.worldMap[mapNumber].borderMap[(int) (realYPos + PLAYER_SIZE_Y)/32][(int) realXPos/32] != 1 )
                && (Maps.worldMap[mapNumber].borderMap[(int) (realYPos + PLAYER_SIZE_Y)/32][(int) (realXPos+PLAYER_SIZE_X)/32] != 1 ));

        if(((KeyManager.pressedButt("LEFT") && canMoveLeftPrim) && ((KeyManager.pressedButt("UP") && canMoveUpPrim) || (KeyManager.pressedButt("DOWN") && canMoveDownPrim))) ||
                ((KeyManager.pressedButt("RIGHT") && canMoveRightPrim) && ((KeyManager.pressedButt("UP") && canMoveUpPrim) || (KeyManager.pressedButt("DOWN") && canMoveDownPrim)))
        ){
            speed = speed * 0.707;              //при двух нажатих кнопках скорость по осям умножаем на cos 45 градусов
        }//todo учитывать барьеры



        if(KeyManager.pressedButt("LEFT")){
            double move = 0;

            if (canMoveLeftPrim){
                move = speed;
            } else if(canMoveLeftSec){
                move = realXPos % 32;
            }

            if (screenXPos > limX1) {
                screenXPos-=move;
            } else {
                MapManager.moveMap(move,"H");
            }
            realXPos-=move;
        }


        if (KeyManager.pressedButt("RIGHT")){
            double move = 0;

            if(canMoveRightPrim){
                move = speed;
            } else if(canMoveRightSec){
                move = PLAYER_SIZE_X - realXPos % 32 - 1;
            }

            if (screenXPos < limX2 ) {
                screenXPos+=move;
            } else {
                MapManager.moveMap(-move,"H");
            }
            realXPos+=move;
        }


        if(KeyManager.pressedButt("UP")){
            double move = 0;

            if(canMoveUpPrim) {
                move = speed;
            }else if(canMoveUpSec) {

                move = realYPos % 32;
            }

            if (screenYPos > limY1 ) {
                screenYPos-=move;
            } else {
                MapManager.moveMap(-move,"V");
            }
            realYPos-=move;
        }

        if(KeyManager.pressedButt("DOWN")){
            double move = 0;

            if(canMoveDownPrim){
                move = speed;
            }else if(canMoveDownSec){

                move = PLAYER_SIZE_Y - realYPos % 32 - 1;
            }

            if (screenYPos < limY2) {
                screenYPos+=move;
            } else {
                MapManager.moveMap(move,"V");
            }
            realYPos+=move;
        }

        if(Maps.worldMap[mapNumber].teleportMap[(int) (realYPos)/32][(int) (realXPos)/32] != 0){

            graphic.currentMapNumber = Maps.worldMap[mapNumber].teleportMap[(int) (realYPos)/32][(int) (realXPos)/32];
            firstCall = true;
            MapManager.setAsFirstCall();
        }

        player.setRealXPos(realXPos);
        player.setRealYPos(realYPos);
    }

    /**
     * метод который находит граници экрана где начинает двигатся карта а не персонаж по экрану
     */
    private void limFinder(){
        int xSlice = (int) graphic.theScene.getWidth()/4;
        limX1 = xSlice;
        limX2 = xSlice * 3;

        int ySlice = (int) graphic.theScene.getHeight()/4;
        limY1 = ySlice;
        limY2 = ySlice * 3;
    }
}