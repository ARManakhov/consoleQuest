package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class PlayerManager {
    // размер спрайта
    private static final double PLAYER_SIZE_X = 16;        // размер игрока по x
    private static final double PLAYER_SIZE_Y = 16;        // размер игрока по y

    //переменные необходимые для отрисовки персонажа
    private static double screenXPos;
    private static double screenYPos;
    private static double limX1;
    private static double limY1;
    private static double limX2;
    private static double limY2;
    private static double realXPos;
    private static double realYPos;

    private static GraphicsContext gc = graphic.playerLayer.getGraphicsContext2D();
    private static Image playerTexture = new Image("resources/characters/player/spruce_sapling.png");

    private static boolean firstCall = true;

    /**
     * метод для отрисовки игрока, и его движения
     * @param currentNanoTime текущее время (для анимации)
     * @param mapNumber номер карты для отрисовки
     */
    public static void drawPlayer(long currentNanoTime, int mapNumber) {

        limFinder();
        if(firstCall){
            realXPos =  32 * Maps.worldMap[mapNumber].spawnPosX;
            realYPos =  32 * Maps.worldMap[mapNumber].spawnPosY;

            screenXPos =  graphic.theScene.getWidth()/2;
            screenYPos =  graphic.theScene.getHeight()/2;

            firstCall = false;
        }

        //todo наискасок скорость через корень


        if(Maps.worldMap[mapNumber].teleportMap[(int) (realYPos)/32][(int) (realXPos)/32] != 0){
            graphic.currentMapNumber = Maps.worldMap[mapNumber].teleportMap[(int) (realYPos)/32][(int) (realXPos)/32];

            firstCall = true;
            MapManager.setAsFirstCall();

        }

        if(KeyManager.pressedButt("LEFT")){

            if ((Maps.worldMap[mapNumber].borderMap[(int) (realYPos)/32][(int) (realXPos - Player.getSpeed())/32] != 1)
                    && (Maps.worldMap[mapNumber].borderMap[(int) (realYPos + PLAYER_SIZE_Y)/32][(int) (realXPos - Player.getSpeed())/32] != 1)){

                if (screenXPos > limX1) {
                    screenXPos-=Player.getSpeed();
                } else {
                    MapManager.moveMap(Player.getSpeed(),"H");

                }

                realXPos-=Player.getSpeed();

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
            if((Maps.worldMap[mapNumber].borderMap[(int) (realYPos)/32][(int) (realXPos + Player.getSpeed() + PLAYER_SIZE_X)/32] != 1 )
                    && (Maps.worldMap[mapNumber].borderMap[(int) (realYPos + PLAYER_SIZE_Y)/32][(int) (realXPos + Player.getSpeed() + PLAYER_SIZE_X)/32] != 1 )  ){
                if (screenXPos < limX2 ) {
                    screenXPos+=Player.getSpeed();
                } else {
                    MapManager.moveMap(-Player.getSpeed(),"H");
                }
                realXPos+=Player.getSpeed();

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
            double move = Player.getSpeed();

            if((Maps.worldMap[mapNumber].borderMap[(int) (realYPos - Player.getSpeed())/32][(int) realXPos/32] != 1 )
                    && (Maps.worldMap[mapNumber].borderMap[(int) (realYPos - Player.getSpeed())/32][(int) (realXPos+PLAYER_SIZE_X)/32] != 1 )) {
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
            if((Maps.worldMap[mapNumber].borderMap[(int) (realYPos + Player.getSpeed() + PLAYER_SIZE_Y)/32][(int) realXPos/32] != 1 )
                    && (Maps.worldMap[mapNumber].borderMap[(int) (realYPos + Player.getSpeed() + PLAYER_SIZE_Y)/32][(int) (realXPos+PLAYER_SIZE_X)/32] != 1 )){
                if (screenYPos < limY2) {
                    screenYPos+=Player.getSpeed();
                } else {
                    MapManager.moveMap(Player.getSpeed(),"V");
                }
                realYPos+=Player.getSpeed();

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

        if (KeyManager.pressedButt("ESCAPE")) {
            graphic.mode = 10;
        }

        gc.clearRect(0, 0, graphic.theScene.getWidth(), graphic.theScene.getHeight());

        gc.drawImage(playerTexture, screenXPos, screenYPos);


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
