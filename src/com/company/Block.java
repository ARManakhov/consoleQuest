package com.company;


import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * класс содержащий массив всех текстур блоков
 */
public class Block {

    private File folder = new File("./resources/blocks");

    public static Image[] blockTexture;

    static {
        try {
            blockTexture = new Image[]{
                        new Image( new FileInputStream(new File("./resources/blocks/0.png"))),
                        new Image( new FileInputStream(new File("./resources/blocks/1.png")))
                };
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setFolder(File folder){
        if(folder.isDirectory() && folder.exists()){
            this.folder = folder;
            Logger.getLoger().addLogg("директория с текстурами блоков изменена");
        } else{
            Logger.getLoger().addLogg("смена директории с текстурами блоков была инициирована но не выполнена");
        }
    }

    public void readFolder(){
        if(folder.isDirectory() && folder.exists()){
            try {
                Logger.getLoger().addLogg("текстуры поменяны на текстуры из директории " + folder.getCanonicalPath());
                File[] textureFiles = folder.listFiles();
                blockTexture = new Image[textureFiles.length];

                for (int i = 0; i < textureFiles.length; i++) {
                        blockTexture[i] = new Image(new FileInputStream(textureFiles[i]));
                }
            } catch (IOException e) {
                Logger.getLoger().addLogg("произошло невозможное, файл текстуры не найден, хотя список файлов прочитан выше");
                e.printStackTrace();
            }

        }
    }

    public Image getBlock(int i){
        return blockTexture[i];
    }

    private Block(){
    }

    private static Block Instance;

    public static Block getInstance() {
        if(Instance == null){
            Instance = new Block();
        }
        return Instance;
    }


}
