package com.company;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class graphic extends Application {
    private static final int X_SIZE = 512;
    private static final int Y_SIZE = 512;

    public static Canvas canvas = new Canvas(X_SIZE,Y_SIZE);
    public static Group root = new Group();
    public static Scene theScene = new Scene( root );


    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle( "Enima" );
        stage.setScene( theScene );
        root.getChildren().add( canvas );

        keyManager.prepareActionHandlers();


        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double time = (currentNanoTime - startNanoTime) / 1000000000.0;
                Menu.drawMenu(currentNanoTime);


            }
        }.start();

        stage.show();
    }
}
