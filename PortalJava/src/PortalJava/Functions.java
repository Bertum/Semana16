/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PortalJava;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 *
 * @author Bertum
 */
public class Functions {

    /*

function initMapCanvas() {
    $("#map").attr("width", CANVAS_WIDTH);
    $("#map").attr("height", CANVAS_HEIGHT);
    MAP_CTX = document.getElementById("map").getContext("2d");
}

function initCollisionCanvas() {
    $("#collision").attr("width", CANVAS_WIDTH);
    $("#collision").attr("height", CANVAS_HEIGHT);
    COLLISION_CTX = document.getElementById("collision").getContext("2d");
}

function initInteractiveCanvas() {
    $("#interactive").attr("width", CANVAS_WIDTH);
    $("#interactive").attr("height", CANVAS_HEIGHT);
    INTERACTIVE_CTX = document.getElementById("interactive").getContext("2d");
}


$(document).keydown(function(event) {
    if (numPlayer == 1) {
        if (event.key == "w") { Player.acceleration = 1; }
        if (event.key == "s") { Player.aceleration = -1; }
        if (event.key == "a") { Player.rotation = -0.15; }
        if (event.key == "d") { Player.rotation = +0.15; }

        if (event.key == "q") { Player.shootPortal(0) }
        if (event.key == "e") { Player.shootPortal(1) }
    }
    else {
        if (event.key == "w") { secondPlayer.acceleration = 1; }
        if (event.key == "s") { secondPlayer.aceleration = -1; }
        if (event.key == "a") { secondPlayer.rotation = -0.15; }
        if (event.key == "d") { secondPlayer.rotation = +0.15; }

        if (event.key == "q") { secondPlayer.shootPortal(0) }
        if (event.key == "e") { secondPlayer.shootPortal(1) }
    }

});
$(document).keyup(function(event) {
    if (numPlayer == 1) {
        if (event.key == "w") { Player.acceleration = 0 }
        if (event.key == "s") { Player.acceleration = 0 }
        if (event.key == "a") { Player.rotation = 0 }
        if (event.key == "d") { Player.rotation = 0 }
    }
    else {
        if (event.key == "w") { secondPlayer.acceleration = 0 }
        if (event.key == "s") { secondPlayer.acceleration = 0 }
        if (event.key == "a") { secondPlayer.rotation = 0 }
        if (event.key == "d") { secondPlayer.rotation = 0 }
    }
});

function endGame() {
    if (player.actualLife <= 0) {
        gamePaused = true;
        $("#gameOver").show();
    }
    var rightImage = colorBackgroundContext.getImageData(player.posX + player.sprite.width + 5, player.posY, 1, 1).data[1];
    if (rightImage >= 250) {
        gamePaused = true;
        $("#youWon").show();
    }
}

function reload() {
    location.reload();
}

function backToMenu() {
    window.location.href = "index.html";
}

function loadImages() {
    doorImageV = new Image();
    openDoorImageV = new Image();
    switchOffImage = new Image();
    switchOnImage = new Image();
    switchOffImage.src = "img/deactivatedButton.png";
    switchOnImage.src = "img/activeButton.png";
    doorImageV.src = "img/closedDoorV.png";
    openDoorImageV.src = "img/openDoorV.png";
}

function initButtonsAndDoors() {
    door = new Door(704, 257, doorImageV, openDoorImageV);
    switchButton = new Switch(170, 450, switchOffImage, switchOnImage, door);
}

$(document).ready(function() {
    $("#newGame").click(function() {
        //CARGAR nueva partida base de datos y mantener al jugador en espera

        //Pruebas
        $("#menu").fadeOut("slow");
        loop();
    });
});
     */
    //TODO: test if work
    public boolean checkPixel(BufferedImage mapData, int xPixel, int yPixel, int pixelOne, int pixelTwo, int pixelThree, int pixelAlpha
    ) {
        boolean isSelected = false;
        int pixel = mapData.getRGB(xPixel, yPixel);
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        if (red == pixelOne && green == pixelTwo && blue == pixelThree && alpha == pixelAlpha) {
            isSelected = true;
        }

        return isSelected;
    }

    //!Important: Don´t delete this
    /*   int w = mapData.getWidth();
        int h = mapData.getHeight();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int pixel = mapData.getRGB(xpixel, yPixel);
                int alpha = (pixel >> 24) & 0xff;
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                if (red == pixelOne && green == pixelTwo && blue == pixelThree && alpha == pixelAlpha) {
                    isSelected = true;
                }
            }
        }*/
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
}
