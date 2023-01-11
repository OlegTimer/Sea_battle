public class CreateRandom {
 public static   int map[][] = new int[10][10];
    public static   int var =1;
    public static void create(int f) {
        Sol.phase = "wait";
        Sol.textline = "";
        Sol.lab.setText(" "+Sol.phase+" "+Sol.textline);

        var=f;
        map= new int[10][10];
        for (int i = 0; i < 20; i++) {
            int r = (int) (Math.random() * 100);
            int a = (r) / 10;
            int b = (r) % 10;

            int t = 0;
            int total = 0;
            try {t= map[a-1][b-1];} catch (Exception e){} total +=t;
            try {t= map[a-1][b+1];} catch (Exception e){} total +=t;
            try {t=map [a+1][b-1];} catch (Exception e){} total +=t;
            try {t= map[a+1][b+1];} catch (Exception e){} total +=t;
            if (total==0&& map[a][b]==0) {
            map[a][b] = 4;
            }
            else{i--;}
        }

     if (var==1){   transfer1();}
        if (var==2){   transfer2();}
        if (var==3){   transfer3();}
    }


    public static void transfer1() {
        for (int j = 0; j < 10; j++){
            for (int k = 0; k < 10; k++){
                Sol.map1[j][k]=map[j][k];
            }}
        boolean boo = Checkfield.checkstart(1);
if (boo) {Field.redraw();}
else{create(1);}
    }

    public static void transfer2() {
        for (int j = 0; j < 10; j++){
            for (int k = 0; k < 10; k++){
                Sol.map2[j][k]=map[j][k];
            }}
        boolean boo = Checkfield.checkstart(2);
        if (boo) {Field.redraw();}
        else{create(2);}
    }


    public static void transfer3() {
        for (int j = 0; j < 10; j++){
            for (int k = 0; k < 10; k++){
                Sol.map3[j][k]=map[j][k];
            }}
        boolean boo = Checkfield.checkstart(3);
        if (boo) {Field.redraw();}
        else{create(3);}
    }
}