package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MapEditor {
    private static boolean firstCall = true;
    private static int pointerPosX = 0;
    private static int pointerPosY = 0;

    private static int mapPosX = 0;
    private static int mapPosY = 0;

    private static long prevNanoTime0;
    private static long prevNanoTime1;
    private static long prevNanoTime2;
    private static long prevNanoTime3;
    private static long prevNanoTime4;
    private static long prevNanoTime5;
    private static long prevNanoTime6;


    private static final long POINTER_WAIT_TIME_0 = 100000000;
    private static final long POINTER_WAIT_TIME_1 = 100000000;
    private static final long POINTER_WAIT_TIME_2 = 100000000;
    private static final long POINTER_WAIT_TIME_3 = 100000000;
    private static final long POINTER_WAIT_TIME_4 = 100000000;
    private static final long POINTER_WAIT_TIME_5 = 100000000;
    private static final long POINTER_WAIT_TIME_6 = 100000000;



    private static GraphicsContext gc = graphic.playerLayer.getGraphicsContext2D();

    private static final String UP_BUT = "W";
    private static final String DOWN_BUT = "S";
    private static final String RIGHT_BUT = "D";
    private static final String LEFT_BUT = "A";

    private static final String NEXT_GROUND_TEXTURE_BUT = "E";
    private static final String PREV_GROUND_TEXTURE_BUT = "Q";
    private static final String CLEAN_GROUND_TEXTURE_BUT = "F";

    private static final String NEXT_FURNITURE_TEXTURE_BUT = "R";
    private static final String PREV_FURNITURE_TEXTURE_BUT = "T";
    private static final String CLEAN_FURNITURE_TEXTURE_BUT = "G";

    private static final String BORDER_STATE_CHANGE_BUT = "B";
    private static final String SPAWN_STATE_CHANGE_BUT = "N";


    private static final String HOT_BUT = "CONTROL";
    private static final String SAVE_BUT = "S";

    private static final String NEXT_MAP_BUT = "X";
    private static final String PREV_MAP_BUT = "Z";









    public static void draw(long currentNanoTime,int currentMap)  {
        if(firstCall){
            prevNanoTime0 = currentNanoTime;
            prevNanoTime1=currentNanoTime;
            firstCall = false;
        }

        pointerMove( currentNanoTime, currentMap);

        byte pointerNum = 0;

        gc.clearRect(0,0,graphic.playerLayer.getWidth(),graphic.playerLayer.getHeight());

        try {
            if(Map.maps[currentMap].borderMap[pointerPosY + mapPosY][pointerPosX + mapPosX] == 1) {
                pointerNum = 1;
            }
            if(Map.maps[currentMap].enemyMap[pointerPosY + mapPosY][pointerPosX + mapPosX] == 1) {
                pointerNum = 2;
            }
            if(Map.maps[currentMap].borderMap[pointerPosY + mapPosY][pointerPosX + mapPosX] == 1 && Map.maps[currentMap].enemyMap[pointerPosY][pointerPosX + mapPosX] == 1) {
                pointerNum = 3;
            }
        } catch (ArrayIndexOutOfBoundsException e){

        }

        try {
            if(pointerNum == 0){
                gc.drawImage(new Image(new FileInputStream( new File("./resources/editor/p0.png"))),pointerPosX*32,pointerPosY*32);
            }
            if(pointerNum == 1){
                gc.drawImage(new Image(new FileInputStream( new File("./resources/editor/p1.png"))),pointerPosX*32,pointerPosY*32);
            }
            if(pointerNum == 2){
                gc.drawImage(new Image(new FileInputStream( new File("./resources/editor/p2.png"))),pointerPosX*32,pointerPosY*32);
            }
            if(pointerNum == 3){
                gc.drawImage(new Image(new FileInputStream( new File("./resources/editor/p3.png"))),pointerPosX*32,pointerPosY*32);
            }
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }



    }

    private static  void pointerMove(long currentNanoTime,int currentMap){

        boolean resetTime0 = false;
        if((currentNanoTime > POINTER_WAIT_TIME_0 + prevNanoTime0) && KeyManager.getActiveKeyHash().contains(UP_BUT) && ((pointerPosY +1)*32>0)){
            pointerPosY--;
            resetTime0 = true;
        }
        if((currentNanoTime > POINTER_WAIT_TIME_0 + prevNanoTime0) && KeyManager.getActiveKeyHash().contains(UP_BUT) && !((pointerPosY +1)*32>0)){
            MapManager.moveMap(-32,"V");
            mapPosY--;
            resetTime0 = true;
        }


        if((currentNanoTime > POINTER_WAIT_TIME_0 + prevNanoTime0) &&  KeyManager.getActiveKeyHash().contains(DOWN_BUT) && !KeyManager.getActiveKeyHash().contains(HOT_BUT) && ((pointerPosY + 1)*32 < graphic.theScene.getHeight())){
            pointerPosY++;
            resetTime0 = true;
        }

        if((currentNanoTime > POINTER_WAIT_TIME_0 + prevNanoTime0) &&  KeyManager.getActiveKeyHash().contains(DOWN_BUT) && !KeyManager.getActiveKeyHash().contains(HOT_BUT) && !((pointerPosY + 1)*32 < graphic.theScene.getHeight())){
            MapManager.moveMap(+32,"V");
            mapPosY++;
            resetTime0 = true;
        }


        if((currentNanoTime > POINTER_WAIT_TIME_0 + prevNanoTime0) &&  KeyManager.getActiveKeyHash().contains(LEFT_BUT) && ((pointerPosX+1)*32 > 0)){     //передвижение курсора влево: сам курсор
            pointerPosX--;
            resetTime0 = true;
        }

        if((currentNanoTime > POINTER_WAIT_TIME_0 + prevNanoTime0) &&  KeyManager.getActiveKeyHash().contains(LEFT_BUT) && ((pointerPosX)*32 <= 0 )){     //передвижение курсра влево: мапа
            MapManager.moveMap(32,"H");
            mapPosX--;
            resetTime0 = true;
        }

        if((currentNanoTime > POINTER_WAIT_TIME_0 + prevNanoTime0) &&  KeyManager.getActiveKeyHash().contains(RIGHT_BUT) && ((pointerPosX + 1)*32 < graphic.theScene.getWidth())){      //передвижение курсра вправо: сам куроср
            pointerPosX++;
            resetTime0 = true;
        }

        if((currentNanoTime > POINTER_WAIT_TIME_0 + prevNanoTime0) &&  KeyManager.getActiveKeyHash().contains(RIGHT_BUT) && !((pointerPosX + 1)*32 < graphic.theScene.getWidth())){     //передвижение курсра вправо: мапа
            MapManager.moveMap(-32,"H");
            mapPosX++;
            resetTime0 = true;
        }

        if(resetTime0){
            prevNanoTime0 = currentNanoTime;
        }

           if (KeyManager.getActiveKeyHash().contains(PREV_GROUND_TEXTURE_BUT ) || KeyManager.getActiveKeyHash().contains(NEXT_GROUND_TEXTURE_BUT) ||
                   KeyManager.getActiveKeyHash().contains(NEXT_FURNITURE_TEXTURE_BUT) || KeyManager.getActiveKeyHash().contains(PREV_FURNITURE_TEXTURE_BUT ) ||
                   KeyManager.getActiveKeyHash().contains(CLEAN_FURNITURE_TEXTURE_BUT) || KeyManager.getActiveKeyHash().contains(CLEAN_GROUND_TEXTURE_BUT) ||
                   KeyManager.getActiveKeyHash().contains(BORDER_STATE_CHANGE_BUT) || KeyManager.getActiveKeyHash().contains(SPAWN_STATE_CHANGE_BUT)) {

               Map.maps[currentMap].groundMap = byteArrExtender(pointerPosX + mapPosX,pointerPosY  + mapPosY,Map.maps[currentMap].groundMap);
               Map.maps[currentMap].furnitureMap = byteArrExtender(pointerPosX + mapPosX,pointerPosY  + mapPosY,Map.maps[currentMap].furnitureMap);
               Map.maps[currentMap].enemyMap = byteArrExtender(pointerPosX + mapPosX,pointerPosY + mapPosY,Map.maps[currentMap].enemyMap);
               Map.maps[currentMap].borderMap = byteArrExtender(pointerPosX + mapPosX,pointerPosY + mapPosY,Map.maps[currentMap].borderMap);
               Map.maps[currentMap].teleportMap = byteArrExtender(pointerPosX + mapPosX,pointerPosY + mapPosY,Map.maps[currentMap].teleportMap);
           }            //todo расширение массива


        boolean resetTime5 = false;
        if ((currentNanoTime > POINTER_WAIT_TIME_5 + prevNanoTime5) && KeyManager.getActiveKeyHash().contains(BORDER_STATE_CHANGE_BUT)){
            if(Map.maps[currentMap].borderMap[pointerPosY + mapPosY][pointerPosX + mapPosX] == 0){
                Map.maps[currentMap].borderMap[pointerPosY + mapPosY][pointerPosX + mapPosX] = 1;
            } else{
                Map.maps[currentMap].borderMap[pointerPosY + mapPosY][pointerPosX + mapPosX] = 0;
            }
            resetTime5 = true;
        }
        if(resetTime5){
            prevNanoTime5 = currentNanoTime;
        }

        boolean resetTime6 = false;
        if ((currentNanoTime > POINTER_WAIT_TIME_6 + prevNanoTime6) && KeyManager.getActiveKeyHash().contains(SPAWN_STATE_CHANGE_BUT)){
            if(Map.maps[currentMap].enemyMap[pointerPosY + mapPosY][pointerPosX + mapPosX] == 0){
                Map.maps[currentMap].enemyMap[pointerPosY + mapPosY][pointerPosX + mapPosX] = 1;
            } else{
                Map.maps[currentMap].enemyMap[pointerPosY + mapPosY][pointerPosX + mapPosX] = 0;
            }
            resetTime6 = true;
        }
        if(resetTime6){
            prevNanoTime6 = currentNanoTime;
        }

        boolean resetTime1 = false;
        if ((currentNanoTime > POINTER_WAIT_TIME_1 + prevNanoTime1) &&  KeyManager.getActiveKeyHash().contains(PREV_GROUND_TEXTURE_BUT) && !KeyManager.getActiveKeyHash().contains(HOT_BUT)){
            if(Map.maps[currentMap].groundMap[pointerPosY + mapPosY][pointerPosX + mapPosX] != 0 ){
                Map.maps[currentMap].groundMap[pointerPosY + mapPosY][pointerPosX + mapPosX]--;
            }else{
                Map.maps[currentMap].groundMap[pointerPosY + mapPosY][pointerPosX + mapPosX] = (byte) (Block.getInstance().getBlockArrSize() - 1);
            }
            resetTime1 = true;
        }

        if ((currentNanoTime > POINTER_WAIT_TIME_1 + prevNanoTime1) &&  KeyManager.getActiveKeyHash().contains(NEXT_GROUND_TEXTURE_BUT) && !KeyManager.getActiveKeyHash().contains(HOT_BUT)){
            if(Map.maps[currentMap].groundMap[pointerPosY + mapPosY][pointerPosX + mapPosX] != (Block.getInstance().getBlockArrSize()-1) ){
                Map.maps[currentMap].groundMap[pointerPosY + mapPosY][pointerPosX + mapPosX]++;
            }else{
                Map.maps[currentMap].groundMap[pointerPosY + mapPosY][pointerPosX + mapPosX] = 0;
            }
            resetTime1 = true;
        }

        if ((currentNanoTime > POINTER_WAIT_TIME_1 + prevNanoTime1) &&  KeyManager.getActiveKeyHash().contains(PREV_GROUND_TEXTURE_BUT) && KeyManager.getActiveKeyHash().contains(HOT_BUT)){

            resetTime1 = true;
        }

        if ((currentNanoTime > POINTER_WAIT_TIME_1 + prevNanoTime1) &&  KeyManager.getActiveKeyHash().contains(NEXT_GROUND_TEXTURE_BUT) && KeyManager.getActiveKeyHash().contains(HOT_BUT)){

            resetTime1 = true;
        }

        if(resetTime1){
            prevNanoTime1 = currentNanoTime;
            MapManager.setNeedRedraw();
        }


        boolean resetTime4 = false;
        if ((currentNanoTime > POINTER_WAIT_TIME_4 + prevNanoTime4) &&  KeyManager.getActiveKeyHash().contains(NEXT_FURNITURE_TEXTURE_BUT)){
            if(Map.maps[currentMap].furnitureMap[pointerPosY + mapPosY][pointerPosX + mapPosX] != 0 ){
                Map.maps[currentMap].furnitureMap[pointerPosY + mapPosY][pointerPosX + mapPosX]--;
            }else{
                Map.maps[currentMap].furnitureMap[pointerPosY + mapPosY][pointerPosX + mapPosX] = (byte) (Block.getInstance().getBlockArrSize() - 1);
            }
            resetTime4 = true;

        }

        if ((currentNanoTime > POINTER_WAIT_TIME_4 + prevNanoTime4) &&  KeyManager.getActiveKeyHash().contains(PREV_FURNITURE_TEXTURE_BUT)){
            if(Map.maps[currentMap].furnitureMap[pointerPosY + mapPosY][pointerPosX + mapPosX] != (Block.getInstance().getBlockArrSize()-1) ){
                Map.maps[currentMap].furnitureMap[pointerPosY + mapPosY][pointerPosX + mapPosX]++;
            }else{
                Map.maps[currentMap].furnitureMap[pointerPosY + mapPosY][pointerPosX] = 0;
            }
            resetTime4 = true;

        }
        if ((currentNanoTime > POINTER_WAIT_TIME_4 + prevNanoTime4) &&  KeyManager.getActiveKeyHash().contains(CLEAN_FURNITURE_TEXTURE_BUT)){
            Map.maps[currentMap].furnitureMap[pointerPosY + mapPosY][pointerPosX + mapPosX] = 0;
            resetTime4 = true;

        }

        if(resetTime4){
            prevNanoTime4 = currentNanoTime;
            MapManager.setNeedRedraw();
        }


        if((currentNanoTime > POINTER_WAIT_TIME_2 + prevNanoTime2) && KeyManager.getActiveKeyHash().contains(SAVE_BUT) && KeyManager.getActiveKeyHash().contains(HOT_BUT)){
            MapSaver.getMapSaver().saveMap(Map.maps[currentMap], new File("./maps/map" + currentMap));
            System.out.println("Saved");
/*            byteLengthToConsole("groundMap",Map.maps[currentMap].groundMap);
            byteLengthToConsole("furnitureMap",Map.maps[currentMap].furnitureMap);
            byteLengthToConsole("enemyMap",Map.maps[currentMap].enemyMap);
            byteLengthToConsole("borderMap",Map.maps[currentMap].borderMap);
            byteLengthToConsole("teleportMap",Map.maps[currentMap].teleportMap);
*/
            prevNanoTime2 = currentNanoTime;
        }

        boolean resetTime3 = false;
        if ((currentNanoTime > POINTER_WAIT_TIME_3 + prevNanoTime3) &&  KeyManager.getActiveKeyHash().contains(NEXT_MAP_BUT)){//todo смена карты
            if(Map.maps.length!= currentMap){
                graphic.currentMapNumber++;
            } else{
                graphic.currentMapNumber=0;
            }
            resetTime3 = true;
        }
        if ((currentNanoTime > POINTER_WAIT_TIME_3 + prevNanoTime3) &&  KeyManager.getActiveKeyHash().contains(PREV_MAP_BUT)){
            if(Map.maps.length!= 0){
                graphic.currentMapNumber--;
            } else{
                graphic.currentMapNumber=Map.maps.length-1;
            }
            resetTime3 = true;
        }
        if (resetTime3) {
            prevNanoTime3 = currentNanoTime;
            MapManager.setNeedRedraw();
        }


    }

    private static byte[][] byteArrExtender(int pointerPosX, int pointerPosY,byte[][] arr){
       // byteLengthToConsole("WAS MAP ",arr);
        if (pointerPosY >= arr.length) {

            byte[][] newMap = new byte[pointerPosY + 1][];


            for (int i = 0; i < arr.length; i++) {
                newMap[i] = new byte[arr[i].length];
                for (int j = 0; j < arr[i].length; j++) {
                    newMap[i][j] = arr[i][j];
                }
            }
            for (int i = arr.length; i < newMap.length; i++) {
                newMap[i] = new byte[pointerPosX];
            }
            arr = newMap;

        }
        if (pointerPosX >= arr[pointerPosY].length) {
            byte[] newMap = new byte[pointerPosX + 1];

            for (int j = 0; j < arr[pointerPosY].length; j++) {
                newMap[j] = arr[pointerPosY][j];
            }
            arr[pointerPosY] = newMap;
        }
        //byteLengthToConsole("NOW MAP ",arr);
        return arr;
    }

    private static void byteLengthToConsole(String text,byte[][] map){
        System.out.println(text + " ln : " +  map.length + ", " + map[0].length);
    }
}


