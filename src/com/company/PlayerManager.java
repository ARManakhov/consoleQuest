package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;

import static java.lang.Math.sqrt;

public class PlayerManager {
    // размер спрайта
    private final double PLAYER_SIZE_X = 32;        // размер игрока по x
    private final double PLAYER_SIZE_Y = 32;        // размер игрока по y

    //переменные необходимые для отрисовки персонажа
    private double screenXPos;
    private double screenYPos;
    private double limX1;
    private double limY1;
    private double limX2;
    private double limY2;
    private final byte ANUMATION_FRAMES_COUNT = 3;
    private byte curentFrameNum = 0; //текущий кадр для анимации

    private static int blockSize = MapManager.getBlockSize();

    private double realXPos;
    private double realYPos;

    private long prevNanoTime;
    private long prevAnimNanoTime;

    private final long NANO_TIME_DELTA     = 17000000;
    private final long ANIMTION_TIME_DELTA = 100000000;


    private GraphicsContext gc = graphic.playerLayer.getGraphicsContext2D();
    private Image[] playerTexture;

    private boolean firstCall = true;

    private static PlayerManager instance = null;
    public static PlayerManager getInstance(){
        if(instance == null){
            instance = new PlayerManager();
            TextureGet tg = new TextureGet(new File ("./resources/characters/player/"));

            instance.playerTexture = tg.getTexture();
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
            if (angleCos > 0.707 && angleCos <= 1){
                dir = 0;
            }
            if (angleCos > -0.707 && angleCos <= 0.707){
                dir = 1;
            }
            if (angleCos > -1 && angleCos <= -0.707){
                dir = 2;
            }
        } else {
            if (angleCos > 0.707 && angleCos <= 1){
                dir = 0;
            }
            if (angleCos > -0.707 && angleCos <= 0.707){
                dir = 3;
            }
            if (angleCos > -1 && angleCos <= -0.707){
                dir = 2;
            }
        }
        //System.out.println(dir);

        gc.clearRect(0, 0, graphic.theScene.getWidth(), graphic.theScene.getHeight());
        gc.drawImage(playerTexture[dir * ANUMATION_FRAMES_COUNT + curentFrameNum], screenXPos, screenYPos);
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
            prevAnimNanoTime = currentNanoTime - ANIMTION_TIME_DELTA;
            realXPos =  blockSize * Map.maps[mapNumber].spawnPosX;
            realYPos =  blockSize * Map.maps[mapNumber].spawnPosY;

            screenXPos =  graphic.theScene.getWidth()/2;
            screenYPos =  graphic.theScene.getHeight()/2;

            firstCall = false;
        }

        speed = speed * (currentNanoTime - prevNanoTime) / NANO_TIME_DELTA;
        prevNanoTime = currentNanoTime;


        boolean canMoveLeftPrim = (Map.maps[mapNumber].borderMap[(int) (realYPos)/ blockSize][(int) (realXPos - speed)/ blockSize] != 1)
                && (Map.maps[mapNumber].borderMap[(int) (realYPos + PLAYER_SIZE_Y)/ blockSize][(int) (realXPos - speed)/ blockSize] != 1);
        boolean canMoveRightPrim = (Map.maps[mapNumber].borderMap[(int) (realYPos)/ blockSize][(int) (realXPos + speed + PLAYER_SIZE_X)/ blockSize] != 1 )
                && (Map.maps[mapNumber].borderMap[(int) (realYPos + PLAYER_SIZE_Y)/ blockSize][(int) (realXPos + speed + PLAYER_SIZE_X)/ blockSize] != 1 );
        boolean canMoveUpPrim = (Map.maps[mapNumber].borderMap[(int) (realYPos - speed)/ blockSize][(int) realXPos/ blockSize] != 1 )
                && (Map.maps[mapNumber].borderMap[(int) (realYPos - speed)/ blockSize][(int) (realXPos+PLAYER_SIZE_X)/ blockSize] != 1 );
        boolean canMoveDownPrim = (Map.maps[mapNumber].borderMap[(int) (realYPos + speed + PLAYER_SIZE_Y)/ blockSize][(int) realXPos/ blockSize] != 1 )
                && (Map.maps[mapNumber].borderMap[(int) (realYPos + speed + PLAYER_SIZE_Y)/ blockSize][(int) (realXPos+PLAYER_SIZE_X)/ blockSize] != 1 );



