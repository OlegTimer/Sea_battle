public class Checkfield {

    public static boolean checkstart(int a){
        boolean res = true;
        int map[][] = new int[10][10];
        if (a==1){map=Sol.map1;}
        if (a==2){map=Sol.map2;}
        if (a==3){map=Sol.map3;}

        int counter = 0;
        for (int j = 0; j < 10; j++){
            for (int k = 0; k < 10; k++){
                if (map[j][k]==4){counter++;}
            }}
if (counter!=20){res=false;}
// res=true; //for test

int block1 = 0;
        int block2 = 0;
        int block3 = 0;
        int block4 = 0;
        int t = 0;
        int total = 0;
if (res){
        for (int j = 0; j < 10; j++){
            for (int k = 0; k < 10; k++){
                t=0; total =0;
                if (map[j][k]==4){
                    try {t= map[j-1][k];} catch (Exception e){} total +=t;
                    try {t= map[j+1][k];} catch (Exception e){} total +=t;
                    try {t= map[j][k-1];} catch (Exception e){} total +=t;
                    try {t= map[j][k+1];} catch (Exception e){} total +=t;
                    if (total==0){block1++;}else{
                        int t0,t2,t3,t4,t5; t0=t2=t3=t4=t5=0;
                        try {t0= map[j][k-1];} catch (Exception e){}
                        try {t2= map[j][k+1];} catch (Exception e){}
                        try {t3= map[j][k+2];} catch (Exception e){}
                        try {t4= map[j][k+3];} catch (Exception e){}
                        try {t5= map[j][k+4];} catch (Exception e){}
 if (t0!=4&t2==4&&t3!=4){block2++;k+=1;}
 if (t0!=4&t2==4&&t3==4&&t4!=4){block3++;k+=2;}
 if (t0!=4&t2==4&&t3==4&&t4==4&&t5!=4){block4++;k+=3;}
                    }
                }
            }}

    for (int k = 0; k < 10; k++){
    for (int j = 0; j < 10; j++){
            t=0; total =0;
            if (map[j][k]==4){
                try {t= map[j-1][k];} catch (Exception e){} total +=t;
                try {t= map[j+1][k];} catch (Exception e){} total +=t;
                try {t= map[j][k-1];} catch (Exception e){} total +=t;
                try {t= map[j][k+1];} catch (Exception e){} total +=t;
                if (total != 0) {
                    int t0,t2,t3,t4,t5; t0=t2=t3=t4=t5=0;
                    try {t0= map[j-1][k];} catch (Exception e){}
                    try {t2= map[j+1][k];} catch (Exception e){}
                    try {t3= map[j+2][k];} catch (Exception e){}
                    try {t4= map[j+3][k];} catch (Exception e){}
                    try {t5= map[j+4][k];} catch (Exception e){}
                    if (t0!=4&t2==4&&t3!=4){block2++;j+=1;}
                    if (t0!=4&t2==4&&t3==4&&t4!=4){block3++;j+=2;}
                    if (t0!=4&t2==4&&t3==4&&t4==4&&t5!=4){block4++;j+=3;}
                }
            }
        }}

}
//System.out.println(block1 +" "+block2 +" "+block3 +" "+block4 );
        if (block1!=4||block2!=3||block3!=2||block4!=1){res=false;}
        return res;
    }

}
