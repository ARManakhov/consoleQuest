package com.company;


import javafx.scene.image.Image;

import java.io.File;

/**
 * класс содержащий массив всех текстур блоков
 */
public class Block {

    private File folder = new File("./resources/blocks");

    private static Image[] blockTexture;
    private static Image[] furnirureTexture;


    public Image getBlock(int i){
        return blockTexture[i];
    }

    public Image getFurniture(int i){
        return furnirureTexture[i];
    }

    public Image[] getBlock(){
        return blockTexture;
    }

    public byte getBlockArrSize(){
        return (byte) blockTexture.length;
    }




    private Block(){
    }

    private static Block instance;

    public static Block getInstance() {
        if(instance == null){
            instance = new Block();
            TextureGet tg = new TextureGet(new File ("./resources/blocks/"));
            blockTexture = tg.getTexture();

            tg = new TextureGet(new File ("./resources/furniture/"));
            furnirureTexture = tg.getTexture();

        }
        return instance;
    }


}
