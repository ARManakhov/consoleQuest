package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MapSaver {
    private static MapSaver instance = null;
    private MapSaver(){}
    PrintWriter pw;

    public static MapSaver getMapSaver(){
        if (instance == null){
            instance = new MapSaver();
        }
        return instance;
    }

    public void save(Map map, File file){
        try {
            pw = new PrintWriter(file);
            writeInt(map.spawnPosX, file);
            writeInt(map.spawnPosY, file);

            writeByteArray(map.groundMap,file);
            writeByteArray(map.borderMap,file);
            writeByteArray(map.enemyMap,file);
            writeByteArray(map.teleportMap,file);
            writeByteArray(map.furnitureMap,file);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

/*    ublic byte[][] groundMap = {{}};

    public byte[][] borderMap = {{}};

    public byte[][] enemyMap = {{}};

    public byte[][] teleportMap = {{}};
*/

    private void writeByteArray(byte[][] arr,File file){
            pw.println(arr.length);

            for (int i = 0; i < arr.length; i++) {
            pw.println(arr[i].length + " ");
                for (int j = 0; j <arr[i].length; j++) {
                    pw.print(arr[i][j] + " ");
                }
                pw.println(" ");
            }
            pw.println(" ");
    }

    private void writeInt(int num,File file){
            pw.println(num +" ");
    }

}
