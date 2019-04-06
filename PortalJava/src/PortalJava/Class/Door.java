/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PortalJava.Class;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Bertum
 */
public class Door extends Trigger {

    public Door(int x, int y, BufferedImage spriteOff, BufferedImage spriteOn) {
        super(x, y, spriteOff, spriteOn);
    }

    public void draw(Graphics2D collisionCtx, Graphics2D interactiveCtx) {
        if (this.triggered) {
            this.currentSprite = this.spriteOn;
            //TODO: Fix
            //collisionCtx.fillStyle = "white"
        } else {
            this.currentSprite = this.spriteOff;
            //TODO: Fix
            //collisionCtx.fillStyle = "red"
        }
        collisionCtx.fillRect(this.x, this.y, 64, 64);
        this.draw(interactiveCtx);
    }
}
