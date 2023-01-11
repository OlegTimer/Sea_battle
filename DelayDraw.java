public class DelayDraw implements Runnable{
    public  void run(){
        try{Thread.sleep(100);}catch(InterruptedException e){System.out.println(e);}
        Field.redraw();
        Field.jFrame.repaint();
        Sol.lab.setText(" "+Sol.phase+" "+Sol.textline);
    }
}