        boolean canMoveLeftSec = ((Map.maps[mapNumber].borderMap[(int) (realYPos)/ blockSize][(int) (realXPos)/ blockSize] != 1)
                && (Map.maps[mapNumber].borderMap[(int) (realYPos + PLAYER_SIZE_Y)/ blockSize][(int) (realXPos )/ blockSize] != 1));
        boolean canMoveRightSec = ((Map.maps[mapNumber].borderMap[(int) (realYPos)/ blockSize][(int) (realXPos + PLAYER_SIZE_X)/ blockSize] != 1 )
                && (Map.maps[mapNumber].borderMap[(int) (realYPos + PLAYER_SIZE_Y)/ blockSize][(int) (realXPos  + PLAYER_SIZE_X)/ blockSize] != 1 ));
        boolean canMoveUpSec = ((Map.maps[mapNumber].borderMap[(int) (realYPos)/ blockSize][(int) realXPos/ blockSize] != 1 )
                && (Map.maps[mapNumber].borderMap[(int) (realYPos)/ blockSize][(int) (realXPos+PLAYER_SIZE_X)/ blockSize] != 1 ));
        boolean canMoveDownSec = ((Map.maps[mapNumber].borderMap[(int) (realYPos + PLAYER_SIZE_Y)/ blockSize][(int) realXPos/ blockSize] != 1 )
                && (Map.maps[mapNumber].borderMap[(int) (realYPos + PLAYER_SIZE_Y)/ blockSize][(int) (realXPos+PLAYER_SIZE_X)/ blockSize] != 1 ));

        if(((KeyManager.pressedButt("LEFT") && canMoveLeftPrim) && ((KeyManager.pressedButt("UP") && canMoveUpPrim) || (KeyManager.pressedButt("DOWN") && canMoveDownPrim))) ||
                ((KeyManager.pressedButt("RIGHT") && canMoveRightPrim) && ((KeyManager.pressedButt("UP") && canMoveUpPrim) || (KeyManager.pressedButt("DOWN") && canMoveDownPrim)))
        ){
            speed = speed * 0.707;              //при двух нажатих кнопках скорость по осям умножаем на cos 45 градусов
        }


        boolean movedL = false;
        boolean movedR = false;
        boolean movedU = false;
        boolean movedD = false;

        if(KeyManager.pressedButt("LEFT")){
            movedL = true;

            double move = 0;
            if (canMoveLeftPrim){
                move = speed;
            } else if(canMoveLeftSec){
                move = realXPos % blockSize;
            }

            if (screenXPos > limX1) {
                screenXPos-=move;
            } else {
                MapManager.moveMap(move,"H");
            }
            realXPos-=move;
        }


        if (KeyManager.pressedButt("RIGHT")){
            movedR = true;


            double move = 0;

            if(canMoveRightPrim){
                move = speed;
            } else if(canMoveRightSec){
                move = PLAYER_SIZE_X - realXPos % blockSize - 1;
            }

            if (screenXPos < limX2 ) {
                screenXPos+=move;
            } else {
                MapManager.moveMap(-move,"H");
            }
            realXPos+=move;
        }


        if(KeyManager.pressedButt("UP")){
            movedU = true;


            double move = 0;

            if(canMoveUpPrim) {
                move = speed;
            }else if(canMoveUpSec) {

                move = realYPos % blockSize;
            }

            if (screenYPos > limY1 ) {
                screenYPos-=move;
            } else {
                MapManager.moveMap(-move,"V");
            }
            realYPos-=move;
        }

        if(KeyManager.pressedButt("DOWN")){

            movedD = true;

            double move = 0;

            if(canMoveDownPrim){
                move = speed;
            }else if(canMoveDownSec){

                move = PLAYER_SIZE_Y - realYPos % blockSize - 1;
            }

            if (screenYPos < limY2) {
                screenYPos+=move;
            } else {
                MapManager.moveMap(move,"V");
            }
            realYPos+=move;
        }

        if ( movedD || movedL || movedR || movedU ){
            if( currentNanoTime - prevAnimNanoTime > ANIMTION_TIME_DELTA ){
                prevAnimNanoTime = currentNanoTime;
                if(curentFrameNum != ANUMATION_FRAMES_COUNT - 1){
                    curentFrameNum++;
                } else{
                    curentFrameNum=0;
                }
            }
        } else {
            curentFrameNum = 1;
        }

        if(Map.maps[mapNumber].teleportMap[(int) (realYPos)/ blockSize][(int) (realXPos)/ blockSize] != 0){

            graphic.currentMapNumber = Map.maps[mapNumber].teleportMap[(int) (realYPos)/ blockSize][(int) (realXPos)/ blockSize];
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