import java.io.*;
import java.net.ServerSocket;
public class Server implements Runnable{

    public  void run(){

        try {
            try  {
                Sol.server = new ServerSocket(Sol.port);
                Sol.clientSocket = Sol.server.accept();
                try {
                    Sol.in = new BufferedReader(new InputStreamReader(Sol.clientSocket.getInputStream()));
                    Sol.out = new BufferedWriter(new OutputStreamWriter(Sol.clientSocket.getOutputStream()));
                    while (true) {//cycle start

 String Clientword = Sol.in.readLine();
go(Clientword);
 //System.out.println("client says: "+Clientword);
                        while (Sol.servansw<1){
                            try{Thread.sleep(1);}catch(InterruptedException e){System.out.println(e);}
                        }
                        Sol.servansw=0;
                     //   System.out.println("server (me) responds: "+Sol.s);
                        Sol.out.write(Sol.s + "\n");
                        Sol.out.flush();
                        Sol.s="";
                    }//cycle end
                } finally {
                    Sol.clientSocket.close();
                    Sol.in.close();
                    Sol.out.close();
                }
            } finally {
                Sol.server.close();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    } //run server end

    void   go(String word){
        int t = -2;
        try {t=  Integer.parseInt(word);} catch (NumberFormatException e) {}
        if (t>-1&&t<200){Game.shoot(t);}

if (word.equals("hello_client")){
    Sol.mp=1;
Sol.s="hello_server";
    Sol.servansw=1;
    Game.clear();
    Sol.textline = ". Client has connected";
    Sol.lab.setText(" "+Sol.phase+" "+Sol.textline);
}

        if (word.equals("ready")){
            Sol.mp=3;
            if (Sol.phase.equals("ready")){Game.readygo();}
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
            Sol.s = "ends";            Sol.servansw=1;
        }

        Game.thread_delay_redraw();
    }


}