package com.company;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class graphic extends Application {
    private static final int X_SIZE = 1024;
    private static final int Y_SIZE = 720;

    public static Canvas canvas = new Canvas();
    public static Group root = new Group();
    public static Group mapNode = new Group();


    public static Scene theScene = new Scene( root, X_SIZE, Y_SIZE );

    public static byte mode = 0;


    @Override
    public void start(Stage stage) throws Exception{
        Player sirossh = new Player("sirossh");
        stage.setTitle( "Enima" );
        stage.setScene( theScene );
        root.getChildren().add( canvas );

        KeyManager.prepareActionHandlers();


        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                //double time = (currentNanoTime - startNanoTime) / 1000000000.0;

                canvasSizeUpdate();

                if (mode == 0) {

                    Menu.drawMenu(currentNanoTime);
                }
                if (mode == 1){

                    MapManager.drawMap(currentNanoTime);
                    sirossh.drawPlayer(currentNanoTime);
                }

            }
        }.start();

        stage.show();
    }

    private static void canvasSizeUpdate (){
        canvas.setHeight(theScene.getHeight());
        canvas.setWidth(theScene.getWidth());
    }
}
