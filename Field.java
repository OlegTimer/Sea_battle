import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public  class Field extends JPanel implements ActionListener {
  public static JFrame jFrame = new JFrame("Sea Battle by OlegTim");

        Field(){
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

            jFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                   try{
                    Sol.clientSocket.close();
                    Sol.in.close();
                    Sol.out.close();
                    Sol.server.close();} catch (Exception e){}
                        System.exit(0);
                }
            });


            JLabel jlab = new JLabel();
            JMenuBar jmb = new JMenuBar();
            JMenu game = new JMenu("game");
            JMenuItem newgame = new JMenuItem("new_game");
            newgame.addActionListener(this);
            game.add(newgame);
            jmb.add(game);
         if (Sol.mp==0)  {
             JMenu multiplayer = new JMenu("multiplayer");
             JMenuItem mp_go = new JMenuItem("mp_go");
             jmb.add(multiplayer);
             mp_go.addActionListener(this);
             multiplayer.add(mp_go);
         }
            JMenu info = new JMenu("info");
            JMenuItem info_show = new JMenuItem("info_show");
            jmb.add(info);
            info_show.addActionListener(this);
            info.add(info_show);

            JMenu redraw = new JMenu("redraw");
            JMenuItem redraw_now = new JMenuItem("redraw_now");
            jmb.add(redraw);
            redraw_now.addActionListener(this);
            redraw.add(redraw_now);

            jFrame.add(jlab);
            jFrame.setJMenuBar(jmb);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(10,210,0,0));

            int bt = -1;
            int counter=0;
            for (int i = 0; i <210; i++) {
                JButton button = new JButton();
                bt++;

                if (bt>99){
                    counter++;
                }

                if (isborder(i)){
                    bt=bt+100-11;
                }

                if (counter>10) {
                    bt=bt-100;
                    counter=0;
                }
                if(i==209){bt=199;}
                button.setName("" + bt);//0-99 first field and 100-199 second

                int m =-1;
                if (!isborder(i)) {
                    if (bt > 99) {
                        int a = (bt-100)/10;
                        int b = (bt-100)%10;
                        m = Sol.map2[a][b];
                    } else {
                        int a = bt/10;
                        int b = bt%10;
                        m = Sol.map1[a][b];
                    }
                }

                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String s = ((JComponent) e.getSource()).getName();
                        int a = Integer.parseInt(s);
                        cellclick(a);
                        redraw();
                    }
                });
                button.setEnabled(true);
                button.setBackground(Color.blue);

                Color c = new Color(0,0,255); // 0 empty
                switch (m){
                    case 1:  c = new Color(0,0,0); break; //miss
                    case 2:  c = new Color(255,0,0); break; //hit
                    case 3:  c = new Color(135,0,20); break; //sunk
                    case 4:  c = new Color(0,255,0); break; //friendly
                }
                button.setBackground(c);

                button.setEnabled(false);
                if (Sol.phase.equals("place_ships")&&bt<100){
                    button.setEnabled(true);
                }
                if (Sol.phase.equals("pl1_fire")&&bt>99&&m==0){
                    button.setEnabled(true);
                }
                if (isborder(i)){
                    button.setEnabled(false);
                    button.setBackground(Color.white);
                }
                button.setPreferredSize(new Dimension(20, 20));
                panel.add(button);
            }

            Sol.lab.setText(" "+Sol.phase+" "+Sol.textline);
            JPanel panel1 = new JPanel();
            panel1.setLayout(new GridLayout(2,1,0,0));
            JPanel panel2 = new JPanel();
            panel2.setLayout(new GridLayout(10,1,0,0));
            panel2.add(Sol.lab);

            Button    button2= new Button("GO");
            button2.addActionListener(this);
        Button    button1= new Button("Create_random");
            button1.addActionListener(this);
            if (Sol.phase.equals("place_ships")){
            panel2.add(button2);
            panel2.add(button1);}

            panel1.add(panel);
            panel1.add(panel2);
            jFrame.add(panel1);
            jFrame.setVisible(true);
        }

 public static void redraw(){
      jFrame.getContentPane().removeAll();
      jFrame.dispose();
      new Field();
      jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      jFrame.revalidate();
}



    @Override
    public void actionPerformed(ActionEvent e) {

        String comstr = e.getActionCommand();
        if (comstr.equals("new_game")){
            if (Sol.mp!=0){
                if (Sol.phase.equals("end")){ Game.clear();}else{
  JOptionPane.showMessageDialog(null, "err", "mp_game is running", JOptionPane.PLAIN_MESSAGE);
                }
            }else{
            Game.clear();}
        }

        if (comstr.equals("Create_random")){
            Sol.phase = "wait";
            Sol.textline = "";
            Sol.lab.setText(" "+Sol.phase+" "+Sol.textline);
            redraw();
            CreateRandom.create(1);
            newg();
        }
        if (comstr.equals("GO")){
            newg();
        }
        if (comstr.equals("mp_go")){
new Multipl();
        }
        if (comstr.equals("info_show")){
            JOptionPane.showMessageDialog(null,
                    new String[] {"Place ships on left field with Left Mouse Button and click <GO> to start",
                            " or click <Create_random> to randomly create your field and start.",
                            "_Ships can placed horizontal or vertical, not on neighbour blocks.",
                            "Ships are: 4-blocks (1 ship); 3-blocks (2); 2-blocks (3); 1-block (4).",
                            "_Try to hit opponent's ships with LMB on the right field.",
                            "After game ends, click <game> - <new game>.",
                            "To play with a person in <multiplayer> , select <mp_go>.",
                            "At first, <create> game; secondly, <join> game on another computer.",
                            "If needed, you can edit port and ip-address, and then create/join.",
                            " Best wishes, OlegTim."},
                    "Info",
                    JOptionPane.PLAIN_MESSAGE);
        }

        if (comstr.equals("redraw_now")){
            Field.redraw();
            Field.jFrame.repaint();
            Sol.lab.setText(" "+Sol.phase+" "+Sol.textline);
        }

    }



public boolean isborder (int i){
    return i == 10 || i == 31 || i == 52 || i == 73 || i == 94 || i == 115 || i == 136 || i == 157 || i == 178 || i == 199;
}

    public void newg(){
        boolean boo = Checkfield.checkstart(1);
        if (boo){
            Sol.phase = "wait";
            Sol.textline = "";
            Sol.lab.setText(" "+Sol.phase+" "+Sol.textline);
            redraw();
            Game.newgame();
        }else
        {JOptionPane.showMessageDialog(null, "err", "check ships", JOptionPane.PLAIN_MESSAGE);}
    }
    public void cellclick (int i){
            if (i > 99) {
                int a = (i-100)/10;
                int b = (i-100)%10;
                Actionpl1.rightfieldlclick(a,b);
            } else {
                int a = i/10;
                int b = i%10;
                Actionpl1.leftfieldlclick(a,b);
            }}


}

