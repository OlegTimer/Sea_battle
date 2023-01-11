public class Ai {
    public static void go (){
        int counter = 0; int a,b; a=b=-1;
        for (int j = 0; j < 10; j++){
            for (int k = 0; k < 10; k++){
                if (Sol.map1[j][k]==2){//

                    int t=-1; counter++;
  t=-1; try {t= Sol.map1[j-1][k];} catch (Exception e){}
   if (t == 0 || t == 4){go2(j-1, k);} else{
       t=-1; try {t= Sol.map1[j+1][k];} catch (Exception e){}
       if (t == 0 || t == 4){go2(j+1, k);} else{
           t=-1; try {t= Sol.map1[j][k-1];} catch (Exception e){}
           if (t == 0 || t == 4){go2(j, k-1);} else{
               t=-1; try {t= Sol.map1[j][k+1];} catch (Exception e){}
               if (t == 0 || t == 4){go2(j, k+1);}
           }
       }
   }

                }//
            }}

        if (counter==0) {
            int r = (int) (Math.random() * 100);
            a = (r) / 10;
            b = (r) % 10;
            if (Sol.map1[a][b] == 0 || Sol.map1[a][b] == 4) {
                go2(a, b);
            } else {
                go();
            }
        }
    }

    public static void go2(int a, int b){
        Sol.textline="";
        if (Sol.map1[a][b]==4){
            Sol.map1[a][b]=2;
            Sol.phase = "pl2_fire";
            Game.checklastblock(a,b,1);
            if (!Sol.phase.equals("end")) { go();}
        }else{
            Sol.map1[a][b]=1;
            Sol.phase = "pl1_fire";
            Field.redraw();
        }
    }
}
