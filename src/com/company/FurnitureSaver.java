package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FurnitureSaver {
        private static FurnitureSaver instance = null;
        private FurnitureSaver(){}
        PrintWriter pw;

        public static FurnitureSaver getInstance(){
            if (instance == null){
                instance = new FurnitureSaver();
            }
            return instance;
        }

        public void save(Map map, File file){
            try {
                pw = new PrintWriter(file);
                int furnCount = 0;
                for (int j = 0; j < map.furnitureMap.length; j++) {
                    for (int k = 0; k < map.furnitureMap[j].length ; k++) {
                        if(map.furnitureMap[j][k] != 0) furnCount ++;
                    }
                }
                writeInt(furnCount,file);
                pw.println();
                //Furniture.furn = new Furniture[furnCount];
                int i = 0;
                for (int j = 0; j < map.furnitureMap.length; j++) {
                    for (int k = 0; k < map.furnitureMap[j].length ; k++) {

                        if(map.furnitureMap[j][k] != 0){
                            writeInt(k,file); //координату x
                            writeInt(j,file); //координату y
                            writeInt(map.furnitureMap[j][k],file); // id
                            if( Furniture.furn != null && Furniture.furn.length != 0 &&  Furniture.furn[graphic.currentMapNumber] != null &&
                                    Furniture.furn[graphic.currentMapNumber][i].x == k & Furniture.furn[graphic.currentMapNumber][i].y == j ){
                                writeInt(Furniture.furn[graphic.currentMapNumber][i].hp, file); // HP
                                writeInt(Furniture.furn[graphic.currentMapNumber][i].exp,file);   //EXP
                                writeInt(Furniture.furn[graphic.currentMapNumber][i].dmg,file);    //DMG
                                writeInt(Furniture.furn[graphic.currentMapNumber][i].speed,file);    //Speed
                                writeInt(Furniture.furn[graphic.currentMapNumber][i].money,file);    //money
                                writeInt(Furniture.furn[graphic.currentMapNumber][i].maxHp,file);    //maxHP
                                writeString(Furniture.furn[graphic.currentMapNumber][i].text,file);
                                i++;
                                pw.println();
                            }
                            else{
                                writeInt(0, file); // HP
                                writeInt(0,file);   //EXP
                                writeInt(0,file);    //DMG
                                writeInt(0,file);    //Speed
                                writeInt(0,file);    //money
                                writeInt(0,file);    //maxHP
                                writeString(new String[]{"s"},file);
                                pw.println();
                            }

                        }


                    }
                }







                pw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

    private void writeInt(int num,File file){
        pw.println(num + " ");
    }

    private void writeString(String[] text,File file){
        writeInt(text.length,file);
        for (int i = 0; i < text.length; i++) {
            pw.println(text[i] + " ");
        }
    }

}

