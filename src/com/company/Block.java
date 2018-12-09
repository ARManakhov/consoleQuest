package com.company;


import javafx.scene.image.Image;

import java.io.File;

/**
 * класс содержащий массив всех текстур блоков
 */
public class Block {

    private File folder = new File("./resources/blocks");

    private static Image[] blockTexture;

    public Image getBlock(int i){
        return blockTexture[i];
    }

    private Block(){
    }

    private static Block instance;

    public static Block getInstance() {
        if(instance == null){
            instance = new Block();
            TextureGet tg = new TextureGet(new File ("./resources/blocks/"));
            blockTexture = tg.getTexture();
        }
        return instance;
    }


}
