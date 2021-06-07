//https://blog.naver.com/yoonemong/220861751839
package Client;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Draw extends JPanel{
    private Vector<Point> xy = new Vector<Point>();
        
    public Draw() {
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) { 
                xy.add(e.getPoint());
                repaint();
            }
        });
            
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                xy.add(null);
                xy.add(e.getPoint());
            }
        });
    }
        
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 1; i < xy.size(); i++) {
           if (xy.get(i - 1) == null)
                continue;
            else if (xy.get(i) == null)
                continue;
            else
                g.drawLine((int) xy.get(i - 1).getX(), (int) xy.get(i - 1).getY(),
                (int) xy.get(i).getX(), (int) xy.get(i).getY());
        }
    }
}