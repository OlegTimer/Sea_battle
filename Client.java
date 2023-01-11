import java.io.*;
import java.net.Socket;

public class Client implements Runnable{
    public  void run(){
        try {
            try {
                Sol.clientSocket = new Socket(Sol.iptarget, Sol.port);
                Sol.in = new BufferedReader(new InputStreamReader(Sol.clientSocket.getInputStream()));
                Sol.out = new BufferedWriter(new OutputStreamWriter(Sol.clientSocket.getOutputStream()));
                Sol.phase = "";
                Sol.textline = "connected to server";
                Sol.lab.setText(" "+Sol.phase+" "+Sol.textline);
                while(true){//cycle start

                    if (Sol.mp==0){
                        Sol.s="hello_client";
                    }
                    else{
                    while (Sol.servansw<1){
                        try{Thread.sleep(1);}catch(InterruptedException e){System.out.println(e);}
                    }}
                    Sol.servansw=0;
                //    System.out.println("client (me) responds: "+Sol.s);
                    Sol.out.write(Sol.s + "\n");
                    Sol.out.flush();
                    Sol.s="";

                    String serverWord = Sol.in.readLine();
                //    System.out.println("server says: "+serverWord);
                    go(serverWord);
                }//cycle end
            } finally {
                Sol.clientSocket.close();
                Sol.in.close();
                Sol.out.close();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

 void   go(String word){
     int t = -2;
     try {t=  Integer.parseInt(word);} catch (NumberFormatException e) {}
     if (t>-1&&t<200){Game.shoot(t);}


     if (word.equals("hello_server")){
         Sol.mp=2; //I am a client
         Game.clear();
         Sol.textline = ". Connected server";
         Sol.lab.setText(" "+Sol.phase+" "+Sol.textline);
     }

     if (word.equals("ready")){
         Sol.phase = "pl1_fire";
         Sol.textline = " Game started";
         Sol.lab.setText(" "+Sol.phase+" "+Sol.textline);
         Field.redraw();
         Field.jFrame.repaint();
     }

     if (word.equals("miss")){
         Sol.map2[Sol.aa][Sol.ab] = 1;
         Sol.aa = -2; Sol.ab = -2;
         Sol.phase = " you_missed. ";
         Sol.lab.setText(" "+Sol.phase+" "+Sol.textline);
         Field.redraw();
         Field.jFrame.repaint();
         Sol.s = "ok";            Sol.servansw=1;
     }

     if (word.equals("ok")){
         Sol.phase = "pl1_fire";
         Sol.textline = "";
         Sol.lab.setText(" "+Sol.phase+" "+Sol.textline);
         Field.redraw();
         Field.jFrame.repaint();
     }

     if (word.equals("hit")){
         Sol.map2[Sol.aa][Sol.ab] = 2;
         Sol.aa = -2; Sol.ab = -2;
         Sol.phase = "pl1_fire";
         Sol.textline = " you've just hit!";
         Sol.lab.setText(" "+Sol.phase+" "+Sol.textline);
         Field.redraw();
         Field.jFrame.repaint();
     }

     if (word.equals("destr")){
         Game.paintdestr(Sol.aa, Sol.ab,2);
         Sol.aa = -2; Sol.ab = -2;
         Sol.phase = "pl1_fire";
         Sol.textline = " you've just destroyed!";
         Sol.lab.setText(" "+Sol.phase+" "+Sol.textline);
         Field.redraw();
         Field.jFrame.repaint();

     }

     if (word.equals("end")){
         Game.paintdestr(Sol.aa, Sol.ab,2);
         Sol.aa = -2; Sol.ab = -2;
         Sol.phase = "end";
         Sol.textline = " you've WON!";
         Sol.lab.setText(" "+Sol.phase+" "+Sol.textline);
         Field.redraw();
         Field.jFrame.repaint();
         Sol.mp=2;
     }

     if (word.equals("ends")){
         Sol.phase = "end";
         Sol.textline = " you've lost";
         Sol.lab.setText(" "+Sol.phase+" "+Sol.textline);
         Field.redraw();
         Field.jFrame.repaint();
         Sol.mp=2;
     }

     Game.thread_delay_redraw();
    }
}
