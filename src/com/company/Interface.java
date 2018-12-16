package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Interface {
    private static GraphicsContext gc = graphic.interfaceLayer.getGraphicsContext2D();
    private static Image hpBarUp;
    private static Image barDown;
    private static Image expBarUp;

    private static final String FONT_NAME = "Arial";                    //задаем константы для шрифта для надписи версия
    private static final int FONT_SIZE = 25;                            //размер текста
    private static final FontWeight FONT_WEIGHT = FontWeight.BOLD;              //название шрифта

    private static boolean firstcall = true;

    public static void draw(long curentNanoTime, int curentMapNumber){
        gc.clearRect(0, 0, graphic.theScene.getWidth(), graphic.theScene.getHeight());
        if (firstcall){
            try {
                hpBarUp = new Image(new FileInputStream(new File("./resources/interface/hp.png")));
                expBarUp = new Image(new FileInputStream(new File("./resources/interface/exp.png")));
                barDown = new Image(new FileInputStream(new File("./resources/interface/bar.png")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            firstcall = false;
        }

        Player pl = Player.getPlayer();
        gc.drawImage(barDown,0,0);
        gc.drawImage(hpBarUp,68,12,hpBarUp.getWidth()*pl.getHp()/pl.getMaxHP(), hpBarUp.getHeight());
        //gc.drawImage(expBarUp,60,64,expBarUp.getWidth()*pl.getHp()/pl.getMaxHP(), expBarUp.getHeight());
        gc.drawImage(expBarUp,0,0,expBarUp.getWidth()*pl.getExp()/pl.getMaxExp(), expBarUp.getHeight(),60,64,expBarUp.getWidth()*pl.getExp()/pl.getMaxExp(),expBarUp.getHeight());
        gc.setFill(Color.web("#ffffff"));
        gc.setFont(Font.font(FONT_NAME, FONT_WEIGHT, 25));
        gc.fillText(pl.getHp() + "/" +  pl.getMaxHP(),70,37);
        gc.setFont(Font.font(FONT_NAME, FONT_WEIGHT, 17));
        gc.fillText( pl.getLvl() + "" ,16,78);
        gc.fillText(pl.getExp() + "/" +  pl.getMaxExp(),76,78);
        FurnManager.draw(curentNanoTime,curentMapNumber);
    }
}
