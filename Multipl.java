import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Multipl implements ActionListener {
    public static      JFrame fr=null;
    public static   JTextArea display2=null;
    public static   JTextArea display3=null;
    Multipl(){

         fr = new JFrame("mp_menu");
        fr.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8,1,0,0));

        Label label = new Label();
        label.setText("Current IP address: ");
        panel.add(label);

        String localip = null;
        try {
            InetAddress ip = InetAddress.getLocalHost();
            localip= "" + ip.getHostAddress();
        } catch (UnknownHostException e) {
            System.err.println(e);
        }

        JTextArea display = new JTextArea ( 1,20 );
        display.setEditable ( false );
        display.append(" "+localip);
        panel.add(display);

        Label label2 = new Label();
        label2.setText("(edit if needed) Port is: ");
        panel.add(label2);

         display2 = new JTextArea ( 1,20 );
        display2.setEditable ( true );
        display2.append(" "+Sol.port);
        panel.add(display2);

        Label label3 = new Label();
        label3.setText("(edit if needed) Try connect to ip: ");
        panel.add(label3);

         display3 = new JTextArea ( 1,20 );
        display3.setEditable ( true );
        display3.append(" "+Sol.iptarget);
        panel.add(display3);

        Button    button2= new Button("create");
        button2.addActionListener(this);
        panel.add(button2);
        Button    button1= new Button("join");
        button1.addActionListener(this);
        panel.add(button1);

        fr.add(panel);
       fr.setBounds(50,50,500,500);
        fr.setSize(500, 500);
       fr.setResizable(false);
        fr.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comstr = e.getActionCommand();
        if (comstr.equals("create")){
            getinfo();
            Sol.phase = "";
            Sol.textline = "server created, wait for client";
            Sol.lab.setText(" "+Sol.phase+" "+Sol.textline);
            Server ser = new Server();
            new Thread(ser).start();
        }
        if (comstr.equals("join")){
            getinfo();
            Sol.phase = "";
            Sol.textline = "try to join server (if too long - failed)";
            Sol.lab.setText(" "+Sol.phase+" "+Sol.textline);
            Client ser = new Client();
            new Thread(ser).start();
        }
    }

    public void getinfo(){
       String s =display2.getText();
        s = s.replaceAll("\\s+","");
        Sol.port=Integer.parseInt( s);
        Sol.iptarget = display3.getText();
        Sol.iptarget =  Sol.iptarget.replaceAll("\\s+","");
        fr.dispose();
    }

}
