/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PortalJava;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author Miguel Agudo, Alberto Delgado, Óscar Sánchez, Ferran Ases
 */
public class Functions {

    public boolean checkPixel(BufferedImage mapData, int xPixel, int yPixel, int pixelOne, int pixelTwo, int pixelThree, int pixelAlpha
    ) {
        boolean isSelected = false;
        Color pixel = new Color(mapData.getRGB(xPixel, yPixel));
        int alpha = pixel.getAlpha();
        int red = pixel.getRed();
        int green = pixel.getGreen();
        int blue = pixel.getBlue();
        if (red == pixelOne && green == pixelTwo && blue == pixelThree && alpha == pixelAlpha) {
            isSelected = true;
        }

        return isSelected;
    }

    public boolean squareCollision(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
        return x1 < x2 + w2
                && x1 + w1 > x2
                && y1 < y2 + h2
                && y1 + h1 > y2;
    }

    public void drawImageRot(Graphics2D context, BufferedImage img, int x, int y, int width, int height, float rad) {
        // Rotation information
        double locationX = img.getWidth() / 2;
        double locationY = img.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rad, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        // Drawing the rotated image at the required drawing locations
        context.drawImage(op.filter(img, null), x, y, null);
    }

    public BufferedImage getImage(String imageName) {
        BufferedImage image = null;
        try {
            URL resource = PortalJava.class.getResource("/PortalJava/images/" + imageName);
            image = ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
