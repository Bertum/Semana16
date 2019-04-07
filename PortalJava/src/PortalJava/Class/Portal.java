/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PortalJava.Class;

import PortalJava.Functions;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Bertum
 */
public class Portal {

    public boolean active;
    public int type;
    public int x;
    public int y;
    public int direction;
    public int width;
    public int height;
    private BufferedImage img;
    private Functions functions;

    public Portal(int x, int y, int type) {
        this.active = true;
        this.x = x;
        this.y = y;
        this.type = type;
        this.width = 20;
        this.height = 40;
        this.functions = new Functions();
        if (type == 0) {
            this.img = functions.getImage("bluePortal.png");
        } else {
            this.img = functions.getImage("yellowPortal.png");
        } // TODO Auto-generated catch block
    }

    public void draw(Graphics2D canvas) {
        //canvas.drawImage(this.img, this.x, this.y, null);
        canvas.drawImage(this.img, this.x - (this.width / 2), this.y - (this.height / 2), null);
        //  INTERACTIVE_CTX.drawImage(this.img, this.x - (this.width / 2), this.y - (this.height / 2), this.width, this.height);
    }
}
