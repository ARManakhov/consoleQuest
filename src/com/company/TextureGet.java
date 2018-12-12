package com.company;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;

public class TextureGet {
    private File resourceFolder ;
    private File[] listOfAllFiles;

    public static Image[] Textures;

    public void setResourceFolder(File resourceFolder){
        if(resourceFolder.isDirectory() && resourceFolder.exists()){
            this.resourceFolder = resourceFolder;
            Logger.getLoger().addLogg("директория с текстурами изменена");
        } else{
            Logger.getLoger().addLogg("смена директории с текстурами была инициирована но не выполнена");
        }
    }

    public void readFolderFiles(){
        if(resourceFolder.isDirectory() && resourceFolder.exists()){
            try {
                Logger.getLoger().addLogg("текстуры поменяны на текстуры из директории " + resourceFolder.getCanonicalPath());
                listOfAllFiles = resourceFolder.listFiles();
                String[] names = new String[listOfAllFiles.length];

                for (int i = 0; i < listOfAllFiles.length; i++) {
                    names[i] = listOfAllFiles[i].getName();
                }


                for (int i = 0; i < listOfAllFiles.length; i++) {
                    for (int j = i; j < listOfAllFiles.length; j++) {
                        int a=0;
                        int b=0;

                        int l=0;

                        while (names[i].charAt(l) != '.'){
                            a = a*10 + names[i].charAt(l);
                            l++;
                        }

                        l=0;
                        while (names[j].charAt(l) != '.'){
                            b = b*10 + names[j].charAt(l);
                            l++;
                        }


                        if(a > b){
                            String sh = names[i];
                            names[i] = names[j];
                            names[j] = sh;

                            File fh = listOfAllFiles[i];
                            listOfAllFiles[i] = listOfAllFiles[j];
                            listOfAllFiles[j] = fh;
                        }
                    }
                }

            } catch (IOException e) {
                Logger.getLoger().addLogg("произошло невозможное, файл текстуры не найден, хотя список файлов прочитан выше");
                e.printStackTrace();
            }

        }
    }

    public void readTextures(){
        readFolderFiles();
        Textures = new Image[listOfAllFiles.length];
        for (int i = 0; i < listOfAllFiles.length; i++){
            if(!listOfAllFiles[i].isDirectory() && listOfAllFiles[i].exists()){
                try {
                    Textures[i] = new Image(new FileInputStream(listOfAllFiles[i]));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public Image getTexture(int i){
        return Textures[i];
    }

    public Image[] getTexture(){
        return Textures;
    }

    public File getTextureAsFile(int i) {

        return listOfAllFiles[i];
    }

    public int getFileCount(){
        return listOfAllFiles.length;
    }


     TextureGet(File source){
        this.resourceFolder = source;
        this.readTextures();
    }

    /*private static TextureGet Instance;

    public static TextureGet getInstance() {
        if(Instance == null){
            Instance = new TextureGet();
        }
        return Instance;
    }*/
}
