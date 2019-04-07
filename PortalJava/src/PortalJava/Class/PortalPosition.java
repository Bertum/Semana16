/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PortalJava.Class;

/**
 *
 * @author Bertum
 */
public class PortalPosition {

    public int x;
    public int y;
    public boolean isBlue;

    public PortalPosition() {

    }

    public PortalPosition(int x, int y, boolean blue) {
        this.x = x;
        this.y = y;
        this.isBlue = blue;
    }
    /*"x": this.x + (this.img.width / 4) + Math.cos(this.angle - Math.PI / 2) * this.speed * index,
                    "y": this.y + (this.img.width / 4) + Math.sin(this.angle - Math.PI / 2) * this.speed * index,
                    "blue": blue*/
}
