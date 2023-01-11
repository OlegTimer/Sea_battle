import javax.swing.*;

public class Game {

    public static void newgame (){
        if (Sol.phase.equals("place_ships")||Sol.phase.equals("wait")){
            newgame2 ();
        }else{
 JOptionPane.showMessageDialog(null, "err", "game is running", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public static void newgame2 (){
        if (Sol.mp==0){
        CreateRandom.create(3);
        Sol.phase = "pl1_fire";
        Sol.textline = "";
       }else{
            Sol.phase = "ready";
            Sol.textline = ". wait for another player";

            if (Sol.mp==2)//if I am client
            {Sol.s="ready";  Sol.servansw=1; Sol.mp=1;}

            if (Sol.mp==3)//if I am server
            {Game.readygo();}

        }
        Sol.lab.setText(" "+Sol.phase+" "+Sol.textline);
        Field.redraw();
        Field.jFrame.repaint();
    }

    public static void   clear(){
        Sol.textline = " and go or Create_random field and start now";
        Sol.phase = "place_ships";
        Sol.lab.setText(" "+Sol.phase+" "+Sol.textline);
        Sol.map1 = new int[10][10];
        Sol.map2 = new int[10][10];
        Sol.map3 = new int[10][10];
        Field.redraw();
    }

    public static  void  checklastblock(int a, int b, int f){
        int map[][] = new int[10][10];
        if (f==1){map=Sol.map1;}
        if (f==2){map=Sol.map2;}
        if (f==3){map=Sol.map3;}

        int t = 0;
        int total = 0;
        boolean hasmore = false;
        int t2,t3,t4; t2=t3=t4=0;

        try {t2= map[a][b-1];} catch (Exception e){}
        try {t3= map[a][b-2];} catch (Exception e){}
        try {t4= map[a][b-3];} catch (Exception e){}
        if (t2==4){total++;}
        if (t3==4&&t2==2){total++;}
        if (t4==4&&t3==2&&t2==2){total++;}

        t2=t3=t4=0;
        try {t2= map[a][b+1];} catch (Exception e){}
        try {t3= map[a][b+2];} catch (Exception e){}
        try {t4= map[a][b+3];} catch (Exception e){}
        if (t2==4){total++;}
        if (t3==4&&t2==2){total++;}
        if (t4==4&&t3==2&&t2==2){total++;}

        t2=t3=t4=0;
        try {t2= map[a-1][b];} catch (Exception e){}
        try {t3= map[a-2][b];} catch (Exception e){}
        try {t4= map[a-3][b];} catch (Exception e){}
        if (t2==4){total++;}
        if (t3==4&&t2==2){total++;}
        if (t4==4&&t3==2&&t2==2){total++;}

        t2=t3=t4=0;
        try {t2= map[a+1][b];} catch (Exception e){}
        try {t3= map[a+2][b];} catch (Exception e){}
        try {t4= map[a+3][b];} catch (Exception e){}
        if (t2==4){total++;}
        if (t3==4&&t2==2){total++;}
        if (t4==4&&t3==2&&t2==2){total++;}

        if (total>0)   {hasmore=true;}

     if (!hasmore){Game.paintdestr(a,b,f);}
    }

    public static  void paintdestr(int a, int b, int f){
        Sol.textline=": destroyed";
        int map[][] = new int[10][10];
        if (f==1){map=Sol.map1;}
        if (f==2){map=Sol.map2;}
        if (f==3){map=Sol.map3;}

        map[a][b]=3;

        int t2,t3,t4; t2=t3=t4=0;
        try {t2= map[a][b-1];} catch (Exception e){}
        try {t3= map[a][b-2];} catch (Exception e){}
        try {t4= map[a][b-3];} catch (Exception e){}
        if (t2==2){ map[a][b-1]=3; }
        if (t3==2&&t2==2){map[a][b-2]=3;}
        if (t4==2&&t3==2&&t2==2){map[a][b-3]=3;}

        t2=t3=t4=0;
        try {t2= map[a][b+1];} catch (Exception e){}
        try {t3= map[a][b+2];} catch (Exception e){}
        try {t4= map[a][b+3];} catch (Exception e){}
        if (t2==2){ map[a][b+1]=3; }
        if (t3==2&&t2==2){map[a][b+2]=3;}
        if (t4==2&&t3==2&&t2==2){map[a][b+3]=3;}

        t2=t3=t4=0;
        try {t2= map[a-1][b];} catch (Exception e){}
        try {t3= map[a-2][b];} catch (Exception e){}
        try {t4= map[a-3][b];} catch (Exception e){}
        if (t2==2){ map[a-1][b]=3; }
        if (t3==2&&t2==2){map[a-2][b]=3;}
        if (t4==2&&t3==2&&t2==2){map[a-3][b]=3;}

        t2=t3=t4=0;
        try {t2= map[a+1][b];} catch (Exception e){}
        try {t3= map[a+2][b];} catch (Exception e){}
        try {t4= map[a+3][b];} catch (Exception e){}
        if (t2==2){ map[a+1][b]=3; }
        if (t3==2&&t2==2){map[a+2][b]=3;}
        if (t4==2&&t3==2&&t2==2){map[a+3][b]=3;}

        int    t=-1;
        for (int j = 0; j < 10; j++){
            for (int k = 0; k < 10; k++){
                if (map[j][k]==3){
   t=-1; try {t= map[j-1][k-1];} catch (Exception e){} if (t==0){map[j-1][k-1]=1;} //nw
 t=-1; try {t= map[j-1][k];} catch (Exception e){} if (t==0){map[j-1][k]=1;} //n
 t=-1; try {t= map[j-1][k+1];} catch (Exception e){} if (t==0){map[j-1][k+1]=1;}//ne
   t=-1; try {t= map[j][k-1];} catch (Exception e){} if (t==0){map[j][k-1]=1;} //w
  t=-1; try {t= map[j][k+1];} catch (Exception e){} if (t==0){map[j][k+1]=1;} //e
  t=-1; try {t= map[j+1][k-1];} catch (Exception e){} if (t==0){map[j+1][k-1]=1;}//sw
 t=-1; try {t= map[j+1][k];} catch (Exception e){} if (t==0){map[j+1][k]=1;}   //s
  t=-1; try {t= map[j+1][k+1];} catch (Exception e){} if (t==0){map[j+1][k+1]=1;}    //se
                }
            }}

        if (f==3){
            for (int j = 0; j < 10; j++){
                for (int k = 0; k < 10; k++){
                    if (map[j][k]!=4){
                    Sol.map3[j][k]=  Sol.map2[j][k]=map[j][k];}
                }}
        }

        if (f==2){
            for (int j = 0; j < 10; j++){
                for (int k = 0; k < 10; k++){
                    Sol.map2[j][k]=map[j][k];
                }}
        }

        if (f==1){
            for (int j = 0; j < 10; j++){
                for (int k = 0; k < 10; k++){
                        Sol.map1[j][k]=map[j][k];
                }}
        }
end();
    }

    public static void end (){
        if (Sol.mp==0) {
            int counter1 = 0;
            int counter2 = 0;
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    if (Sol.map1[j][k] == 4) {
                        counter1++;
                    }
                    if (Sol.map3[j][k] == 4) {
                        counter2++;
                    }
                }
            }
            if (counter1 == 0) {
                Sol.phase = "end";
                Sol.textline = " you failed";
                Sol.lab.setText(" "+Sol.phase+" "+Sol.textline);
                Field.redraw();
            }
            if (counter2 == 0) {
                Sol.phase = "end";
                Sol.textline = " you WON!";
                Sol.lab.setText(" "+Sol.phase+" "+Sol.textline);
                Field.redraw();
            }
        }
        else{

            int counter1 = 0;
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    if (Sol.map1[j][k] == 4) {
                        counter1++;
                    }
                }
            }

            if (counter1 == 0) {
                Sol.phase = "end";
                Field.redraw();
            }

        }
    }

    public static void readygo(){//for server
        Sol.s="ready";  Sol.servansw=1; Sol.mp=7;
        Sol.phase = "opponent shoots.";
        Sol.textline = " Game started";
        Sol.lab.setText(" "+Sol.phase+" "+Sol.textline);
        Field.redraw();
        Field.jFrame.repaint();
    }

    public static void shoot(int num){//pl is under fire

        int a = num/10;
        int b = num%10;

        String res = "miss";
        Sol.phase = "you_are_under_fire: ";
        if (Sol.map1[a][b]==4){res="hit";
            Sol.map1[a][b]=2;
            Game.checklastblock(a,b,1);}
        else{
            Sol.map1[a][b]=1;
        }

        if (Sol.map1[a][b]==3){res="destr";}

        if (Sol.phase.equals("end")){res="end";}

        Sol.textline = res;
        if (Sol.phase.equals("end")){Sol.textline=" you've lost";}
        Sol.lab.setText(" "+Sol.phase+" "+Sol.textline);

        Sol.s=res;
        Sol.servansw=1;

        Field.redraw();
        Field.jFrame.repaint();
    }

    public static void thread_delay_redraw(){
        DelayDraw ser = new DelayDraw();
        new Thread(ser).start();
    }

}
