import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class Painting extends JFrame{
    public static void main(String[] args) {
        new Painting();
    }

    public Painting() {
        super("Painting");
        setSize(800, 500);
        setDefaultCloseOperation(3);
        setVisible(true);
        setLayout(new FlowLayout());
        
        addSliders();
        addPArea(500, 400);
        
        repaint();
        revalidate();
    }
    
    JSlider R,G,B;
    private void  addSliders(){
        R = new JSlider(0, 255, 0);
        R.setPreferredSize(new Dimension(255, 10));
        R.setVisible(true);
        add(R);
        G = new JSlider(0, 255, 0);
        G.setPreferredSize(new Dimension(255, 10));
        G.setVisible(true);
        add(G);
        B = new JSlider(0, 255, 0);
        B.setPreferredSize(new Dimension(255, 10));
        B.setVisible(true);
        add(B);
        
        setListeners();
    }
    Color c = new Color(Color.BLACK.getRGB());
    private void setListeners(){
        R.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                c = new Color(R.getValue(), G.getValue(), B.getValue());
            }
        });
        G.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                c = new Color(R.getValue(), G.getValue(), B.getValue());
            }
        });
        B.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                c = new Color(R.getValue(), G.getValue(), B.getValue());
            }
        });
    }
    
    JLabel label;
    BufferedImage image;
    private void addPArea(int Width, int Height){
        label = new JLabel(new ImageIcon(createPArea(Width, Height)));
        label.setPreferredSize(new Dimension(Width, Height));
        label.setVisible(true);
        add(label);
        addListener();
    }
    Graphics2D g;
    private BufferedImage createPArea(int Width, int Height){
        image = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < Width; i++) {
            for (int j = 0; j < Height; j++) {
                image.setRGB(i, j, Color.WHITE.getRGB());
            }
        }
        g = (Graphics2D) image.getGraphics();
        return image;
    }
    int x,y;
    private void addListener(){
        label.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                g.setStroke(new BasicStroke(1));
                g.setColor(c);
                g.drawLine(x, y, e.getX(), e.getY());
                x = e.getX(); y = e.getY();
//                image.setRGB(e.getX(), e.getY(), c.getRGB());
                label.repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                x = e.getX(); y = e.getY();
            }
            
        });
    }
}
