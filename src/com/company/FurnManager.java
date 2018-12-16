package com.company;

import javafx.scene.canvas.GraphicsContext;

public class FurnManager {

    private static GraphicsContext gc = graphic.interfaceLayer.getGraphicsContext2D();
    private static long prevNanoTime0;
    private static long prevNanoTime1;
    private static final long WAIT_TIME_0 = 100000000;
    private static final long WAIT_TIME_1 = 1000000000;

    private static String[] textToScreenSecondary = null;

    public  static void draw (long curentNanoTime, int curentMap){
        int PlayerX = (int)( Player.getPlayer().getRealXPos() + MapManager.getBlockSize() / 2) / 32;
        int PlayerY = (int)( Player.getPlayer().getRealYPos() + MapManager.getBlockSize() / 2) / 32;
        int dir = (int) PlayerManager.getInstance().dir;
        Furniture furn;
        gc.clearRect(0, 0, graphic.theScene.getWidth(), graphic.theScene.getHeight());


        if(dir == 0){ //вправо
            PlayerX ++;
        }
        if(dir == 1){ //вниз
            PlayerY ++;
        }
        if(dir == 2){ //влево
            PlayerX --;
        }
        if(dir == 3){ //вверх
            PlayerY --;
        }
        String[] textToScreenPrimary = null;

        if(Map.maps[curentMap].furnitureMap[PlayerY][PlayerX] != 0){
            furn = Furniture.getFurnitureOnPos(PlayerX,PlayerY,curentMap);
            if(furn != null){
                String[] text = furn.text;
                if(text.length > 0 ){
                    /*System.out.println(text[0] + "," + text[0].length());
                    System.out.println("OK");*/

                    textToScreenPrimary = new String[1];
                    textToScreenPrimary[0] = text[0];
                    if(prevNanoTime0 + WAIT_TIME_0 < curentNanoTime && text.length > 1 && KeyManager.pressedButt("ACTION")){
                        if (furn.id == 7 || furn.id == 15 || furn.id == 16 || furn.id == 35) {
                            Player pl = Player.getPlayer();
                            int strArrLn = 0;
                            pl.removeAddble();
                            boolean writeMaxHp = false;
                            if(0 < furn.maxHp){
                                pl.setAddMaxHp(pl.getAddMaxHp() + furn.maxHp);
                                writeMaxHp = true;
                                strArrLn++;
                                furn.maxHp = 0;
                            }

                            boolean writeHp = false;
                            if(0 < furn.hp){
                                pl.setHp(pl.getHp() + furn.hp < pl.getMaxHP() ? pl.getHp() + furn.hp : pl.getMaxHP());
                                writeHp = true;
                                strArrLn++;
                                furn.hp = 0;
                            }

                            boolean writeDmg = false;
                            if(pl.getAddDmg() < furn.dmg){
                                pl.setAddDmg(furn.dmg);
                                writeDmg = true;
                                strArrLn++;
                                furn.dmg = 0;
                            }

                            boolean writeSpeed = false;
                            if(pl.getAddSpeed() < furn.speed){
                                pl.setAddSpeed(furn.speed);
                                writeSpeed = true;
                                strArrLn++;
                                furn.speed = 0;
                            }

                            boolean writeMoney = false;
                            if(furn.money > 0){
                                pl.setMoney(pl.getMoney()+furn.money);
                                writeMoney = true;
                                strArrLn++;
                                furn.money = 0;
                            }
                            pl.addAddble();
                            if(strArrLn == 0){
                                textToScreenSecondary = new String[1];
                                textToScreenSecondary[0] = text[1];
                            } else {
                                textToScreenPrimary = new String[strArrLn];
                                int i = 0;
                                if (writeMaxHp){
                                    textToScreenPrimary[i] = text[5];
                                    i++;
                                }
                                if (writeDmg){
                                    textToScreenPrimary[i] = text[4];
                                    i++;
                                }
                                if (writeSpeed){
                                    textToScreenPrimary[i] = text[6];
                                    i++;
                                }
                                if (writeMoney){
                                    textToScreenPrimary[i] = text[2];
                                    i++;
                                }
                                if (writeHp){
                                    textToScreenPrimary[i] = text[3];
                                    i++;
                                }
                            }
                                    /*
                                    0,"Чтоб взять вещи из горшка нажмите " + KeyManager.getButtAction()
                                    1,"Горшок пуст"
                                    2,"Вы нашли деньги"
                                    3,"Вы нашли зелье здоровья HP+"
                                    4,"Вы нашли новый мечь ATK+"
                                    5,"Вы нашли новую броню MAXHP++"
                                    6,"Вы нашли новые ботинки SPEED++"
                                    */


                            //textToScreenSecondary = new String[furn.text.length-1];
                            //for (int i = 0; i < text.length-1; i++) {
                              //  textToScreenSecondary[i] = text[i+1];
                            //}
                            //Player.getPlayer().setHp(Player.getPlayer().getMaxHP());
                            /*
                            case 7:  writeString(new String[]{"Чтоб взять вещи из горшка нажмите " + KeyManager.getButtAction(),"PLS ADD TEXT"},file); break; //горшок
                            case 15:  writeString(new String[]{"Чтоб взять вещи из сундука нажмите " + KeyManager.getButtAction(),"PLS ADD TEXT"},file); break; //сундук
                            case 16:  writeString(new String[]{"Чтоб взять вещи из сундука нажмите " + KeyManager.getButtAction(),"PLS ADD TEXT"},file); break; //еще сундук
                            case 35:  writeString(new String[]{"Чтоб взять вещи из ящика " + KeyManager.getButtAction(),"PLS ADD TEXT"},file); break; //ящик
                            case 25:  writeString(new String[]{"Чтоб поспать нажмите " + KeyManager.getButtAction(),"PLS ADD TEXT"},file); break; //кровать
                            case 33:  writeString(new String[]{"Чтоб поспать нажмите " + KeyManager.getButtAction(),"PLS ADD TEXT"},file); break; // кровать
                            case 28:  writeString(new String[]{"Чтоб открыть нажмите " + KeyManager.getButtAction(),"PLS ADD TEXT"},file); break; // дверь
                            case 29:  writeString(new String[]{"Чтоб открыть нажмите " + KeyManager.getButtAction(),"PLS ADD TEXT"},file); break; // дверь
                            */
                            prevNanoTime0 = curentNanoTime;
                            prevNanoTime1 = curentNanoTime;

                        }

                        if (furn.id == 25 || furn.id == 33) {
                            textToScreenSecondary = new String[furn.text.length - 1];
                            for (int i = 0; i < text.length - 1; i++) {
                                textToScreenSecondary[i] = text[i + 1];
                            }
                            Player.getPlayer().setHp(Player.getPlayer().getMaxHP());
                            prevNanoTime0 = curentNanoTime;
                            prevNanoTime1 = curentNanoTime;
                        }

                        if(furn.id == 28 || furn.id == 29 ){

                            furn.id += 25;
                            Map.maps[curentMap].furnitureMap[PlayerY][PlayerX] = (byte) furn.id;
                            Map.maps[curentMap].borderMap[PlayerY][PlayerX] = 0;
                            MapManager.setNeedRedraw();
                            prevNanoTime0 = curentNanoTime;
                            prevNanoTime1 = curentNanoTime;
                            textToScreenSecondary = null;
                        }else
                        if(furn.id == 53 || furn.id == 54){
                            furn.id -= 25;
                            Map.maps[curentMap].furnitureMap[PlayerY][PlayerX] = (byte) furn.id;
                            Map.maps[curentMap].borderMap[PlayerY][PlayerX] = 1;
                            MapManager.setNeedRedraw();
                            prevNanoTime0 = curentNanoTime;
                            prevNanoTime1 = curentNanoTime;
                            textToScreenSecondary = null;
                        }
                    }
                }
            }
        }
        if(curentNanoTime - prevNanoTime1 < WAIT_TIME_1 && textToScreenSecondary != null ){
            TextOnScreen.drawText(textToScreenSecondary,gc);
        }else{
            if(textToScreenPrimary != null)
                TextOnScreen.drawText(textToScreenPrimary,gc);
        }
    }
}
