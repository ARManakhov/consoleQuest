package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class mapEditor {
    private static boolean firstCall = false;
    private static int pointerPosX = 1;
    private static int pointerPosY = 0;

    private static GraphicsContext gc = graphic.interfaceLayer.getGraphicsContext2D();


    public static void draw(long currentNanoTime,int currentMap)  {

        try {
            gc.drawImage(new Image(new FileInputStream( new File("./resources/editor/pointer.png"))),pointerPosX*32,pointerPosY*32);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
