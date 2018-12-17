package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

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
        Byte[] importantID = {7,8,11,15,16,23,24,25,26,27,28,29,33,34,35,42,43,44,45,46,53,54,55,56,57,58,59,60,61,62};

        public void save(Map map, File file,int currentMapNumber){
            try {
                pw = new PrintWriter(file);
                int furnCount = 0;
                for (int j = 0; j < map.furnitureMap.length; j++) {
                    for (int k = 0; k < map.furnitureMap[j].length ; k++) {
                        if(Arrays.asList(importantID).contains(map.furnitureMap[j][k])) furnCount ++;
                    }
                }
                writeInt(furnCount,file);
                pw.println();
                //Furniture.furn = new Furniture[furnCount];
                int i = 0;
                for (int j = 0; j < map.furnitureMap.length; j++) {
                    for (int k = 0; k < map.furnitureMap[j].length ; k++) {

                        if(Arrays.asList(importantID).contains(map.furnitureMap[j][k])){
                            writeInt(k,file); //координату x
                            writeInt(j,file); //координату y
                            writeInt(map.furnitureMap[j][k],file); // id
                            if( Furniture.furn != null && Furniture.furn.length != 0 &&  Furniture.furn[currentMapNumber] != null && Furniture.furn[currentMapNumber].length != 0 &&
                                    Furniture.furn[currentMapNumber][i].x == k & Furniture.furn[currentMapNumber][i].y == j ){
                                writeInt(Furniture.furn[currentMapNumber][i].hp, file); // HP
                                writeInt(Furniture.furn[currentMapNumber][i].exp,file);   //EXP
                                writeInt(Furniture.furn[currentMapNumber][i].dmg,file);    //DMG
                                writeInt(Furniture.furn[currentMapNumber][i].speed,file);    //Speed
                                writeInt(Furniture.furn[currentMapNumber][i].money,file);    //money
                                writeInt(Furniture.furn[currentMapNumber][i].maxHp,file);    //maxHP
                                writeString(Furniture.furn[currentMapNumber][i].text,file);
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

                                switch (map.furnitureMap[j][k]){
                                    case 7:  writeString(new String[]{"Чтоб взять вещи из горшка нажмите " + KeyManager.getButtAction(),"Горшок пуст","Вы нашли деньги","Вы нашли зелье здоровья HP+","Вы нашли новый мечь ATK+","Вы нашли новую броню MAXHP++","Вы нашли новые ботинки SPEED++"},file); break;
                                    case 8:  writeString(new String[]{"Разбитый горшок"},file); break;
                                    case 11:  writeString(new String[]{"Глубокий колодец"},file); break;
                                    case 15:  writeString(new String[]{"Чтоб взять вещи из сундука нажмите " + KeyManager.getButtAction(),"Сундук пуст","Вы нашли деньги","Вы нашли зелье здоровья HP+","Вы нашли новый мечь ATK+","Вы нашли новую броню MAXHP++","Вы нашли новые ботинки SPEED++"},file); break;
                                    case 16:  writeString(new String[]{"Чтоб взять вещи из сундука нажмите " + KeyManager.getButtAction(),"Сундук пуст","Вы нашли деньги","Вы нашли зелье здоровья HP+","Вы нашли новый мечь ATK+","Вы нашли новую броню MAXHP++","Вы нашли новые ботинки SPEED++"},file); break;
                                    case 23:  writeString(new String[]{"Качестевнно сделанная игрушка замка"},file); break;
                                    case 24:  writeString(new String[]{"Качестевнно сделанная игрушка дома"},file); break;
                                    case 25:  writeString(new String[]{"Чтоб поспать нажмите " + KeyManager.getButtAction(),"Вы поспали, здоровье востановлено"},file); break;
                                    case 26:  writeString(new String[]{"Удобно выглядещее кресло"},file); break;
                                    case 27:  writeString(new String[]{"Статуя птицы"},file); break;
                                    case 28:  writeString(new String[]{"Чтоб открыть нажмите " + KeyManager.getButtAction(),"Это дверь"},file); break;
                                    case 29:  writeString(new String[]{"Чтоб открыть нажмите " + KeyManager.getButtAction(),"Это дверь"},file); break;
                                    case 33:  writeString(new String[]{"Чтоб поспать нажмите " + KeyManager.getButtAction(),"Вы поспали, здоровье востановлено"},file); break;
                                    case 34:  writeString(new String[]{"Пустой стол"},file); break;
                                    case 35:  writeString(new String[]{"Чтоб взять вещи из ящика нажмите " + KeyManager.getButtAction(),"Ящик пуст","Вы нашли деньги","Вы нашли зелье здоровья HP+","Вы нашли новый мечь ATK+","Вы нашли новую броню MAXHP++","Вы нашли новые ботинки SPEED++"},file); break;
                                    case 42:  writeString(new String[]{"Куча неизвестного вещества"},file); break;
                                    case 43:  writeString(new String[]{"Куча неизвестного вещества"},file); break;
                                    case 44:  writeString(new String[]{"Надпись на табличке :", "PLS ADD TEXT"},file); break;
                                    case 45:  writeString(new String[]{"Куча золота"},file); break;
                                    case 46:  writeString(new String[]{"Куча кристалов"},file); break;
                                    case 53:  writeString(new String[]{"Чтоб закрыть нажмите " + KeyManager.getButtAction(),"Это дверь"},file); break;
                                    case 54:  writeString(new String[]{"Чтоб закрыть нажмите " + KeyManager.getButtAction(),"Это дверь"},file); break;
                                    case 55:  writeString(new String[]{"Чтоб поговорить нажмите " + KeyManager.getButtAction(), "PLS ADD TEXT"},file); break;
                                    case 56:  writeString(new String[]{"Чтоб поговорить нажмите " + KeyManager.getButtAction(), "PLS ADD TEXT"},file); break;
                                    case 57:  writeString(new String[]{"Чтоб поговорить нажмите " + KeyManager.getButtAction(), "PLS ADD TEXT"},file); break;
                                    case 58:  writeString(new String[]{"Чтоб поговорить нажмите " + KeyManager.getButtAction(), "PLS ADD TEXT"},file); break;
                                    case 59:  writeString(new String[]{"Чтоб поговорить нажмите " + KeyManager.getButtAction(), "PLS ADD TEXT"},file); break;
                                    case 60:  writeString(new String[]{"Чтоб поговорить нажмите " + KeyManager.getButtAction(), "PLS ADD TEXT"},file); break;
                                    case 61:  writeString(new String[]{"Чтоб поговорить нажмите " + KeyManager.getButtAction(), "PLS ADD TEXT"},file); break;
                                    case 62:  writeString(new String[]{"Чтоб поговорить нажмите " + KeyManager.getButtAction(), "PLS ADD TEXT"},file); break;

                                    default:  writeString(new String[]{},file);
                                }
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

