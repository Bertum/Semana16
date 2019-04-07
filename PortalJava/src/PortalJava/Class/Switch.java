/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PortalJava.Class;

import java.awt.image.BufferedImage;

/**
 *
 * @author Bertum
 */
public class Switch extends Trigger {

    private Door target;

    public Switch(int x, int y, BufferedImage spriteOff, BufferedImage spriteOn, Door target) {
        super(x, y, spriteOff, spriteOn);
        this.target = target;
    }

    @Override
    public void trigger() {
        this.triggered = !this.triggered;
        if (this.triggered) {
            this.currentSprite = this.spriteOn;
        } else {
            this.currentSprite = this.spriteOff;
        }
        this.target.trigger();
    }
}
