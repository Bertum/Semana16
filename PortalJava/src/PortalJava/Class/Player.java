/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PortalJava.Class;

import PortalJava.Functions;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author Miguel Agudo, Alberto Delgado, Óscar Sánchez, Ferran Ases
 */
public class Player {

    public int id;
    public int x;
    public int y;
    public float acceleration;
    public float rotation;
    public float angle;
    public int speed = 5;
    public boolean inButton;
    public PortalPosition lastPositionPortal;
    public Portal portal0;
    public Portal portal1;
    public BufferedImage img;
    public Switch button;
    private Functions functions;

    public Player(int x, int y, Switch button) {
        this.x = x;
        this.y = y;
        this.acceleration = 0;
        this.rotation = 0;
        this.angle = 0;
        this.speed = 3;
        this.inButton = false;
        this.portal0 = null;
        this.portal1 = null;
        this.button = button;
        this.functions = new Functions();
        //this.lastPositionPortal = new PortalPosition(0, 0, true);
        try {
            URL resource = Player.class.getResource("/PortalJava/images/players/player1.png");
            this.img = ImageIO.read(resource);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void rotate(float value) {
        this.angle += value;
    }

    public void movementControl(Graphics2D canvas, BufferedImage collisionImage) {
        boolean collision = this.collision(collisionImage);
        this.checkButtonCollision();
        if (collision == false) {
            this.movement();
        }
        this.angle += this.rotation;
        this.scope(canvas, collisionImage);
        this.playerIntoPortal();
    }

    public void movement() {
        this.x += Math.cos(this.angle - Math.PI / 2) * this.speed * this.acceleration;
        this.y += Math.sin(this.angle - Math.PI / 2) * this.speed * this.acceleration;
    }

    public void draw(Graphics2D canvas) {
        functions.drawImageRot(canvas, this.img, this.x - (this.img.getWidth() / 4), this.y - (this.img.getWidth() / 4), this.img.getWidth(), this.img.getHeight(), this.angle);

        //Draw portals
        if (this.portal0 != null) {
            this.portal0.draw(canvas);
        }
        if (this.portal1 != null) {
            this.portal1.draw(canvas);
        }
    }

    public void keyReleased(KeyEvent e) {
        //W
        if (e.getKeyCode() == 87) {
            this.acceleration = 0;
        }
        //S
        if (e.getKeyCode() == 83) {
            this.acceleration = 0;
        }
        //A
        if (e.getKeyCode() == 65) {
            this.rotation = 0;
        }
        //D
        if (e.getKeyCode() == 68) {
            this.rotation = 0;
        }
    }

    public void keyPressed(KeyEvent e) {
        //System.out.println(e.getKeyCode());
        //W
        if (e.getKeyCode() == 87) {
            this.acceleration = 1;
        }
        //S
        if (e.getKeyCode() == 83) {
            this.acceleration = -1;
        }
        //A
        if (e.getKeyCode() == 65) {
            this.rotation = (float) -0.05;
        }
        //D
        if (e.getKeyCode() == 68) {
            this.rotation = (float) 0.05;
        }
        //Q
        if (e.getKeyCode() == 81) {
            this.shootPortal(0);
        }
        //E
        if (e.getKeyCode() == 69) {
            this.shootPortal(1);
        }
    }

    public boolean collision(BufferedImage image) {
        int dataCollisionX = this.x + (this.img.getWidth() / 4) + (int) Math.cos(this.angle - Math.PI / 2) * this.speed * 4;
        int dataCollisionY = this.y + (this.img.getWidth() / 4) + (int) Math.sin(this.angle - Math.PI / 2) * this.speed * 4;
        int dataCentralCollisionX = this.x + (this.img.getWidth() / 4) + (int) Math.cos(this.angle - Math.PI / 2) * this.speed;
        int dataCentralCollisionY = this.y + (this.img.getWidth() / 4) + (int) Math.sin(this.angle - Math.PI / 2) * this.speed;

        if (functions.checkPixel(image, dataCollisionX, dataCollisionY, 255, 0, 0, 255) || functions.checkPixel(image, dataCollisionX, dataCollisionY, 0, 0, 255, 255)) {
            return true;
        } else if (functions.checkPixel(image, dataCentralCollisionX, dataCentralCollisionY, 0, 0, 0, 255)) {
            this.die();
        } else {
            return false;
        }
        return false;
    }

    public void die() {
        System.out.println("mueres");
    }

    public void checkButtonCollision() {
        if (functions.squareCollision(this.x + 16, this.y + 16, 1, 1,
                this.button.x, this.button.y, this.button.currentSprite.getWidth(), this.button.currentSprite.getHeight())) {
            if (!this.inButton) {
                this.button.trigger();
                this.inButton = true;
            }
        } else {
            this.inButton = false;
        }
    }

    public void scope(Graphics2D canvas, BufferedImage image) {
        try {
            for (int i = 0; i < 768; i++) {
                double calculatedX = this.x + (this.img.getWidth() / 4) + Math.cos(this.angle - Math.PI / 2) * this.speed * i;
                double calculatedY = this.y + (this.img.getWidth() / 4) + Math.sin(this.angle - Math.PI / 2) * this.speed * i;
                canvas.setColor(Color.red);
                canvas.fillRect(
                        (int) calculatedX,
                        (int) calculatedY,
                        1,
                        1);
                if (functions.checkPixel(image, (int) calculatedX, (int) calculatedY, 255, 0, 0, 255) || functions.checkPixel(image, (int) calculatedX, (int) calculatedY, 0, 0, 255, 255)) {
                    boolean blue = true;
                    if (functions.checkPixel(image, (int) calculatedX, (int) calculatedY, 255, 0, 0, 255)) {
                        blue = false;
                    }
                    this.lastPositionPortal = new PortalPosition((int) calculatedX, (int) calculatedY, blue);
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("Se ha roto, pero no nos importa");
        }
    }

    public void shootPortal(int number) {
        //0 y 1
        boolean nearOfPortal = this.portalNearOfPortal(number);
        if (!nearOfPortal && this.lastPositionPortal.isBlue) {
            if (number == 0) {
                this.portal0 = new Portal(this.lastPositionPortal.x, this.lastPositionPortal.y, 0);
            } else {
                this.portal1 = new Portal(this.lastPositionPortal.x, this.lastPositionPortal.y, 1);
            }
        }
    }

    //Logica que deberia esta en functions
    public boolean portalNearOfPortal(int number) {
        Portal portalContrario = null;
        if (number == 0) {
            portalContrario = this.portal1;
        } else {
            portalContrario = this.portal0;
        }
        if (portalContrario == null) {
            return false;
        } else {
            if (Math.sqrt(Math.pow((this.lastPositionPortal.x - portalContrario.x), 2) + Math.pow((this.lastPositionPortal.y - portalContrario.y), 2)) < 30) {
                return true;
            } else {
                return false;
            }
        }
    }

    public void playerIntoPortal() {
        if (this.portal0 != null && this.portal1 != null) {
            if (Math.sqrt(Math.pow((this.x - this.portal0.x), 2) + Math.pow((this.y - this.portal0.y), 2)) < 20 && this.portal0.active) {
                this.exitInTheOtherPortal(0);
            } else if (Math.sqrt(Math.pow((this.x - this.portal1.x), 2) + Math.pow((this.y - this.portal1.y), 2)) < 20 && this.portal1.active) {
                this.exitInTheOtherPortal(1);
            }
            //Si esta lejos el personaje de el portal lo volvemos a activar
            if (Math.sqrt(Math.pow((this.x - this.portal0.x), 2) + Math.pow((this.y - this.portal0.y), 2)) > 25) {
                this.portal0.active = true;
            } else if (Math.sqrt(Math.pow((this.x - this.portal1.x), 2) + Math.pow((this.y - this.portal1.y), 2)) > 25) {
                this.portal1.active = true;
            }
        }
    }

    public void exitInTheOtherPortal(int number) {
        if (number == 0) {
            this.portal1.active = false;
            this.x = this.portal1.x;
            this.y = this.portal1.y;
        } else {
            this.portal0.active = false;
            this.x = this.portal0.x;
            this.y = this.portal0.y;
        }
    }
}
