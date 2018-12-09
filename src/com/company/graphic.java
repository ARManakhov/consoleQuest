package com.company;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;


public class graphic extends Application {

    private static final int X_SIZE = 1024;     //стандартная ширина окна
    private static final int Y_SIZE = 720;      //стандартная высота окна

    public static Group root = new Group();     //группа root
    public static Canvas mapLayer = new Canvas();           //слой карты
    public static Canvas playerLayer = new Canvas();        //слой с игроком и возможно с мобами
    public static Canvas interfaceLayer = new Canvas();     //холст интерфейса
    private static Pane mainPane = new Pane();

    private static Logger logger = Logger.getLoger();

    private static double prevStageHeight = 0;
    private static double prevStageWidth = 0;

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

    private static boolean first = true;

    /**
     * игровая петля
     * @param stage основная сцена
     * @throws Exception добавил при добавление ввода вывода, но может еще пригодится
     */
    @Override
    public void start(Stage stage) throws Exception{
        if (first){
            MapReader.getInstance().readMapsFromFolder();
            logger.setLogFile(new File("log.txt"));
            prevStageHeight = theScene.getHeight();
            prevStageWidth = theScene.getWidth();
            canvasSizeUpdate();
            first = false;
        }
        stage.setTitle( "Enima" );
        stage.setScene( theScene );
        mainPane.getChildren().add(interfaceLayer);     //добавление слоев в Pane
        mainPane.getChildren().add(playerLayer);
        mainPane.getChildren().add(mapLayer);
        root.getChildren().add(mainPane);               //добавление Pane в Root
        mapLayer.toFront();
        playerLayer.toFront();
        interfaceLayer.toFront();

        KeyManager.prepareActionHandlers(); //проверяем ввод с устройств




        /**
         * таймер анимации
         */
        new AnimationTimer()  {
            public void handle(long currentNanoTime)  {

                canvasSizeUpdate();
                //System.out.println(currentNanoTime);
                if (mode == 0){
                 MainMenu.draw(currentNanoTime);
                }
                if (mode == 1){
                    MapManager.serDrawFromAngle(false);
                    MapManager.draw(currentNanoTime, currentMapNumber);
                    PlayerManager.getInstance().draw(currentNanoTime,currentMapNumber);

                }
                if(mode == 2){
                    MapManager.serDrawFromAngle(true);
                    MapManager.draw(currentNanoTime, currentMapNumber);
                    mapEditor.draw(currentNanoTime,currentMapNumber);
                }
            }
        }.start();



        stage.show();
    }

    @Override
    public void stop(){
        logger.saveLogFile();
    }

    /**
     * обновление размера холста в соответсвие с размером окна
     */
    public static void canvasSizeUpdate (){
        if ( prevStageHeight != theScene.getHeight() || prevStageWidth != theScene.getWidth() || first){
            mapLayer.setHeight(3*theScene.getHeight());
            mapLayer.setWidth(3*theScene.getWidth());
            playerLayer.setHeight(theScene.getHeight());
            playerLayer.setWidth(theScene.getWidth());
            interfaceLayer.setHeight(theScene.getHeight());
            interfaceLayer.setWidth(theScene.getWidth());
            mapLayer.setTranslateX(-theScene.getWidth());
            mapLayer.setTranslateY(-theScene.getHeight());
            MapManager.setNeedRedraw();

            prevStageWidth = theScene.getWidth();
            prevStageHeight = theScene.getHeight();
            logger.addLogg("выполнено назначение или обновление размера окна");
        }


    }
}
