package classes;

import java.awt.*;

import javax.swing.*;

public class GradientPanel extends JPanel {
    public GradientPanel(LayoutManager lm) {
        super(lm);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!isOpaque()) {
            return;
        }
       
        int width = getWidth();
        int height = getHeight();
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gradientPaint =new GradientPaint(width/2, height/2,Color.white, width, height, Color.blue,false);
       
       
        g2.setPaint(gradientPaint);
        g2.fillRect(0, 0, width, height);
       
    }
    public static void main(String args[]){
           JFrame jf=new JFrame("GradientPanelTest");
           jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
           GradientPanel gp=new GradientPanel(new BorderLayout());
           jf.getContentPane().add(gp);
           jf.setSize(500,400);
           jf.setVisible(true);
    }
}
