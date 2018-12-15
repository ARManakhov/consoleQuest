package com.company;

public class Furniture {
    static Furniture[][] furn;
    int x;
    int y;
    int hp ;     //HP
    int maxHp ;  //maxHP
    int exp ;    //EXP
    int dmg ;    //DMG
    int speed ;  //Speed
    int money ;  //Money
    int id ;  //Money
    String[] text;

    static Furniture getFurnitureOnPos (int x,int y,int curentMapNum){
        for (int i = 0; i <furn[curentMapNum].length ; i++) {
             if(furn[curentMapNum][i].x == x && furn[curentMapNum][i].y == y){
                 return furn[curentMapNum][i];

            }
        }
        return null;
    }

    public static Furniture[][] getFurn() {
        return furn;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getExp() {
        return exp;
    }

    public int getDmg() {
        return dmg;
    }

    public int getSpeed() {
        return speed;
    }

    public int getMoney() {
        return money;
    }

    public String[] getText() {
        return text;
    }

    void use() {
    }



    public Furniture(int x, int y,int id, int hp, int exp, int dmg, int speed, int money, int maxHp,String[] text) {// y , x , id , hp , exp, dmg , speed ,money , maxHP , TextLn , String[] text
        this.x = x;
        this.y = y;
        this.id = id;
        this.hp = hp;
        this.maxHp = maxHp;
        this.exp = exp;
        this.dmg = dmg;
        this.speed = speed;
        this.money = money;
        this.text = text;
    }
}
    /*
     String[] getText(){

    }
    int conteinsEXP(){

    }
    int conteinsHP(){

    }
    int conteinsMaxHP(){

    }
    int conteinsDamage(){

    }
    int conteinsMoney(){

    }*/


    /*                writeInt(j,file); //координату y
                writeInt(k,file); //координату x
                writeInt(map.furnitureMap[j][k],file); // id

                            writeInt(Furniture.furn[i].hp, file); // HP
                            writeInt(Furniture.furn[i].exp,file);   //EXP
                            writeInt(Furniture.furn[i].dmg,file);    //DMG
                            writeInt(Furniture.furn[i].speed,file);    //Speed
                            writeInt(Furniture.furn[i].maxHp,file);    //maxHP

                writeInt(0, file); // HP
                writeInt(0,file);   //EXP
                writeInt(0,file);    //DMG
                writeInt(0,file);    //Speed
                writeInt(0,file);    //maxHP
                writeString(new String[]{},file);
}*/
