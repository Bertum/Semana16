/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PortalJava.Class;

import PortalJava.Functions;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author Bertum
 */
public class Player {

    public int id;
    public int x;
    public int y;
    public float acceleration;
    public float rotation;
    public float angle;
    public float speed = 5;
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
        try {
            //TODO: relative path
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

    public void movementControl(Graphics2D canvas) {
        boolean collision = this.collision();
        this.checkButtonCollision();
        if (collision == false) {
            this.movement();
        }
        this.angle += this.rotation;
        this.scope(canvas);
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
        //if(e.getKeyCode() == 81)    this.shootPortal(0);
        //E
        //if(e.getKeyCode() == 69)    this.shootPortal(1);
    }

    public boolean collision() {
        //        var colisionDatos = COLLISION_CTX.getImageData(
//                this.x + (this.img.width / 4) + Math.cos(this.angle - Math.PI / 2) * this.speed * 4,
//                this.y + (this.img.width / 4) + Math.sin(this.angle - Math.PI / 2) * this.speed * 4,
//                1,
//                1).data;
//
//        var colisionCentralDatos = COLLISION_CTX.getImageData(
//                this.x + (this.img.width / 4) + Math.cos(this.angle - Math.PI / 2) * this.speed,
//                this.y + (this.img.width / 4) + Math.sin(this.angle - Math.PI / 2) * this.speed,
//                1,
//                1).data;
//
//        if (checkPixel(colisionDatos, 255, 0, 0) || checkPixel(colisionDatos, 0, 0, 255)) {
//            console.log("colision");
//            return true;
//        } else if (checkPixel(colisionCentralDatos, 0, 0, 0)) {
//            this.die();
//        } else {
//            return false;
//        }
        return false;
    }

//    this.collision  = function()
//
//    {
//
//        var colisionDatos = COLLISION_CTX.getImageData(
//                this.x + (this.img.width / 4) + Math.cos(this.angle - Math.PI / 2) * this.speed * 4,
//                this.y + (this.img.width / 4) + Math.sin(this.angle - Math.PI / 2) * this.speed * 4,
//                1,
//                1).data;
//
//        var colisionCentralDatos = COLLISION_CTX.getImageData(
//                this.x + (this.img.width / 4) + Math.cos(this.angle - Math.PI / 2) * this.speed,
//                this.y + (this.img.width / 4) + Math.sin(this.angle - Math.PI / 2) * this.speed,
//                1,
//                1).data;
//
//        if (checkPixel(colisionDatos, 255, 0, 0) || checkPixel(colisionDatos, 0, 0, 255)) {
//            console.log("colision");
//            return true;
//        } else if (checkPixel(colisionCentralDatos, 0, 0, 0)) {
//            this.die();
//        } else {
//            return false;
//        }
//    }
    //TODO
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

    public void scope(Graphics2D canvas) {
        //INTERACTIVE_CTX.fillStyle = "red";
        /*for (int index = 3; index < 1000; index++) {
            canvas.fillRect(
                    this.x + (this.img.getWidth() / 4) + Math.cos(this.angle - Math.PI / 2) * this.speed * index,
                    this.y + (this.img.getWidth()) / 4) + Math.sin(this.angle - Math.PI / 2) * this.speed * index
            ,
                    1,
                    1)
            var dataParedes = COLLISION_CTX.getImageData(
                    this.x + (this.img.getWidth()) / 4) + Math.cos(this.angle - Math.PI / 2) * this.speed * index,
                    this.y + (this.img.getWidth() / 4) + Math.sin(this.angle - Math.PI / 2) * this.speed * index,
                    1,
                    1).data

            if (checkPixel(dataParedes, 255, 0, 0) || checkPixel(dataParedes, 0, 0, 255)) {
                boolean blue;
                if (checkPixel(dataParedes, 255, 0, 0)) {
                    blue = false;
                } else {
                    blue = true;
                }
                this.lastPositionPortal = {
                    "x": this.x + (this.img.width / 4) + Math.cos(this.angle - Math.PI / 2) * this.speed * index,
                    "y": this.y + (this.img.width / 4) + Math.sin(this.angle - Math.PI / 2) * this.speed * index,
                    "blue": blue
                }
                break;
                //index = 1000;
            }

        }*/
    }

//    this.scope  = function()
//
//    {
//        INTERACTIVE_CTX.fillStyle = "red";
//        for (let index = 3; index < 1000; index++) {
//            INTERACTIVE_CTX.fillRect(
//                    this.x + (this.img.width / 4) + Math.cos(this.angle - Math.PI / 2) * this.speed * index,
//                    this.y + (this.img.width / 4) + Math.sin(this.angle - Math.PI / 2) * this.speed * index,
//                    1,
//                    1)
//            var dataParedes = COLLISION_CTX.getImageData(
//                    this.x + (this.img.width / 4) + Math.cos(this.angle - Math.PI / 2) * this.speed * index,
//                    this.y + (this.img.width / 4) + Math.sin(this.angle - Math.PI / 2) * this.speed * index,
//                    1,
//                    1).data
//
//            if (checkPixel(dataParedes, 255, 0, 0) || checkPixel(dataParedes, 0, 0, 255)) {
//                var blue;
//                if (checkPixel(dataParedes, 255, 0, 0)) {
//                    blue = false;
//                } else {
//                    blue = true;
//                }
//                this.lastPositionPortal = {
//                    "x": this.x + (this.img.width / 4) + Math.cos(this.angle - Math.PI / 2) * this.speed * index,
//                    "y": this.y + (this.img.width / 4) + Math.sin(this.angle - Math.PI / 2) * this.speed * index,
//                    "blue": blue
//                }
//                break;
//                //index = 1000;
//            }
//
//        }
//    }
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
