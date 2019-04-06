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
    Canvas gameCanvas;
    Canvas collisionCanvas;
    Canvas interactiveCanvas;
    BufferStrategy gameBufferStrategy;
    BufferStrategy collisionBufferStrategy;
    BufferStrategy interactiveBufferStrategy;

    int posx = 600;
    int posy = 400;

    public PortalJava() {
        loadImages();

        player = new Player(posx, posy);

        frame = new JFrame("Lazzy Portals");

        configureGame();
        drawBackgrounds();
    }

    private void configureGame() {
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        panel.setLayout(null);
        panel.setFocusable(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setVisible(true);
        //TODO: Make it work to refactor code
        /*gameCanvas = configureCanvas(panel);
        collisionCanvas = configureCanvas(panel);
        interactiveCanvas = configureCanvas(panel);*/

        //Game Canvas
        gameCanvas = new Canvas();
        gameCanvas.setBounds(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        gameCanvas.setIgnoreRepaint(true);
        gameCanvas.setVisible(true);

        panel.add(gameCanvas);
        //collision Canvas
        collisionCanvas = new Canvas();
        collisionCanvas.setBounds(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        collisionCanvas.setIgnoreRepaint(true);
        collisionCanvas.setVisible(false);
        panel.add(collisionCanvas);
        gameCanvas.createBufferStrategy(2);
        gameBufferStrategy = gameCanvas.getBufferStrategy();
        gameCanvas.requestFocus();

        collisionCanvas.createBufferStrategy(2);
        collisionBufferStrategy = collisionCanvas.getBufferStrategy();
        collisionCanvas.requestFocus();

        //Interaction Canvas
        interactiveCanvas = new Canvas();
        interactiveCanvas.setBounds(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        interactiveCanvas.setIgnoreRepaint(true);
        interactiveCanvas.setVisible(false);
        panel.add(interactiveCanvas);
        interactiveCanvas.createBufferStrategy(2);
        interactiveBufferStrategy = interactiveCanvas.getBufferStrategy();
        interactiveCanvas.requestFocus();

        gameCanvas.addKeyListener(this);
        MAP_CTX = (Graphics2D) gameBufferStrategy.getDrawGraphics();
        COLLISION_CTX = (Graphics2D) collisionBufferStrategy.getDrawGraphics();
        INTERACTIVE_CTX = (Graphics2D) interactiveBufferStrategy.getDrawGraphics();
    }

    private Canvas configureCanvas(JPanel panel) {
        Canvas canvas = new Canvas();
        canvas.setBounds(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setIgnoreRepaint(true);
        canvas.setVisible(false);
        panel.add(canvas);
        return canvas;
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

            INTERACTIVE_CTX.clearRect(0, 0, 512, 512);
            INTERACTIVE_CTX.drawImage(player.img, player.x, player.y, null);
            INTERACTIVE_CTX.dispose();
            gameBufferStrategy.show();
            collisionBufferStrategy.show();
            interactiveBufferStrategy.show();
            try {
                Thread.sleep(3);
            } catch (InterruptedException ex) {
                Logger.getLogger(PortalJava.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void drawBackgrounds() {
        MAP_CTX.drawImage(imgMap, 0, 0, null);
        COLLISION_CTX.drawImage(imgMapCollisions, 0, 0, null);
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
