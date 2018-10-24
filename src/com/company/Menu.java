package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Menu {// todo remake all there
    private static final Image BUTTON_IMG_0 = new Image( "/resources/menu/button_0.png");
    private static final Image BUTTON_IMG_1 = new Image( "/resources/menu/button_1.png");
    private static final Image BUTTON_IMG_2 = new Image( "/resources/menu/button_2.png");

    private static final int BUT_0_POS_X = 0;
    private static final int BUT_0_POS_Y = 0;


    private static final int BUT_1_POS_X = 0;
    private static final int BUT_1_POS_Y = 30;


    private static final int BUT_2_POS_X = 0;
    private static final int BUT_2_POS_Y = 60;

    private static final String VERSION = "0.1";

    private static byte pos = 0;

    private static long prevTime;

    public static GraphicsContext gc = graphic.canvas.getGraphicsContext2D();


    public static void drawMenu(long currentNanoTime){

        if (currentNanoTime - prevTime >= 100000000){
            if(keyManager.activeKeyHash.contains("DOWN") && pos != 2){
                pos++;
                prevTime = currentNanoTime;
            } else if(keyManager.activeKeyHash.contains("DOWN") && pos == 2){
                pos = 0;
                prevTime = currentNanoTime;
            } else if(keyManager.activeKeyHash.contains("UP") && pos != 0){
                pos --;
                prevTime = currentNanoTime;
            }else if(keyManager.activeKeyHash.contains("UP") && pos == 0){
                pos = 2;
                prevTime = currentNanoTime;
            }
        }

        System.out.println(currentNanoTime - prevTime);
        if (pos == 0) {
            gc.drawImage(BUTTON_IMG_2, BUT_0_POS_X, BUT_0_POS_Y);
        } else {
            gc.drawImage(BUTTON_IMG_0, BUT_0_POS_X, BUT_0_POS_Y);
        }

        if (pos == 1) {
            gc.drawImage(BUTTON_IMG_2, BUT_1_POS_X, BUT_1_POS_Y);
        } else {
            gc.drawImage(BUTTON_IMG_0, BUT_1_POS_X, BUT_1_POS_Y);
        }

        if (pos == 2) {
            gc.drawImage(BUTTON_IMG_2, BUT_2_POS_X, BUT_2_POS_Y);
        } else {
            gc.drawImage(BUTTON_IMG_0, BUT_2_POS_X, BUT_2_POS_Y);
        }




    }
}
