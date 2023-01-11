/*
path C:\Program Files (x86)\java\jdk1.7.0\bin
cd c:\1
javac *.java
jar cmf manif.txt  Sol.jar *.class
*/

import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Sol  {
 public static int aa =-2;
 public static int ab =-2;
    public static int mp =0;
    public static JLabel lab = new JLabel();
   public static String textline = "";
    public static String phase = "";
    public static String iptarget = "127.0.0.1";
    public static int port = 26000;
    public static Socket clientSocket;
    public static ServerSocket server;
    public static BufferedReader in;
    public static BufferedWriter out;

    public static int servansw =0;
    public static String s="s";
    public static    int map1[][] = new int[10][10];
    public static    int map2[][] = new int[10][10];
    public static    int map3[][] = new int[10][10];
    public static void main(String[] args) {Game.clear();}
}
