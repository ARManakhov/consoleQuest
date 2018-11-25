package com.company;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;


public class graphic extends Application {

    private static final int X_SIZE = 1024;     //стандартная ширина окна
    private static final int Y_SIZE = 720;      //стандартная высота окна

    public static Group root = new Group();     //группа root
    public static Canvas mapLayer = new Canvas();           //слой карты //todo проработать отрисовку в javafx получше
    public static Canvas playerLayer = new Canvas();        //слой с игроком и возможно с мобами
    public static Canvas interfaceLayer = new Canvas();     //холст интерфейса
    private static Pane mainPane = new Pane();

    private static double prevStageHeight = 0;
    private static double prevStageWidth = 0;

    public static Scene theScene = new Scene(root, X_SIZE, Y_SIZE);   //новая сцена стандартного размера

    public static int currentMapNumber = 0;

    /**
     * переменная которая выбирает "режим работы" программы
     * если равна 0 то в главном меню
     * ксли равна 1 то в первом мире
     * <p>
     * по мере добавления обновлять !
     */
    public static byte mode = 0;

    private static boolean first = true;

    /**
     * игровая петля
     *
     * @param stage основная сцена
     * @throws Exception добавил при добавление ввода вывода, но может еще пригодится
     */
    @Override
    public void start(Stage stage) throws Exception {
        if (first) {

            prevStageHeight = theScene.getHeight();
            prevStageWidth = theScene.getWidth();
            canvasSizeUpdate();
            first = false;
        }
        stage.setTitle("Enima");
        stage.setScene(theScene);
        mainPane.getChildren().add(interfaceLayer);     //добавление слоев в Pane
        mainPane.getChildren().add(playerLayer);
        mainPane.getChildren().add(mapLayer);
        root.getChildren().add(mainPane);               //добавление Pane в Root
        playerLayer.toFront();
        interfaceLayer.toFront();

        KeyManager.prepareActionHandlers(); //проверяем ввод с устройств


        /**
         * таймер анимации
         */
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                MenuEscManager mem = new MenuEscManager();
                SettingManager sm = new SettingManager();
                MainMenu mm = new MainMenu();

                // кнопки обновляются Айнуром
                canvasSizeUpdate();
                if (mode == 0) {
                    mm.drawMenu(currentNanoTime);
                }
                if (mode == 1) {
                    graphic.interfaceLayer.getGraphicsContext2D().clearRect(0, 0, theScene.getWidth(), theScene.getHeight());

                    MapManager.drawMap(currentNanoTime);
                    PlayerManager.drawPlayer(currentNanoTime, currentMapNumber);
                }
                if (mode == 2) {
                    sm.drawMenu(currentNanoTime);
                }
                if (mode == 3) {
                }
                if (mode == 4) {
                }
                if (mode == 5) {
                    if (KeyManager.saveButt("UP")) {
                        mode = 2;
                    }
                }
                if (mode == 6) {
                    if (KeyManager.saveButt("DOWN")) {
                        mode = 2;
                    }
                }
                if (mode == 7) {
                    if (KeyManager.saveButt("RIGHT")) {
                        mode = 2;
                    }
                }
                if (mode == 8) {
                    if (KeyManager.saveButt("LEFT")) {
                        mode = 2;
                    }
                }
                if (mode == 9) {
                    if (KeyManager.saveButt("CHOSE")) {
                        mode = 2;
                    }
                }
                if (mode == 10) {
                    mem.drawMenu(currentNanoTime);
                }
                if (mode == 11) {
                    Platform.exit();
                }
                System.out.println(mode);
            }
        }.start();

        stage.show();
    }

    /**
     * обновление размера холста в соответсвие с размером окна
     */
    private static void canvasSizeUpdate() {
        if (prevStageHeight != theScene.getHeight() || prevStageWidth != theScene.getWidth() || first) {
            mapLayer.setHeight(3 * theScene.getHeight());
            mapLayer.setWidth(3 * theScene.getWidth());
            playerLayer.setHeight(theScene.getHeight());
            playerLayer.setWidth(theScene.getWidth());
            interfaceLayer.setHeight(theScene.getHeight());
            interfaceLayer.setWidth(theScene.getWidth());
            mapLayer.setTranslateX(-theScene.getWidth());
            mapLayer.setTranslateY(-theScene.getHeight());

            MapManager.setNeedRedraw();

            prevStageWidth = theScene.getWidth();
            prevStageHeight = theScene.getHeight();

        }


    }
}
