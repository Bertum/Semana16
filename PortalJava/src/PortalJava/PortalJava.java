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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Miguel Agudo, Alberto Delgado, Óscar Sánchez, Ferran Ases
 */
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
    Functions functions;

    int posx = 600;
    int posy = 400;

    public PortalJava() {
        functions = new Functions();
        loadImages();
        door = new Door(704, 257, closeDoorImageV, openDoorImageV);
        switchButton = new Switch(170, 450, switchOffImage, switchOnImage, door);
        player = new Player(posx, posy, switchButton);

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

        //Game Canvas
        gameCanvas = new Canvas();
        gameCanvas.setBounds(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        gameCanvas.setIgnoreRepaint(true);
        gameCanvas.setVisible(true);

        panel.add(gameCanvas);

        gameCanvas.createBufferStrategy(2);
        gameBufferStrategy = gameCanvas.getBufferStrategy();
        gameCanvas.requestFocus();

        //collision Canvas
        collisionCanvas = new Canvas();
        collisionCanvas.setBounds(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        collisionCanvas.setIgnoreRepaint(true);
        collisionCanvas.setVisible(false);
        panel.add(collisionCanvas);

        collisionCanvas.createBufferStrategy(2);
        collisionBufferStrategy = collisionCanvas.getBufferStrategy();
        collisionCanvas.requestFocus();

        //Interaction Canvas
        interactiveCanvas = new Canvas();
        interactiveCanvas.setBounds(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        interactiveCanvas.setIgnoreRepaint(true);
        interactiveCanvas.setVisible(true);
        panel.add(interactiveCanvas);
        interactiveCanvas.createBufferStrategy(2);
        interactiveBufferStrategy = interactiveCanvas.getBufferStrategy();
        interactiveCanvas.requestFocus();

        gameCanvas.addKeyListener(this);
        interactiveCanvas.addKeyListener(this);
        MAP_CTX = (Graphics2D) gameBufferStrategy.getDrawGraphics();
        COLLISION_CTX = (Graphics2D) collisionBufferStrategy.getDrawGraphics();
        INTERACTIVE_CTX = (Graphics2D) interactiveBufferStrategy.getDrawGraphics();
    }

    private void loadImages() {
        closeDoorImageV = functions.getImage("closedDoorV.png");
        openDoorImageV = functions.getImage("openDoorV.png");
        switchOffImage = functions.getImage("deactivatedButton.png");
        switchOnImage = functions.getImage("activeButton.png");
        imgMap = functions.getImage("map_1.png");
        imgMapCollisions = functions.getImage("map_color_1.png");
    }

    public static void main(String[] args) {
        PortalJava ex = new PortalJava();
        new Thread(ex).start();
    }

    @Override
    public void run() {
        while (true) {
            MAP_CTX.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
            drawBackgrounds();
            player.movementControl(MAP_CTX, imgMapCollisions);
            door.draw(MAP_CTX, COLLISION_CTX);
            switchButton.draw(MAP_CTX);
            player.draw(MAP_CTX);
            gameBufferStrategy.show();
            collisionBufferStrategy.show();
            interactiveBufferStrategy.show();
            INTERACTIVE_CTX.dispose();
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
        player.keyReleased(ke);
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        player.keyPressed(ke);
    }
}
