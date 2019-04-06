package PortalJava;

import PortalJava.Class.Door;
import PortalJava.Class.Player;
import PortalJava.Class.Switch;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PortalJava extends JPanel implements KeyListener, Runnable {
    //Canvas dimensions

    int CANVAS_WIDTH = 768;
    int CANVAS_HEIGHT = 576;

    // Contextos
    Graphics2D MAP_CTX = null;
    Graphics2D COLLISION_CTX = null;
    Graphics2D INTERACTIVE_CTX = null;
    Graphics2D SCANNERS_CTX = null;

    float timer;
    Player player = null;
//Image
    BufferedImage closeDoorImageV = null;
    BufferedImage openDoorImageV = null;
    BufferedImage switchOffImage = null;
    BufferedImage switchOnImage = null;

//Switch
    Switch switchButton = null;
    Door door = null;

//Images canvas
    BufferedImage imgMap = null;
    BufferedImage imgMapCollisions = null;

    JFrame frame;
    Canvas canvas;
    Canvas interactionCanvas;
    BufferStrategy gameBufferStrategy;
    BufferStrategy interactionBufferStrategy;
    private BufferedImage imagenmanzana;
    int posx = 256;
    int posy = 256;

    public PortalJava() {
        loadImages();

        player = new Player(posx, posy);

        frame = new JFrame("Lazzy Portals");

        JPanel panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(512, 512));
        panel.setLayout(null);
        panel.setFocusable(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setVisible(true);

        //Game Canvas
        canvas = new Canvas();
        canvas.setBounds(0, 0, 512, 512);
        canvas.setIgnoreRepaint(true);
        canvas.setVisible(true);
        panel.add(canvas);

        //Interaction Canvas
        interactionCanvas = new Canvas();
        interactionCanvas.setBounds(0, 0, 512, 512);
        interactionCanvas.setIgnoreRepaint(true);
        interactionCanvas.setVisible(false);
        panel.add(interactionCanvas);

        canvas.createBufferStrategy(2);
        gameBufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();

        interactionCanvas.createBufferStrategy(2);
        interactionBufferStrategy = interactionCanvas.getBufferStrategy();
        interactionCanvas.requestFocus();
        
        canvas.addKeyListener(this);
    }

    private void loadImages() {
        closeDoorImageV = getImage("closedDoorV.png");
        openDoorImageV = getImage("openDoorV.png");
        switchOffImage = getImage("deactivatedButton.png");
        switchOnImage = getImage("activeButton.png");
        imgMap = getImage("map_1.png");
        imgMapCollisions = getImage("map_color_1.png");
    }

    private BufferedImage getImage(String imageName) {
        BufferedImage image = null;
        try {
            URL resource = PortalJava.class.getResource("/PortalJava/images/" + imageName);
            image = ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static void main(String[] args) {
        PortalJava ex = new PortalJava();
        new Thread(ex).start();
    }

    @Override
    public void run() {
        while (true) {
            posx += (Math.random() - 0.4) * 5;
            posy += (Math.random() - 0.4) * 5;
            Graphics2D g = (Graphics2D) gameBufferStrategy.getDrawGraphics();
            g.clearRect(0, 0, 512, 512);
            g.drawImage(player.img, player.x, player.y, null);
            g.dispose();
            gameBufferStrategy.show();
            try {
                Thread.sleep(3);
            } catch (InterruptedException ex) {
                Logger.getLogger(PortalJava.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        player.keyPressed(ke);
    }
}
