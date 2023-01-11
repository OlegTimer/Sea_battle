public class Actionpl1 {
    public static void leftfieldlclick (int a, int b){

        int t = 0;
        int total = 0;
        try {t= Sol.map1[a-1][b-1];} catch (Exception e){} total +=t;
        try {t= Sol.map1[a-1][b+1];} catch (Exception e){} total +=t;
        try {t= Sol.map1[a+1][b-1];} catch (Exception e){} total +=t;
        try {t= Sol.map1[a+1][b+1];} catch (Exception e){} total +=t;

        int counter = 0;
        for (int j = 0; j < 10; j++){
            for (int k = 0; k < 10; k++){
                if (Sol.map1[j][k]==4){counter++;}
            }}

        if (total==0) { //if not diagonal
            if (  Sol.map1[a][b] == 0){
                if (counter<20){
            Sol.map1[a][b] = 4;}
            }
           else{
                Sol.map1[a][b] = 0;}
        }
    }

    public static void rightfieldlclick (int a, int b){
       if (Sol.mp==0){
           if (Sol.map3[a][b]==4){
               Sol.textline=": hit!";
               Sol.map3[a][b]= Sol.map2[a][b]=2;
               Game.checklastblock(a,b,3);
           }
           else{
               Sol.phase = "pl2_fire";
               Sol.textline=": miss";
               Sol.map3[a][b]= Sol.map2[a][b]=1;
               Ai.go();
           }
       }//mp 0 end
        else{//pl is firing
           Sol.phase = "shell_away";
           Sol.textline = "";
           Sol.lab.setText(" "+Sol.phase+" "+Sol.textline);
           Sol.aa=a; Sol.ab = b;
           Sol.s=a+""+b;
           Sol.servansw=1;
       }
    }

}


