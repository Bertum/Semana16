/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PortalJava.Class;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

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
    public float width;
    public float height;
    private BufferedImage img;

    public Portal(int x, int y, int type) {
        this.active = true;
        this.x = x;
        this.y = y;
        this.type = type;
        this.width = 20;
        this.height = 40;
        try {
            if (type == 0) {
                this.img = ImageIO.read(new File("C://imagenes//bluePortal.png"));
            } else {
                this.img = ImageIO.read(new File("C://imagenes//yellowPortal.png"));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D canvas) {
        canvas.drawImage(this.img, this.x, this.y, null);
        //  INTERACTIVE_CTX.drawImage(this.img, this.x - (this.width / 2), this.y - (this.height / 2), this.width, this.height);
    }
}
