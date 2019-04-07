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
 * @author Miguel Agudo, Alberto Delgado, Óscar Sánchez, Ferran Ases
 */
public class Door extends Trigger {

    public Door(int x, int y, BufferedImage spriteOff, BufferedImage spriteOn) {
        super(x, y, spriteOff, spriteOn);
    }

    @Override
    public void trigger() {
        this.triggered = !this.triggered;
        if (this.triggered) {
            this.currentSprite = this.spriteOn;
        } else {
            this.currentSprite = this.spriteOff;
        }
    }

    public void draw(Graphics2D collisionCtx, Graphics2D interactiveCtx) {
        if (this.triggered) {
            this.currentSprite = this.spriteOn;
        } else {
            this.currentSprite = this.spriteOff;
        }
        collisionCtx.drawImage(this.currentSprite, this.x, this.y, null);
    }
}
