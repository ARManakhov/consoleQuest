package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FurnitureReader {

    //x, y , Exp, Hp, MaxHp, Damage, Money, Text[]
    void read(){

    }

    private static File furnitureDirectory = new File("./furniture/");
    private static File[] furnitureFiles;
    private static FurnitureReader instance;

    private Scanner sc;

    private void readFolderFiles(){
        if(furnitureDirectory.exists() && furnitureDirectory.isDirectory() ){
            furnitureFiles = furnitureDirectory.listFiles();
        }
    }

    public static FurnitureReader getInstance(){
        if(instance == null){
            instance = new FurnitureReader();
            instance.readFolderFiles();

        }
        return instance;
    }

    private FurnitureReader(){}

    public void readFurnitureFromFolder(){
        Furniture.furn = new Furniture[furnitureFiles.length][];
        for (int i = 0; i < furnitureFiles.length; i++) {
            try {
                sc = new Scanner(furnitureFiles[i]);
                int furnCount = sc.nextInt();
                Furniture.furn[i] = new Furniture[furnCount];
                for (int j = 0; j < furnCount; j++) {
                    Furniture.furn[i][j] = new Furniture(readInt()
                            ,readInt()
                            ,readInt()
                            ,readInt()
                            ,readInt()
                            ,readInt()
                            ,readInt(),readInt(),readInt(),readString());
                }
            } catch (FileNotFoundException fe) {
                fe.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException ae){
                ae.printStackTrace();
            }
        }
    }

    private int readInt(){
        while(!sc.hasNextInt()){
       }
        return sc.nextInt();
    }

    private String[] readString(){
        String[] text = new String[readInt()];
        sc.nextLine();
        for (int i = 0; i < text.length; i++) {
            text[i] = sc.nextLine();
        }
        return text;
    }
}

/*
* for (int j = 0; j < Map.maps[i].furnitureMap.length; j++) {
                    for (int k = 0; k < Map.maps[i].furnitureMap[j].length ; k++) {
                        if(Map.maps[i].furnitureMap[j][k] != 0) furnCount ++;
                    }
                }
* */
