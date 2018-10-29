package com.company;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;


public class graphic extends Application {

    private static final int X_SIZE = 1024;     //стандартная ширина окна
    private static final int Y_SIZE = 720;      //стандартная высота окна

    public static Canvas canvas = new Canvas(); //основной холст //todo проработать отрисовку в javafx получше
    public static Group root = new Group();     //группа root

    public static Scene theScene = new Scene( root, X_SIZE, Y_SIZE );   //новая сцена стандартного размера

    public static int currentMapNumber = 0;

    /**
     * переменная которая выбирает "режим работы" программы
     * если равна 0 то в главном меню
     * ксли равна 1 то в первом мире
     *
     * по мере добавления обновлять !
     */
    public static byte mode = 0;

    /**
     * игровая петля
     * @param stage основная сцена
     * @throws Exception добавил при добавление ввода вывода, но может еще пригодится
     */
    @Override
    public void start(Stage stage) throws Exception{

        stage.setTitle( "Enima" );
        stage.setScene( theScene );
        root.getChildren().add( canvas );

        KeyManager.prepareActionHandlers(); //проверяем ввод с устройств




        /**
         * таймер анимации
         */
        new AnimationTimer() {
            public void handle(long currentNanoTime) {

                canvasSizeUpdate();

                if (mode == 0) {

                    MainMenu.drawMenu(currentNanoTime);
                }
                if (mode == 1){
                    currentMapNumber = 0;
                    MapManager.drawMap(currentNanoTime);
                    PlayerManager.drawPlayer(currentNanoTime,currentMapNumber);
                }

            }
        }.start();

        stage.show();
    }

    /**
     * обновление размера холста в соответсвие с размером окна
     */
    private static void canvasSizeUpdate (){
        canvas.setHeight(theScene.getHeight());
        canvas.setWidth(theScene.getWidth());
    }
}
