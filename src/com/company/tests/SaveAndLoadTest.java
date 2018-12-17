package com.company.tests;

import com.company.Map;
import com.company.MapReader;
import com.company.MapSaver;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;
public class SaveAndLoadTest {
    MapReader mr;

    @Before
    public void start(){
        mr = MapReader.getInstance();
        mr.changeFile(new File("./mapTest/"));
    }

    @Test
    public void loadMapTest() {
        mr.readFolderFiles();
        mr.readMapsFromFolder();
        byte[][] arr = {{1,1},
                        {1,1}};
        Map expected = new Map(2,2,arr,arr,arr,arr,arr);
        assertEquals(expected.spawnPosX,Map.maps[0].spawnPosX);
        assertEquals(expected.spawnPosY,Map.maps[0].spawnPosY);
        assertArrayEquals(expected.furnitureMap,Map.maps[0].furnitureMap);
        assertArrayEquals(expected.borderMap,Map.maps[0].borderMap);
        assertArrayEquals(expected.groundMap,Map.maps[0].groundMap);
        assertArrayEquals(expected.teleportMap,Map.maps[0].teleportMap);
        assertArrayEquals(expected.enemyMap,Map.maps[0].enemyMap);
    }

    @Test
    public void saveMapTest() {

        byte[][] arr = {{1,1},
                        {1,1}};
        Map expected = new Map(2,2,arr,arr,arr,arr,arr);
        MapSaver.getMapSaver().save(expected,new File("./mapTest/map0"));
        mr.readFolderFiles();
        mr.readMapsFromFolder();
        assertEquals(expected.spawnPosX,Map.maps[0].spawnPosX);
        assertEquals(expected.spawnPosY,Map.maps[0].spawnPosY);
        assertArrayEquals(expected.furnitureMap,Map.maps[0].furnitureMap);
        assertArrayEquals(expected.borderMap,Map.maps[0].borderMap);
        assertArrayEquals(expected.groundMap,Map.maps[0].groundMap);
        assertArrayEquals(expected.teleportMap,Map.maps[0].teleportMap);
        assertArrayEquals(expected.enemyMap,Map.maps[0].enemyMap);
    }
}
