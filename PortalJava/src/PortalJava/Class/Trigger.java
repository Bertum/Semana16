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
public class Trigger {

    public int x;
    public int y;
    public BufferedImage spriteOff;
    public BufferedImage spriteOn;
    public BufferedImage currentSprite;
    public boolean triggered;

    public Trigger(int x, int y, BufferedImage spriteOff, BufferedImage spriteOn) {
        this.x = x;
        this.y = y;
        this.spriteOff = spriteOff;
        this.spriteOn = spriteOn;
        this.triggered = false;
        this.currentSprite = spriteOff;
    }

    public void trigger() {
        this.triggered = !this.triggered;
        if (this.triggered) {
            this.currentSprite = this.spriteOn;
        } else {
            this.currentSprite = this.spriteOff;
        }
    }

    public void draw(Graphics2D interactiveCtx) {
        interactiveCtx.drawImage(this.currentSprite, this.x, this.y, null);
    }
}
