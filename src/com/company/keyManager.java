package com.company;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.HashSet;

public class keyManager {

    public static HashSet<String> activeKeyHash;    //hash with pressed buttons


    public static void prepareActionHandlers() {

        activeKeyHash = new HashSet<String>();  //hash with presst keys

        graphic.theScene.setOnKeyPressed(new EventHandler<KeyEvent>()   //add button to hash when pressed
        {
            @Override
            public void handle(KeyEvent event)
            {
                activeKeyHash.add(event.getCode().toString());
            }
        });

        graphic.theScene.setOnKeyReleased(new EventHandler<KeyEvent>()  //remove button from hash when released
        {
            @Override
            public void handle(KeyEvent event)
            {
                activeKeyHash.remove(event.getCode().toString());
            }
        });
    }

   /* For test only
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


