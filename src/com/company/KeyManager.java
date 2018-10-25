package com.company;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.HashSet;

public class KeyManager {

    public static HashSet<String> activeKeyHash;    //hashset со всеми нажатыми в данный момент кнопками


    public static void prepareActionHandlers() {

        activeKeyHash = new HashSet<String>();

        graphic.theScene.setOnKeyPressed(new EventHandler<KeyEvent>()   //добавляем кнопки если нажаты и не были в хэше до этого
        {
            @Override
            public void handle(KeyEvent event)
            {
                activeKeyHash.add(event.getCode().toString());
            }
        });

        graphic.theScene.setOnKeyReleased(new EventHandler<KeyEvent>()  //убираем кнопку если она отжата
        {
            @Override
            public void handle(KeyEvent event)
            {
                activeKeyHash.remove(event.getCode().toString());
            }
        });
    }

   /* ТОЛЬКО ДЛЯ ТЕСТИРОВАНИЯ
   public static void printKeyArr(){
        String[] activeKeyArr = activeKeyHash.toArray(new String[activeKeyHash.size()]);
        if(activeKeyArr.length != 0 ){
            for (String arr:activeKeyArr){
                System.out.print(arr +" ");
            }
            System.out.println();
        }

    }*/
}


