package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MapReader {
    private static File mapDirectory = new File("./maps/");
    private static File[] mapFiles;
    private static MapReader instance;

    private  Scanner sc;

    private void readFolderFiles(){
        if(mapDirectory.exists() && mapDirectory.isDirectory() ){
            mapFiles = mapDirectory.listFiles();
        }
    }

    public static MapReader getInstance(){
        if(instance == null){
            instance = new MapReader();
            instance.readFolderFiles();

        }
        return instance;
    }

    private MapReader(){}

    public void readMapsFromFolder(){
        Map.maps = new Map[mapFiles.length];
        for (int i = 0; i < mapFiles.length; i++) {
            try {
                sc = new Scanner(mapFiles[i]);
                Map.maps[i] = new Map(readInt(), readInt(),readByteArray(), readByteArray(), readByteArray(),readByteArray(),readByteArray());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private byte[][] readByteArray(){
        int ln1 = sc.nextInt();
        byte[][] arr = new byte[ln1][];

        for (int i = 0; i < ln1; i++) {
            int ln2 = sc.nextInt();
            arr[i] = new byte[ln2];
            for (int j = 0; j <arr[i].length; j++) {
                arr[i][j] = sc.nextByte();
            }
        }
        return arr;
    }

    private int readInt(){
        return sc.nextInt();
    }
}
